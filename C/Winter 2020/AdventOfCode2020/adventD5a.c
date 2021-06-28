#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define lineSize 12 // Chars per row. 10 chars +\0 + \n
#define lines 815 // All lines of the file
#define sizeN1 7 
#define sizeN2 10

unsigned int i, max, ID;
char line[lineSize]; // Treat each line (String) as an array of chars

int binarySearch() // Function that converts the line (i.e. BFBBFFFLRR) into an ID
{
    for (i = 0; i < sizeN1; i++) // Treat the first 7 chars as a 7bit unsigned decimal and convert Binary >> Decimal
    {
        if (line[i] == 'B') 
        {
            ID += pow(2, sizeN1-i-1); 
        }
    }
    ID = ID*8;
    for (i; i < sizeN2; i++) // Treat the remaining 3 chars as an 3bit unsigned decimal and convert Binary >> Decimal
    {
        if (line[i] == 'R') 
        {
            ID += pow(2, sizeN2-i-1);
        }
    }
    return ID; // Return the ID number (i.e. 700)
}


int main(void)
{
    FILE *inp = fopen("inputD5.txt", "r"); // Import Task File
    for(int k = 0; k < lines; k++) // Loop for every single line
    {
        ID = 0; 
        fgets(line, lineSize,inp); // Grabs the whole line (i.e. BFBBFFFLRR)
        binarySearch();
        if(ID > max) // Max keeper IF
        {
            max = ID;
        }
    }
    printf("%d", max);
    fclose(inp);
}