#include "p3200262-p3150208-res.h"

/*
* OPERATING SYSTEMS PROJECT - SPRING/SUMMER 2022 AUEB
* SEATS RESERVATION SYSTEM
*
* TEAM MEMBERS:
* -- DIONYSIOS RIGATOS         P3200262
* -- CHRISTOFOROS PAPAPOSTOLOU P3150208
*/

pthread_mutex_t operator_lock_a;        // Locks Zone A
pthread_mutex_t operator_lock_b;        // Locks Zone B
pthread_mutex_t operator_wait_lock;     // Locks the operator wait condition
pthread_mutex_t cashier_wait_lock;      // Locks the cashier wait condition
pthread_mutex_t payment_lock;           // Locks the payment variable
pthread_mutex_t output_lock;            // Locks the output
pthread_mutex_t free_seats_lock;        // Locks the free_per_row array
pthread_mutex_t time_lock;              // Locks the time variables

pthread_cond_t operator_cond;           // Condition variable for operator
pthread_cond_t cashier_cond;            // Condition variable for cashier

unsigned int operators_available = OPERATORS;
unsigned int cashiers_available = CASHIERS;
unsigned int customer_count;

unsigned int seed;
unsigned int BALANCE;
unsigned int seat_fails;
unsigned int payment_fails;

double wait_time;
double call_time;

int seat_plan[ROWS_A*SEATS_PER_ROW + ROWS_B*SEATS_PER_ROW]; // The seat plan
int free_per_row[ROWS_A + ROWS_B];                          // Available seats per row

customer* customers;                                      // Array of customers

void* make_reservation(void* arg);

void operator_call(customer* c);
int reserve_seat(int from_row, int to_row, customer* c);
int first_free_seat(int row, int tickets);

void cashier_call(customer* c);
void cancel_reservation(customer* c);

void mutex_lock(pthread_mutex_t* lock);
void mutex_unlock(pthread_mutex_t* unlock);

void init_mutex_conds();
void destroy_mutex_conds();
void free_memory();

int main(int argc, char *argv[]) {

    // If the user does not provide the correct number of arguments, print an error message and exit
    if(argc != 3){
        printf("Error: Invalid number of arguments.\n");
        exit(-1);
    }

    customer_count = atoi(argv[1]); // First argument is the number of customers
    seed = atoi(argv[2]);           // Second argument is the seed for the random number generator
    srand(seed);                    // Seed the random number generator

    // If the number of customers is less than 1, the program will exit
    if(customer_count < 1){
        printf("Error: Invalid number of customers.\n");
        exit(-1);
    }

    printf("Welcome to the Derigni (amphi)theatre!\n");
    printf("Now taking reservations for the Operating Systems S/S 2022 Class.\n");
    printf("Reserve your seat (and up to 4 extra for your friends) or you will be standing!\n\n");

    init_mutex_conds();

    // Initialize the free_per_row counter array 
    for(int i = 0; i < ROWS_A + ROWS_B; i++){
        free_per_row[i] = 10;
    }
    
    pthread_t customer_threads[customer_count];
    customers = malloc(sizeof(*customers) * customer_count);

    // Creating the customers (thread) and starting their reservation (function call)
    for(int i = 0; i < customer_count; i++){
        customers[i].id = i + 1;
        int local_seed = seed + i + 1;
        if(pthread_create(&customer_threads[i], NULL, &make_reservation, (void*) &customers[i]) != 0){
            printf("Error: Failed to create customer thread %d\n", i);
            free_memory();
            destroy_mutex_conds();
            exit(-1);
        }
        sleep(rand_r(&local_seed) % (CALL_INTERVAL_MAX - CALL_INTERVAL_MIN + 1) + CALL_INTERVAL_MIN);
    }

    // Waiting for all the customers (threads) to finish their reservation (exit)
    for(int i = 0; i < customer_count; i++){        
        if(pthread_join(customer_threads[i], NULL) != 0){
            printf("Error: Failed to join customer thread %d\n", i);
            free_memory();
            destroy_mutex_conds();
            exit(-1);
        };
    }


    printf("\nReservations are now complete! PROFIT: %d€\n", BALANCE);
    printf("Statistics:\n");
    printf("\tSuccessful reservations                  : %.2f%%\n", ((customer_count - seat_fails - payment_fails)/(double)customer_count) * 100);
    printf("\tFailed reservations - NO SEATS AVAILABLE : %.2f%%\n", (seat_fails/(double)customer_count) * 100);
    printf("\tFailed reservations - PAYMENT FAILED     : %.2f%%\n", (payment_fails/(double)customer_count) * 100);
    printf("\tAverage wait time                        : %.2f sec\n", wait_time / (double)customer_count);
    printf("\tAverage call time                        : %.2f sec\n", call_time / (double)customer_count);

    printf("\n\n-----SEAT PLAN FOR TODAYS PLAY-----\n");
    for(int i = 0; i < ROWS_A + ROWS_B; i++){
        #ifdef DEBUG
        printf("Free seats in row %d: %d\n", i, free_per_row[i]);
        #endif
        for(int j = 0; j < SEATS_PER_ROW; j++){
            if(seat_plan[i*SEATS_PER_ROW + j] != 0){
                if(i < ROWS_A){
                    printf("[Zone A ");
                } else {
                    printf("[Zone B ");
                }
                printf("/ Row %d / Seat %d / Client %d]\n", i + 1, j + 1, seat_plan[i*SEATS_PER_ROW + j]);
            }
        }
    }
    
    free_memory();
    destroy_mutex_conds();

    return 0;
}

