#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
  int i; int g;
  float S; float grade; float noStudents; float noGrades; float gradeAvg; float max = -1.00; float min = 11.00; float avg = 0.00;
  char **nmlist; char **srnmlist; char *nmax = malloc(33); char *srnmax = malloc(33); char *nmin = malloc(33); char *srnmin = malloc(33);
  FILE *fwrite;
  FILE *fp;
  fp = fopen(argv[2], "r"); /* Open read file and check for errors */
  if (fp == NULL)
  {
    printf("Error!");
    exit(-1);
  }
  fwrite = fopen(argv[4], "w"); /* Open write file*/
  fscanf(fp,"%f %f", &noStudents, &noGrades); /* Read the No. of noStudents + grades per student */
  /* Allocates the memory required for 1 array per student name and surname */
  nmlist = malloc(sizeof(char*)*noStudents);
  srnmlist = malloc(sizeof(char*)*noStudents);
  /* Allocates the memory required for the name and surname of every student */
  for(i=0; i < noStudents; i++)
  {
    nmlist[i] = (char*)malloc(sizeof(char)*33); /* 33 epeidh einai 32 chars + /0*/
    srnmlist[i] = (char*)malloc(sizeof(char)*33);
  }
  for(i=0; i < noStudents; i++)
  {
    fscanf(fp,"%s %s", nmlist[i], srnmlist[i]); /* Takes the name and surname */
    S = 0;
    grade = 0;
    /* Takes all the grades of the student and sums them */
    for(g = 0; g < noGrades; g++)
    {
      fscanf(fp,"%f", &grade);
      S += grade;
    }
    gradeAvg = S/noGrades; /* Grade average for the student*/
    if(gradeAvg > max) /* Max check */
    {
      max = gradeAvg;
      memcpy(nmax, nmlist[i], 33);
      memcpy(srnmax, srnmlist[i], 33);
    }
    if(gradeAvg < min)  /* Min check */
    {
      min = gradeAvg;
      memcpy(nmin, nmlist[i], 33);
      memcpy(srnmin, srnmlist[i], 33);
    }
    avg += gradeAvg;
    fprintf(fwrite, "Name: %s %s avg %.2f\n", nmlist[i], srnmlist[i], gradeAvg);  /* Prints the name, surname nad the avg grade of the student */
  }
  fprintf(fwrite,"max: %s %s %.2f\n", nmax, srnmax, max); /* Prints max name, surname and grade */
  fprintf(fwrite,"min: %s %s %.2f\n", nmin, srnmin, min); /* Prints min name, surname and grade */
  fprintf(fwrite,"average: %.2f\n", avg/noStudents); /* Prints the average grade of the class */
  /* File closing + memory freeing */
  for (i = 0; i < noStudents; i++)
  {
    free(nmlist[i]);
    free(srnmlist[i]);
  }
  free(nmlist);
  free(srnmlist);
  free(nmax);
  free(srnmax);
  free(nmin);
  free(srnmin);
  fclose(fwrite);
  fclose(fp);
  return 0;
}