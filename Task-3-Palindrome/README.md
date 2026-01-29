# Task 3: Palindrome Checker

## Program Logic

This program checks whether a given string is a palindrome. A palindrome is a word, phrase, or sequence that reads the same forwards and backwards.

### Steps:

1. **Input**: Accept a string from the user
2. **Normalize**: Convert the string to lowercase for case-insensitive comparison
3. **Reverse**: Create a reversed version of the normalized string using a separate method
4. **Compare**: Check if the original normalized string equals the reversed string
5. **Output**: Display whether the string is a palindrome or not

### Key Methods:

- `reverseString(String str)`: Reverses the input string character by character
- `isPalindrome(String input)`: Performs case-insensitive comparison between original and reversed strings

## Example Input/Output

### Example 1:
```
Enter a string to check: racecar

Result: "racecar" is a palindrome.
```

### Example 2:
```
Enter a string to check: Hello

Result: "Hello" is NOT a palindrome.
```

### Example 3:
```
Enter a string to check: Madam

Result: "Madam" is a palindrome.
```

### Example 4:
```
Enter a string to check: Java

Result: "Java" is NOT a palindrome.
```

## How to Run

1. Compile the Java file:
   ```bash
   javac PalindromeChecker.java
   ```

2. Run the program:
   ```bash
   java PalindromeChecker
   ```

3. Enter a string when prompted and press Enter.