/*
* RESERVATION FUNCTION
*/
void* make_reservation(void* arg){
    customer* c = (customer*) arg;
    int local_seed = seed + c->id;
    int rc;
    struct timespec op_wait_start;
    struct timespec op_wait_end;
    struct timespec cs_wait_start;
    struct timespec cs_wait_end;
    struct timespec call_start;
    struct timespec call_end;

    c->tickets = rand_r(&local_seed) % TICKETS_MAX + TICKETS_MIN;
    c->cost = 0;

    clock_gettime(CLOCK_REALTIME, &call_start);

    clock_gettime(CLOCK_REALTIME, &op_wait_start);

    /* OPERATOR PART */
    // Operator availability check
    mutex_lock(&operator_wait_lock);
    while(operators_available <= 0){
        rc = pthread_cond_wait(&operator_cond, &operator_wait_lock);
        if(rc != 0){
            printf("Error: Condition failed to wait for operator availability.\n");
            pthread_exit(&rc);
        }
    }
    operators_available--;   
    mutex_unlock(&operator_wait_lock);


    clock_gettime(CLOCK_REALTIME, &op_wait_end);

    // Operator call
    // Time required to see if seats are available
    sleep(rand_r(&local_seed) % (OPERATOR_RESPONSE_MAX - OPERATOR_RESPONSE_MIN + 1) + OPERATOR_RESPONSE_MIN); 
    operator_call(c);

    // If no seats were available, return
    if(c->cost == 0){
        clock_gettime(CLOCK_REALTIME, &call_end);

        mutex_lock(&output_lock);
        printf("[CID: %d] Reservation failed! No %d consecutive seats are available in Zone %c.\n", c->id, c->tickets, c->zone);
        mutex_unlock(&output_lock);

        mutex_lock(&time_lock);
        wait_time += op_wait_end.tv_sec - op_wait_start.tv_sec;
        call_time += call_end.tv_sec - call_start.tv_sec;
        mutex_unlock(&time_lock);

        // We keep the zone and the amount of tickets in the struct in case
        // the customer wants to try again after someone else cancelled (For scalability, no real usage in this HW)
        c->row = -1;
        c->seat = -1;
        c->cost = -1;

        return NULL;
    }


    /* CASHIER PART */
    clock_gettime(CLOCK_REALTIME, &cs_wait_start);

    // Cashier availability check
    mutex_lock(&cashier_wait_lock);
    while(cashiers_available <= 0) {
        rc = pthread_cond_wait(&cashier_cond, &cashier_wait_lock);
        if(rc != 0){
            printf("Error: Condition failed to wait for cashier availability.\n");
            cancel_reservation(c);
            pthread_exit(&rc);
        }
    }
    cashiers_available--;
    mutex_unlock(&cashier_wait_lock);

    clock_gettime(CLOCK_REALTIME, &cs_wait_end);


    // Cashier call
    sleep(rand_r(&local_seed) % (CASHIER_RESPONSE_MAX - CASHIER_RESPONSE_MIN + 1) + CASHIER_RESPONSE_MIN);
    cashier_call(c);

    clock_gettime(CLOCK_REALTIME, &call_end);


    mutex_lock(&time_lock);
    wait_time += op_wait_end.tv_sec - op_wait_start.tv_sec + cs_wait_end.tv_sec - cs_wait_start.tv_sec;
    call_time += call_end.tv_sec - call_start.tv_sec;
    mutex_unlock(&time_lock);

    return NULL;
}

