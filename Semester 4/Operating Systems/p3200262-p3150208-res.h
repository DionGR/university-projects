#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <pthread.h>
#include <time.h>

#define OPERATORS 3 // Phone Operators 
#define CASHIERS 2 

#define SEATS_PER_ROW 10
#define ROWS_A 10
#define ROWS_B 20
#define PROBABILITY_ZONE_A 0.3

#define COST_A 30
#define COST_B 20

// Amount of tickets range
#define TICKETS_MIN 1 
#define TICKETS_MAX 5 

// Time between client calls
#define CALL_INTERVAL_MIN 1
#define CALL_INTERVAL_MAX 5

// Time required to check if seats are available 
#define OPERATOR_RESPONSE_MIN 5
#define OPERATOR_RESPONSE_MAX 13

// Time required for the cashier to process the tickets
#define CASHIER_RESPONSE_MIN 4
#define CASHIER_RESPONSE_MAX 8

#define PROBABILITY_PAY_SUCESS 0.9

struct s_customer{
    char zone;
    int row;
    int seat;
    int tickets;
    int cost;
    int id;
} customer_default = {-1};

typedef struct s_customer customer;



