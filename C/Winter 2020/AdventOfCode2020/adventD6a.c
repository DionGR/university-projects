#include <stdio.h>
#include <string.h>

#define SIZE 123

char asciiTable[SIZE];
unsigned int toCount = 0U;

void adder()
{
    for(unsigned int i = 0; i < SIZE; i++)
    {
        if(asciiTable[i] == 1)
        {
            toCount++;
        }
    }
}

int main(void)
{ 
    char c = 0U, j = 0U; 

    FILE *inp = fopen("inputD6.txt", "r");

    while((c = getc(inp)) != EOF)
    {
        if (j == '\n' && c == '\n')
        {
            adder(toCount);
            memset(asciiTable, 0, sizeof(asciiTable));
        }
        if (asciiTable[c] == 0 && c !='\n')
        {
            asciiTable[c] = 1;
        }
        j = c;
    }
    adder(toCount);
    printf("Sum: %d\n", toCount);
    fclose(inp);
    return 0;
}