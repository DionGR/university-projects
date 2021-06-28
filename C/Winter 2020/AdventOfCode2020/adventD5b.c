#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define lineSize 12 /* Chars per row. 10 chars +\0 + \n */
#define lines 815 /* All lines of the file */
#define sizeN1 7 
#define sizeN2 10

unsigned int i, k, j, tmp;
int ID[lines];
char line[lineSize]; /* Treat each line (String) as an array of chars */

int binarySearch() /* Function that converts the line (i.e. BFBBFFFLRR) into an ID */
{
    for (i = 0; i < sizeN1; i++) /* Treat the first 7 chars as a 7bit unsigned decimal and convert Binary >> Decimal */
    {
        if (line[i] == 'B') 
        {
            ID[k] += pow(2, sizeN1-i-1); 
        }
    }
    ID[k] = ID[k]*8;
    for (i = i; i < sizeN2; i++) /* Treat the remaining 3 chars as an 3bit unsigned decimal and convert Binary >> Decimal */
    {
        if (line[i] == 'R') 
        {
            ID[k] += pow(2, sizeN2-i-1);
        }
    }
    return ID[k]; /* Return the ID number (i.e. 700) */
}


int main(void)
{
    FILE *inp = fopen("inputD5.txt", "r"); /* Import Task File */
    for(k = 0; k < lines; k++) /* Loop for every single line */
    {
        fgets(line, lineSize,inp); /* Grabs the whole line (i.e. BFBBFFFLRR) */
        binarySearch();
    }
    for (k = 0; k < lines; k++) /* Bubblesort for the IDs */
    {
        for (j = 0; j < lines-k-1; j++) 
        {
            if (ID[j] > ID[j+1]) 
            {
                tmp = ID[j];
                ID[j] = ID[j+1];
                ID[j+1] = tmp;
            }
        }
    }
    for (k = 0; k < lines; k++) /* Look and printf for your seat ID + the max ID from beforehand */
    {
        if(ID[k] + 2 == ID[k+1] )
        {
            printf("The Maximum ID is: %d\n", ID[lines-1]);
            printf("Your seat ID is: %d\n", ID[k]+1);
        }
    }
    fclose(inp);
    return 0;
}