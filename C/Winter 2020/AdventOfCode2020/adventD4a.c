#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define lineSize 15

void vibecheck(char *string, int *count) 
{
    if(strstr(string, "iyr"))
	{
		(*count)++;
	}
    if(strstr(string, "byr"))
	{
		(*count)++;
	}
    if(strstr(string, "eyr"))
	{
		(*count)++;
	}
    if(strstr(string, "hgt"))
	{
		(*count)++;
	}
    if(strstr(string, "hcl"))
	{
		(*count)++;
	}
    if(strstr(string, "ecl"))
	{
		(*count)++;
	}
    if(strstr(string, "pid"))
	{
		(*count)++;
	}
}

int main(void)
{
    int i = 0U;
    int maxR = 127U, minR = 0U, midR = 63;
    char line[lineSize];

    FILE *inp = fopen("inputD4.txt", "r");
    //do
    //{
        fgets(line, lineSize,inp);
        for(i; i < 7; i++)
        {
            printf("%c", &line[i]);
            if(line[i] == 'F')
            {
                maxR = midR;
                midR = ((maxR+minR)-1)/2;
            }else if(line[i] == 'F')
            {
                minR = midR;
                midR = ((maxR+minR)+1)/2;
            }
        }
        printf("%d", midR);


        //c = fgetc(inp);
        //j = c;
        //ungetc(c, inp);
    //}
    //while(j != EOF);

    fclose(inp);
}