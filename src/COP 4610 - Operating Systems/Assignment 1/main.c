// 7495 
// Assignment #1
// COP 4610 - CRN 80604
 
/* A C program that uses the fork() system call to calculate the Factorial of
   a number in the child process and print the result in the parent process. */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <ctype.h>
#include <string.h>
#include <sys/wait.h>

// global variables
int result = 0;
int value = 0;

// function prototypes
void procFactorial(int value);
int calcFactorial(int value);

int main() {    
   
    // prompt for user input
    printf("Please enter an integer: ");
    
    // if value is an integer
    if(scanf("%d", &value) == 1) {
        // value is out of range, program exits    
        if(value < 1 || value > 5) {
            printf("Results are out of range!");
            exit(0);
        } 
        // value is within range, find factorial of value
        procFactorial(value);
    }
    // if value is not an integer, error
    else {
        printf("Invalid input!");
        exit(0);
    }   
}

// calculate the factorial and prints the result to the parent process
void procFactorial(int value) {
    int pid = fork();    
    
    if(pid == 0) {  // child process
        result = calcFactorial(value);
        exit(result);
    }    
    else {  // print the output in the parent process
        wait(&result);
        result = WEXITSTATUS(result);
        
        printf("Factorial of %d = %d", value, result); 
        exit(0);
    }         
}

// recursive function to calculate the factorial of a value
int calcFactorial(int value) {
    if(value >= 1) {
        return value * calcFactorial(value - 1);
    }
    else {  // base case
        return 1;
    }
}

