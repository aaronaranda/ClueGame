// Authors: Aaron Aranda and Alejandro Belli

package clueGame;

@SuppressWarnings("serial")
public class BadConfigFormatException extends Exception{

	public BadConfigFormatException() {
		System.out.println("A file reading error was encountered.");
	
	}
	
	public BadConfigFormatException(String filename) {
		System.out.println("An error was encountered when trying to read: " + filename);
	}

    public BadConfigFormatException(char invalidChar) {
        System.out.println("Invalid board cell was found with label: " + invalidChar);
    }
	
    public BadConfigFormatException(int expectedColumnSize, int falseColumnsSize) {
    	System.out.println("Dimension Error: Expected a columns size of " + 
    			expectedColumnSize + ", instead got " + falseColumnsSize + "."
    			);
    }
}
