#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n1, i, j, k, entry[200];
    FILE *inp = fopen("inputD1.txt", "r");
    for(i = 0; i < 200; i++)
    {
        fscanf(inp, "%d", &entry[i]);
    }
    for(i = 0; i < 200; i++)
    {
        for(j = i; j < 200; j++)
        {
            for(k = j; k < 200; k++)
            {
                if(entry[i] + entry[j] + entry[k] == 2020)
                {
                    printf("%d\n", entry[i]*entry[j]*entry[k]);   
                    return 0;
                }
            }
        }
    }
    fclose(inp);
}