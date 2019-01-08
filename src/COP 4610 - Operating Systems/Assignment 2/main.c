/* A C program that uses the fork() system call to calculate the Fibonacci 
   sequence of a number and print the result in the child process. */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <ctype.h>
#include <string.h>
#include <sys/wait.h>

// global variable
int value = 0;

// function prototypes
void printFibonacci(int value);
int fibSequence(int value);
void validString(char str[]);

int main() {  
    // char array for user input
    char str[10];
    
    // prompt for user input
    printf("Please enter an integer: ");
    scanf("%s", &str);
    
    // check input string for validity and store it in 'value'
    validString(str);    
}

// prints the Fibonacci sequence in the child process
void printFibonacci(int value) {
    int pid = fork();    
    int i;
    
    if(pid == 0) {  // child process        
        printf("The Fibonacci sequence of %d is: ", value);
        for(i = 0; i < value; i++) {
            printf("%d ", fibSequence(i));            
        }
    }    
    else {  // wait for the child process to complete before exiting
        wait(NULL);
        exit(0);
    }         
}

// recursive function to calculate the Fibonacci sequence of a value
int fibSequence(int value) {
    // base case #1
    if(value == 0) {
        return 0;
    } 
    // base case #2
    else if(value == 1) {
        return 1;
    }
    else {
        return(fibSequence(value - 1) + fibSequence(value - 2));
    }
} 

// validates the input string for positive integer value
void validString(char str[]) {
    int length, i;
    length = strlen(str);
    
    // scan the input string for alpha characters
    for (i = 0; i < length; i++) {
        // input string is not numerical, program exits 
        if (!isdigit(str[i])) {
            printf("Invalid input!");
            exit(0);
        }
    }
    // input string is numerical, convert it to integer and store in 'value'
    value = atoi(str);
    
    // 'value' is negative, program exits    
    if(value < 0) {
        printf("Input is a negative number!");
        exit(0);
    }    
    
    // 'value' is valid and non-negative, find Fibonacci sequence
    else {
        printFibonacci(value);
    }    
}


