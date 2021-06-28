#include <stdio.h>
#include <stdlib.h> 

int i = 0, flag = 0, y;
char x[12];

/* Epivevaiwnei oti to input einai or8o kai den periexei alla characters. Optional, alla why not. 8a ginotan efkola epishs se enan pinaka/string kai na apokleiei ama to input einai apo ekei anti gia na valw ka8e ypoperiptwsh ksexwrista. */
int decimalcheck() 
{
    i = 0;
    flag = 0;
    while(x[i] != '\0') 
    {  
       if(x[i] == '.'|| x[i] == ',' || x[i] == 'a'|| x[i] == 'b'|| x[i] == 'c'|| x[i] == 'd'|| x[i] == 'e'|| x[i] == 'f'|| x[i] == 'g'|| x[i] == 'h'|| x[i] == 'i'|| x[i] == 'j'|| x[i] == 'k'|| x[i] == 'l'|| x[i] == 'm'|| x[i] == 'n'|| x[i] == 'o'|| x[i] == 'p'|| x[i] == 'q'|| x[i] == 'r'|| x[i] == 's'|| x[i] == 't'|| x[i] == 'u'|| x[i] == 'v'|| x[i] == 'w'|| x[i] == 'x'|| x[i] == 'y'|| x[i] == 'z' || x[i] == 'A'|| x[i] == 'B'|| x[i] == 'C' || x[i] == 'D'|| x[i] == 'E'|| x[i] == 'F'|| x[i] == 'G'|| x[i] == 'H'|| x[i] == 'I'|| x[i] == 'J'|| x[i] == 'K'|| x[i] == 'L'|| x[i] == 'M'|| x[i] == 'N'|| x[i] == 'O'|| x[i] == 'P'|| x[i] == 'Q'|| x[i] == 'R'|| x[i] == 'S'|| x[i] == 'T'|| x[i] == 'U'|| x[i] == 'V'|| x[i] == 'W'|| x[i] == 'X'|| x[i] == 'Y'|| x[i] == 'Z'|| x[i] == '#'|| x[i] == '[' || x[i] == ']' || x[i] == ' '|| x[i] == ';' || x[i] == '"' || x[i] == '/' || x[i] == '+' || x[i] == '='|| x[i] == '_'|| x[i] == '}'|| x[i] == '{'|| x[i] == '`'|| x[i] == '~'|| x[i] == ','|| x[i] == '.'|| x[i] == '<'|| x[i] == '>'|| x[i] == '|'|| x[i] == '!'|| x[i] == '@'|| x[i] == '#'|| x[i] == '$'|| x[i] == '%'|| x[i] == '^'|| x[i] == '&'|| x[i] == '*'|| x[i] == '('|| x[i] == ')'|| x[i] == '?')   
        {
            flag = 1;
            break;
        }
        if(x[i+1] == '-')
        {
            flag = 1;
            break;
        }
        i++;
    } 
}

/* Metatrepei to input mas pou einai char se integer */
int absoluteval()
{
       y = atoi(x);
       y = abs(y);
       printf("The absolute value is %d\n", y);
       return 0;
}

int main()
{
    scanf("%s", x); /* Diavazei string etsi wste na ginei o elegxos sto decimalcheck */
    decimalcheck();
    if(flag == 1)
    {
        do
        {
        printf("Invalid value! Give an integer:");
        scanf("%s", x);
        decimalcheck();
        }while(flag == 1);
    }
    if(flag == 0)
    {
        absoluteval();
    }
}