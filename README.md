## TestTaskCalculator

### Description:
**Console application "Calculator"**  
The application reads from the console the strings entered by the user, numbers, 
arithmetic operations carried out between them and displays the result of their execution to the console.  

Method takes a string with an arithmetic expression between two numbers and returns a string with the result of their execution.  

### Requirements:
1. Calculator can perform addition, subtraction, multiplication and division operations with two numbers:  
   `a + b, a - b, a * b, a / b`  
   > Do not take arithmetic priority of operations, such as `(), *, /`
   
   **Data is transferred in one line**
2. Calculator can work with both Arabic (1, 2, 3, 4, 5...) and Roman (I, II, III, IV, V...) numbers  
3. Calculator should accept numbers as input, no more. At the output, the numbers are not limited in size and can be any.  
4. Calculator can only work with integers.  
5. Calculator only works with Arabic or Roman numerals at the same time, when the user enters a string like  
   `3 + II`, the calculator throws an exception and stops working.  
6. When entering Roman numerals, the answer is displayed in Roman numerals, respectively, when entering Arabic - the answer is in Arabic  
7. When the user enters invalid numbers, the application throws an exception and exits  
8. When the user enters a string that does not match one of the above arithmetic operations, the application throws an exception and exits  
9. The result of the division operation is an integer, the remainder is discarded  
10. The result of the calculator with Roman numerals can only be positive numbers, if the result of the work is less than one, an exception is thrown  

**Use some patterns**
- strategy
- interpreter  
