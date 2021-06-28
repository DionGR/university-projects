#include <stdio.h>
#include <string.h>

#define SIZE 123

char asciiTable[SIZE], firstTable[SIZE];
unsigned int toCount = 0U;

void adder()
{
    for(unsigned int i = 0; i < SIZE; i++)
    {
        if(firstTable[i] == 1)
        {
            toCount++;
        }
    }
}

int main(void)
{ 
    char c = 0U, j = 0U;  
    unsigned int dn = 1U;
    FILE *inp = fopen("input.txt", "r");

    while((c = getc(inp)) != EOF)
    {
        if(c == '\n' && j != '\n')
        {
            if (dn == 1)
            {
                dn = 0;
            }
            else
            {
                for(int m = 0; m < SIZE; m++)
                {
                    if(asciiTable[m] != firstTable[m])
                    {
                        firstTable[m] = 0;
                    }
                }
            }
            memset(asciiTable, 0, SIZE);
        }else if (j == '\n' && c == '\n')
        {
            adder(toCount);
            memset(firstTable, 0, SIZE);
            dn = 1;
        }
        if(c !='\n')
        {
            if(dn == 1 && firstTable[c] == 0)
            {
                firstTable[c] = 1;
            }
            else if(dn == 0)
            {
                asciiTable[c] = 1;
            }
        }
        j = c;
    }
    for(int m = 0; m < SIZE; m++)
    {
        if(firstTable[m] == 1)
        {
            printf("%d\n", firstTable[m]);
        }
    }
    adder(toCount);
    printf("Sum: %d\n", toCount);
    fclose(inp);
    return 0;
}