/*
* OPERATOR CALL MAIN FUNCTION
*/
void operator_call(customer* c){
    int local_seed = seed + c->id;
    // Check if the seats are available - each zone is accessible separately 
    if(rand_r(&local_seed) % 100 / 100.0f < PROBABILITY_ZONE_A){ // [0, 29] Zone A, [30, 99] Zone B
        mutex_lock(&operator_lock_a);
        c->zone = 'A';
        c->cost = COST_A * c->tickets * reserve_seat(0, ROWS_A, c);
        mutex_unlock(&operator_lock_a);
    }else{
        mutex_lock(&operator_lock_b);
        c->zone = 'B';
        c->cost = COST_B * c->tickets * reserve_seat(ROWS_A, ROWS_A + ROWS_B, c);
        mutex_unlock(&operator_lock_b);
    }
    mutex_lock(&operator_wait_lock);
    operators_available++;
    if(c->cost == 0){
        seat_fails++;
    }
    mutex_unlock(&operator_wait_lock);
    int rc = pthread_cond_broadcast(&operator_cond);
    if(rc != 0){
        printf("Error: Opreator condition failed to broadcast.\n");
        pthread_exit(&rc);
    }
}

/*
* OPERATOR CALL HELPER FUNCTION
* Finds TICKET consecutive seats in the seat plan, in the right zone
* Returns 1 if successful, 0 if unsuccessful
*/
int reserve_seat(int from_row, int to_row, customer* c){
    for(int i = from_row; i < to_row; i++){
        if(free_per_row[i] >= c->tickets){
            c->row = i;
            c->seat = first_free_seat(c->row, c->tickets);
            // If no seats were found in this row, continue
            if(c->seat == -1){      
                continue;
            }
            // If seats were found, update the seat plan
            for(int j = c->seat; j < c->seat + c->tickets; j++){
                seat_plan[c->row*SEATS_PER_ROW + j] = c->id;
            }
            mutex_lock(&free_seats_lock);
            free_per_row[c->row] -= c->tickets;
            mutex_unlock(&free_seats_lock);
            return 1;
        }
    }
    return 0;
}

/* 
* OPERATOR CALL HELPER FUNCTION
* Finds if there are TICKET amount of free seats in a row
* Returns the first empty seat if there are enough seats, -1 otherwise
*/
int first_free_seat(int row, int tickets) {
    int j = 0;
    // For every first empty seat in this row
    for (int i = 0; i < SEATS_PER_ROW && i + tickets <= SEATS_PER_ROW; i++) {
        if(seat_plan[row*SEATS_PER_ROW + i] == 0){
            j = 1;
            // Check if there are enough free seats consecutively
            for(; j < tickets; j++){
                if(seat_plan[row*SEATS_PER_ROW + i + j] != 0){
                    break;
                }
            }
            // If there are enough free seats, return the first empty seat
            if(j == tickets){
                return i;
            }
        }
    }
    return -1;
}

/*
* CASHIER CALL MAIN FUNCTION
*/
void cashier_call(customer* c){
    int local_seed = seed + c->id;
    if(rand_r(&local_seed) % 100 / 100.0f < PROBABILITY_PAY_SUCESS){ // [0-89] Success, [90-99] Failure
        mutex_lock(&payment_lock);
        BALANCE += c->cost;
        mutex_unlock(&payment_lock);

        // Print the reservation details
        mutex_lock(&output_lock);
        if(c->tickets == 1){
            printf("[CID: %d] Reservation successful! [Seat: %d, Row: %d, Zone: %c || Cost: %d]\n", c->id, c->seat + 1, c->row + 1, c->zone, c->cost);
        }else if(c->tickets > 1){
            printf("[CID: %d] Reservation successful! [Seats: %d - %d, Row: %d, Zone: %c || Cost: %d]\n", c->id, c->seat + 1, c->seat + c->tickets, c->row + 1, c->zone, c->cost);
        }
        mutex_unlock(&output_lock);
    }else{
        if(c->zone == 'A'){
            mutex_lock(&operator_lock_a);
            cancel_reservation(c);
            mutex_unlock(&operator_lock_a);
        }else if (c->zone == 'B'){
            mutex_lock(&operator_lock_b);
            cancel_reservation(c);
            mutex_unlock(&operator_lock_b);
        }

        // Print the failed payment message
        mutex_lock(&output_lock);
        if(c->tickets == 1){
            printf("[CID: %d] Reservation failed! Payment unsuccessful - [Seat: %d, Row: %d, Zone: %c || Cost: %d]\n", c->id, c->seat + 1, c->row + 1, c->zone, c->cost);
        }else{
            printf("[CID: %d] Reservation failed! Payment unsuccessful - [Seats: %d - %d, Row: %d, Zone: %c || Cost: %d]\n", c->id, c->seat + 1, c->seat + c->tickets, c->row + 1, c->zone, c->cost);
        }
        mutex_unlock(&output_lock);

        // Keep the customers info but mark the cost as -1 so we know his payment failed.
        // Keep the rest of the data in case they wish to retry the payment (Scalability, no real usage in this HW)
        c->cost = -1;
    }
    mutex_lock(&cashier_wait_lock);
    cashiers_available++;
    if(c->cost == -1 && c->tickets >= 1){
        payment_fails++;
    }
    mutex_unlock(&cashier_wait_lock);
    int rc = pthread_cond_broadcast(&cashier_cond);
    if(rc != 0){
        printf("Error: Cashier condition failed to broadcast.\n");
        pthread_exit(&rc);
    }
}

