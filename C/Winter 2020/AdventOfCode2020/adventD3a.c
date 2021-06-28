#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main() {
  int i=0, j = 0;
  int treecount = 0;
  char slope[323][33]={0},c = 0;
  FILE*inp = fopen("inputD3.txt", "r");
  while (fgets(slope[i],sizeof(slope[i]),inp))
  {
    printf("%s",(slope[i++]));
  }
  j=3;
  for(i=0; i<323; i++)
  {
    if(slope[i][j]=='#')
    {
      treecount++;
    }
    j=((j+3)%31);
  }
  printf("\n\n%d\n", treecount);
  fclose(inp);
}