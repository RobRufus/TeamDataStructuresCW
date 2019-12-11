package wordgame;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestClass {

	
	@Test
	public void duplicateCheck2()
	{
		String rack = "23421";
		
		char[] chars = rack.toCharArray();
		
		Map<Character, Integer> charMap = new HashMap<>();
		
		for(char c : chars)
		{
			if(charMap.containsKey(c))
			{
				System.out.print(c);
				
			}
			else 
			{
				System.out.print(1);
				charMap.put(c, 1);
			}
		}
		
		
	}
	
	
}

/* private boolean checkDouble(int col, int row) {
	board.peekAtPosition(col, row);

	return false;
} */

/*//Check DOWN words

		// Place each row of characters into a string, then into an array of strings that holds every row
		
		for (int i = 0; i < GameBoard.BOARD_DIMENSIONS; i++) {
			for (int j = 0; j < GameBoard.BOARD_DIMENSIONS; j++) {
		
				String charAtLocation = tempBoard.peekAtPosition(i, j);
				stringAcross+= charAtLocation;
				
			}
			
			rowStringArray[i] = stringAcross;
			stringAcross = "";
			
		}
		
		for (int i = 0; i < rowStringArray.length; i++) {
			
			System.out.println(rowStringArray[i]);
			
		}
		
		for (int i = 0; i < rowStringArray.length; i++) {
			
			// Reset the check string
			checkStringAcross = "";
			
			for (int j = 0; j < rowStringArray[i].length(); j++) {
				
				char character = rowStringArray[i].charAt(j);
				String characterString = "" + character;
				
				
				// If character is equal to an empty space
				if (characterString.equals("-") || characterString.equals("+")) {
					
					// See if held string is empty, if so, do nothing
					if (checkStringAcross.length() == 0 || (checkStringAcross.length() == 1 && playDirection.equals("DOWN"))) { 
						
						;;
					
					// If a word, validate word
					} else {
						
						// If a valid word, add to valid word array, reset the string.
						if (IO.searchWord(checkStringAcross)) {
							
							validWords.add(checkStringAcross);
							System.out.println("VALID WORD ACROSS" + checkStringAcross);
							checkStringAcross = "";
						
						// If Invalid Word, change validPlay to be false.
						} else {
							
							System.out.println("INVALID WORD ACROSS ");
							checkStringAcross = "";
							validPlay = false;
							
						}
						
					}
				
				// If a character in space, add to string
				} else {
					
					checkStringAcross+= characterString;
					System.out.println(checkStringAcross);
					
					// If at the end of the row, check the word
					
					if (j == GameBoard.BOARD_DIMENSIONS - 1) {
						
							if (IO.searchWord(checkStringAcross)) {
							
							validWords.add(checkStringAcross);
							System.out.println("VALID WORD ACROSS" + checkStringAcross);
							checkStringAcross = "";
						
						// If Invalid Word, change validPlay to be false.
						} else {
							
							System.out.println("INVALID WORD ACROSS ");
							checkStringAcross = "";
							validPlay = false;
							
						}
					}
										
				} 
					
			}
			
		}	*/

/*
private boolean duplicateCheck(String rackInput)
{
	String rack = rackInput;
	
	char[] chars = rack.toCharArray();
	
	Map<Character, Integer> charMap = new HashMap<>();
	
	for(char c : chars)
	{
		if(charMap.containsKey(c))
		{
			return true;
		}
		else 
		{
			charMap.put(c, 1);
		}
	}
	
	return false;
}
*/

