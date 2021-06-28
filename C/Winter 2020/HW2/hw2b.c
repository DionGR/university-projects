#include <stdio.h>

int main()
{
    int height; int stars; int spaces; int i; int y; int j; int m; 
    do
    {
        scanf("%d", &height);
        if(height > 20 || height < 4)
        {
            printf("Height is not correct!");
        }
    }while(height > 20 || height < 4);
    for(i = 1; i < height; i++)
    {
        stars = i * 2 - 1;
        spaces = i + height - stars; 
        for(y = 1; y < spaces - 1; y++)
        {
            printf(" ");
        }
        for(j = 0; j < stars; j++)
        {
            printf("*");
        }
        printf("\n");
    }
    for(m = 1; m <= (stars/2); m++)
    {
        printf(" ");
    }
    printf("*\n");
}
