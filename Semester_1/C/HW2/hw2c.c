#include <stdio.h>

int intg1; int numerator1; int denominator1; int intg2; int numerator2;  int denominator2; int tmp1, tmp2;
char sign; 

int input()
{
    scanf("%d\n%d\n%d\n%d\n%d\n%d\n%c", &intg1, &numerator1, &denominator1, &intg2, &numerator2, &denominator2, &sign);
}

int tmpA() 
{
    tmp1 = (intg1 * denominator1 + numerator1) * denominator2;
    tmp2 = (intg2 * denominator2 + numerator2) * denominator1;
}

int tmpB()
{
    tmp1 = (intg1 * denominator1 + numerator1); 
    tmp2 = (intg2 * denominator2 + numerator2); 
}


int main()
{
    input();
    switch(sign)
    {
        case '+':
            tmpA();
            printf("%d %d %d", (tmp1 + tmp2) / (denominator1 * denominator2), (tmp1 + tmp2) % (denominator1 * denominator2), denominator1 * denominator2);
            break;
        case '-':
            tmpA();
            printf("%d %d %d", (tmp1 - tmp2) / (denominator1 * denominator2), (tmp1 - tmp2) % (denominator1 * denominator2), denominator1 * denominator2);
            break;
        case '*':
            tmpB();
            printf("%d %d %d", (tmp1 * tmp2) / (denominator1 * denominator2), (tmp1 * tmp2) % (denominator1 * denominator2), denominator1 * denominator2);
            break;
        case '/':
            tmpB();
            printf("%d %d %d", (tmp1 * denominator2) / (denominator1 * tmp2),(tmp1 * denominator2) % (denominator1 * tmp2), denominator1 * tmp2);
            break;
    }
}