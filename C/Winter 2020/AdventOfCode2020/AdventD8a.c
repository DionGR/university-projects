#include <stdio.h>
#include <string.h>

#define LINES 641

int main() 
{
    unsigned int check[LINES] = {0}; // Has the value been checked? 1 or 0
    char inst[LINES][4];
    int acc[LINES], tAcc = 0, i;

    FILE*inp = fopen("inputD8.txt", "r");

    for(i = 0; i < LINES; i++) // Fill 2 parallel arrays. One with the instruction, one with the accumulator.
    {
        fscanf(inp, "%s %d", inst[i], &acc[i]);
    }
    i = 0;
    while(check[i] != 1 && i < LINES) // Start checking till a value has already been checked
    {
        check[i] = 1;
        if(!strcmp(inst[i], "acc")) // Acc addition sequence
        {
            tAcc += acc[i];
            i++;
        }else if(!strcmp(inst[i], "jmp")) // Jump sequence
        {
            i += acc[i];
        }else // No operation
        {
            i++;
        }
    }
    printf("%d", tAcc);
    fclose(inp);
}