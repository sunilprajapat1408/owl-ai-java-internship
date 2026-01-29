import java.util.Scanner;

/**
 * Palindrome Checker
 * This program checks whether a given string is a palindrome.
 * A palindrome reads the same forwards and backwards (case-insensitive).
 */
public class PalindromeChecker {
    
    /**
     * Reverses a given string
     * @param str The string to reverse
     * @return The reversed string
     */
    public static String reverseString(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }
    
    /**
     * Checks if a string is a palindrome
     * @param input The string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(String input) {
        // Convert to lowercase for case-insensitive comparison
        String normalized = input.toLowerCase();
        
        // Reverse the normalized string
        String reversed = reverseString(normalized);
        
        // Compare original and reversed strings
        return normalized.equals(reversed);
    }
    
    /**
     * Main method to run the program
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();
        
        // Check if the string is a palindrome
        if (isPalindrome(input)) {
            System.out.println("\nResult: \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("\nResult: \"" + input + "\" is NOT a palindrome.");
        }
        
        scanner.close();
    }
}