/*
* CASHIER CALL HELPER FUNCTION
* Returns the seats to the plan if a reservation was cancelled
*/
void cancel_reservation(customer* c){
    // Free the seats customer c reserved
    for(int i = 0; i < c->tickets; i++){
        seat_plan[c->row*SEATS_PER_ROW + c->seat + i] = 0;
    }
    // Return them to the free seats per row array
    mutex_lock(&free_seats_lock);
    free_per_row[c->row] += c->tickets;
    mutex_unlock(&free_seats_lock);
}

void free_memory(){
    free(customers);
}

void mutex_lock(pthread_mutex_t* lock){
    int rc = pthread_mutex_lock(lock);
    if(rc != 0){
        printf("Error %d: Could not lock mutex!\n", rc);
        pthread_exit(&rc);
    }
}

void mutex_unlock(pthread_mutex_t* unlock){
    int rc = pthread_mutex_unlock(unlock);
    if(rc != 0){
        printf("Error %d: Could not unlock mutex!\n", rc);
        pthread_exit(&rc);
    }
}

void init_mutex_conds(){
    if(pthread_mutex_init(&operator_lock_a, NULL) != 0){
        printf("Mutex operator_lock_a init failed");
        exit(-1);
    }
    if(pthread_mutex_init(&operator_lock_b, NULL) != 0){
        printf("Mutex operator_lock_b init failed\n");
        exit(-1);
    }
    if(pthread_mutex_init(&cashier_wait_lock, NULL) != 0){
        printf("Mutex cashier_wait_lock init failed\n");
        exit(-1);
    }
    if(pthread_mutex_init(&operator_wait_lock, NULL) != 0){
        printf("Mutex operator_wait_lock init failed\n");
        exit(-1);
    }
    if(pthread_mutex_init(&free_seats_lock, NULL) != 0){
        printf("Mutex free_seats_lock init failed\n");
        exit(-1);
    }
    if(pthread_mutex_init(&payment_lock, NULL) != 0){
        printf("Mutex payment_lock init failed\n");
        exit(-1);
    }

    if(pthread_mutex_init(&output_lock, NULL) != 0){
        printf("Mutex output_lock init failed\n");
        exit(-1);
    }
    if(pthread_mutex_init(&time_lock, NULL) != 0){
        printf("Mutex time_lock init failed\n");
        exit(-1);
    }

    if(pthread_cond_init(&cashier_cond, NULL) != 0){
        printf("Cond cashier_cond init failed\n");
        exit(-1);
    }
    if(pthread_cond_init(&operator_cond, NULL) != 0){
        printf("Cond operator_cond init failed\n");
        exit(-1);
    }
}

void destroy_mutex_conds(){
    if(pthread_mutex_destroy(&operator_lock_a) != 0){
        printf("Mutex operator_lock_a destroy failed\n");
        exit(-1);
    }
    if(pthread_mutex_destroy(&operator_lock_b) != 0){
        printf("Mutex operator_lock_b destroy failed\n");
        exit(-1);
    }
    if(pthread_mutex_destroy(&cashier_wait_lock) != 0){
        printf("Mutex cashier_wait_lock destroy failed\n");
        exit(-1);
    }
    if(pthread_mutex_destroy(&operator_wait_lock) != 0){
        printf("Mutex operator_wait_lock destroy failed\n");
        exit(-1);
    }
    if(pthread_mutex_destroy(&free_seats_lock) != 0){
        printf("Mutex free_seats_lock destroy failed\n");
        exit(-1);
    }
    if(pthread_mutex_destroy(&payment_lock) != 0){
        printf("Mutex payment_lock destroy failed\n");
        exit(-1);
    }

    if(pthread_mutex_destroy(&output_lock) != 0){
        printf("Mutex output_lock destroy failed\n");
        exit(-1);
    }
    if(pthread_mutex_destroy(&time_lock) != 0){
        printf("Mutex time_lock destroy failed\n");
        exit(-1);
    }

    if(pthread_cond_destroy(&cashier_cond) != 0){
        printf("Cond cashier_cond destroy failed\n");
        exit(-1);
    }
    if(pthread_cond_destroy(&operator_cond) != 0){
        printf("Cond operator_cond destroy failed\n");
        exit(-1);
    }
}