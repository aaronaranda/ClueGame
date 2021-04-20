package clueGame;

@SuppressWarnings("serial")
public class BadConfigFormatException extends Exception {
    public BadConfigFormatException() {
        System.out.println("A file reading error was encountered.");        
    }

    public BadConfigFormatException(String filename) {
        System.out.println(
                "An error was encountered when trying to read: " + filename 
                );
    }
    
    public BadConfigFormatException(Character invalidChar) {
        System.out.println(
                "Invalid board cell was found with label: " + invalidChar
                );
    }

    public BadConfigFormatException(int expectedColSize, int falseColSize, int row) {
        System.out.println(
                "Dimension Error: Expected a column size of " + expectedColSize
                + ", instead got " + falseColSize + " at row " + row + "."
                );
    }
}
