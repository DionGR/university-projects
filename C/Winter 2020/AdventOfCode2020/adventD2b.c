#include <stdio.h>
#include <stdlib.h>
int main()
{
    int totcount = 0, i, min, max;
    char letter, pass[21];
    FILE *inp = fopen("inputD2.txt", "r");
    for(i = 0; i < 1000; i++)
    {
        fscanf(inp, "%d -%d %c: %s", &max, &min, &letter, pass);
        if((pass[max-1] == letter && pass[min-1] != letter) || (pass[max-1] != letter && pass[min-1] == letter))
        {
            totcount++;
        }
    }
    printf("%d", totcount);
    fclose(inp);
    return 0;
}