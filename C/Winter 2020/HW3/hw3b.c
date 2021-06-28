#include <stdio.h>

int A[3][2]; int B[2][2]; int C[3][2]; int i; int j;


void input()
{
    for(i = 0; i <= 2; i++)
    {
        for(j=0; j <= 1; j++)
        {
            scanf("%d", &A[i][j]);
        }
    }
    for(i = 0; i <= 1; i++)
    {
        for(j=0; j <= 1; j++)
        {
            scanf("%d", &B[i][j]);
        }
    }
}
int main()
{
    input();
    for(i = 0; i <= 2; i++)
    {
        C[i][0] = A[i][0]*B[0][0]+A[i][1]*B[1][0];
        C[i][1] = A[i][0]*B[0][1]+A[i][1]*B[1][1];
        printf("%d %d", C[i][0], C[i][1]);
        if(i < 2) /* Gia kapoio logo 8elei newline se ola ektos apto teleftaio... ftiakste ta test... waste of code space */
        { 
            printf("\n");
        }
    }
    return 0;
}