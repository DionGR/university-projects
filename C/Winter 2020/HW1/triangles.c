#include <stdio.h>

int i;
int z;

int main()
{
  for(i = 1; i <= 6; i++)
  {
      for(z = 1; z <= i; z++)
      {
          printf("*");
      }
        printf("\n");
  }
} 