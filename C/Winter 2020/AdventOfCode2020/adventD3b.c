#include <stdio.h>
#include <stdlib.h>

#define SIZE 10050

int main() {
  unsigned int i, j = 0;
  unsigned int treecount = 0;
  char slope[SIZE], c = 0;
  FILE * inp = fopen("inputD3.txt", "r"); // Open input file

  for (i = 0; i < SIZE;) { // Assign all the characters into a 1D, one line array of chars
    if ((c = getc(inp)) == EOF) { // This has been tested and is doing it properly!
      break;
    }
    if (c != '\n' && c != ' ') {
      slope[i++] = c;
    }
    if (c == EOF) {
      break;
    }
  }
  i = 34;
  do
  {
    if (i <= SIZE) {
      if (slope[i] == '#') {
        printf("%c %d\n", slope[i], i);
        treecount++;
      }
    }else
    {
      break;
    }
    if((i+3)%31 < 3)
    { 
      i += 3;
    }else
    {
      i+= 34;
    }
  } 
  while((i + 34) < SIZE-1);
  printf("%d", treecount);
  fclose(inp);
}