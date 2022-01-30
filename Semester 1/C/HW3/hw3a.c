#include <stdio.h>
#include <stdlib.h>

int size; int i; int j; int num = 0; int **arDet; int a; int b; int c; int d; int e; int f; int g; int h;

void input()
{
    do
    {
        scanf("%d", &size);
    }while(size > 3 || size < 2);
    arDet = malloc(sizeof(arDet[0])*size);  /* Kanei allocate to mege8os tou pinaka AKA input size sthn mnhmh tou gia ta rows */
    for(i = 0 ; i < size; i++)
    {
        arDet[i] = malloc(sizeof(arDet[i][0]) * size); /* Kanei allocate to mege8os tou pinaka AKA input size sthn mnhmh tou gia ta columns */
        for(j = 0 ; j < size; j++)
        {    
            scanf("%d", &arDet[i][j]);  /* Diavazei enan ari8mo kai elegxei ean vrisketai sto [-9,9]. An oxi, termatizei antikanonika me return -1. */
            if(arDet[i][j] < -9 || arDet[i][j] > 9)
            {
                j = size;
                i = size; /* Gia kapoio logo to break den espage thn synarthsh kai ksanaekane epanalhpsh... */
                num = 1;
                break;
            }
        }
    }
}

int main()
{
    input();
    if(size == 2 && num == 0)
    {
        printf("%d", arDet[0][0]*arDet[1][1] - arDet[0][1]*arDet[1][0]); 
        return 0;
    }else if(size == 3 && num == 0)
    {
       printf("%d", arDet[0][0]*(arDet[1][1]*arDet[2][2] - arDet[2][1]*arDet[1][2]) - arDet[0][1]*(arDet[1][0]*arDet[2][2] - arDet[1][2]*arDet[2][0]) + arDet[0][2]*(arDet[1][0]*arDet[2][1] - arDet[2][0]*arDet[1][1]));
        return 0;
    }else
    {
        return -1;
    }
}