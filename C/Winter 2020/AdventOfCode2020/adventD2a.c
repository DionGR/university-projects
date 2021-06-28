#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
    int letters, incount, totcount = 0, i, j, k, m;
    char letter, pass[21];
    FILE *inp = fopen("inputD2.txt", "r");

    for(i = 0; i < 1000; i++)
    {
        incount = 0;
        fscanf(inp, "%d -%d %c: %s", &k, &j, &letter, pass);
        letters = strlen(pass);
        for(m = 0; m < letters; m++)
        {
            if(pass[m] == letter)
            {
                incount++;
            }
        }
        if(incount >= k && incount <= j)
        {
            totcount++;
        }
    }
    printf("%d", totcount);
    fclose(inp);
}