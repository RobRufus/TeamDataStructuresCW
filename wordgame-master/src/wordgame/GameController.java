//
//
// PLEASE NOTE, FOR THIS CONTEXT, ONE LETTER WORDS WILL NOT BE CHECKED 
//
//

package wordgame;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import wordgame.io.DictionaryInput;

public class GameController implements Controller {

	private Rack rack; 
	private GameBoard board;
	private DictionaryInput IO;
	private Map<String, Integer> scoreMap;
	
	// Constructor, creates a rack object, parsed a board object
	public GameController(GameBoard board) throws IOException {
		rack = new Rack();
		this.board = board;
		IO = new DictionaryInput();
		
		scoreMap = new HashMap<>();
		int[] letterScore = {1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 1, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3};
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		for(int i=0; i< letterScore.length; i++) {
			String character = "" + alphabet.charAt(i); 
			scoreMap.put(character, letterScore[i]);
		}
	}
	
	@Override
	public String refillRack() {
		
		// Calls the fillRack method and returns a string which represents the player rack.
		
		String filledRack = rack.fillRack();
		return filledRack;
		
	}
	
	@Override
	public String gameState() {
		
		// Calls the getBoard method and returns a string which represents the board
		
		String boardString = board.getBoard();
		
		return boardString;
		
	}
 
	@Override
	public String play(Play play) {
		//creating a GameBoard to be modified and then returned
		GameBoard returnBoard = board;
		
		//modifiable X & Y coordinates of a tracked position in the board, Y = column, X = Row.
		int positionX = Integer.parseInt(play.cell().substring(1, 2));					//convert the 2nd character of the location string to an integer
		int positionY = Integer.parseInt(letterToInt(play.cell().substring(0, 1)));		//convert the 1st character of the location string to a number based on the letter then convert to integer
		int positionIncrement = 0;														// variable to track which direction we are looking at
		int positionNoneIncrement = 0;													// variable to track which direction we are looking at
		

		//modifiable X & Y coordinates of a tracked position in the board set based on direction, Y = column, X = Row.
		switch (play.dir()) {
		case ACROSS:
				positionIncrement = positionY;			//set which value we will increment to check for off board error
				positionNoneIncrement = positionX;		
			break;
		case DOWN:
			positionNoneIncrement = positionY;			//set which value we will increment to check for off board error
			positionIncrement = positionX;				
		}	
		

		//Place some tiles horizontally or vertically on a sequence of unoccupied cells in the game board.
		
		//if statement to check for out of board dimensions
		if ((positionIncrement + play.letterPositionsInRack().length()) <= returnBoard.BOARD_DIMENSIONS && positionNoneIncrement <= returnBoard.BOARD_DIMENSIONS)
	    {
		
			//do for each letter to be placed on the board
			for(int i = 0; i < play.letterPositionsInRack().length(); i++)
			{
		 
				//System.out.println("CHECKING CELL " + "(" + intToLetter(positionY) + " , " + (positionX-1) + ")"  );/////////////////////////////////////////////////////////////////////////////////
				
				//check if position empty, if so proceed
				if(returnBoard.peekAtPosition(positionY, positionX-1) == "-" || returnBoard.peekAtPosition(positionY, positionX-1) == "+")
				{

			    
			        	//set the board space at the position we are currently viewing to be the current letter selected from the rack.
						returnBoard.setPosition(positionX-1, positionY, rack.getLetter(Integer.parseInt(play.letterPositionsInRack().substring(i, i+1))-1)); 
			    
			    
						//look at next cell in game board
						switch (play.dir()) {
						case ACROSS:
							positionY++;
							break;
						case DOWN:
							positionX++;
						}	
				}
				else {

						//throws index out of bounds exception for off board play
						System.out.println("Invalid Play, overlapping letter at position " + "(" + intToLetter(positionY) + " , " + (positionX-1) + ")"  );
						
				
						//look at next cell in game board
						switch (play.dir()) {
						case ACROSS:
							positionY++;
							break;
						case DOWN:
							positionX++;
						}
				}
			
		    }
		}
		else								
	    {
	    	//print exception when error occurs
			System.out.println("Play exceeds board dimentions");
	    } 
		
		//return the game board as a string object
		return returnBoard.getBoard();
	}

	@Override
	public String calculateScore(Play play) {
		
		int score = 0;
		int posX = Integer.parseInt(play.cell().substring(1, 2));
		posX -= 1;
		int posY = Integer.parseInt(letterToInt(play.cell().substring(0, 1)));

		String s = play.letterPositionsInRack();
		String tmpScore = "null";
		String str = "null";
		

		
		for(int i = 0; i < s.length(); i++) {
			
			str = rack.getLetter(i);
			
			if(board.checkDouble(posX, posY)) {
				
				score = score + (scoreMap.get(str)*2);
				switch (play.dir()) {
				case ACROSS:
					posY++;
					break;
				case DOWN:
					posX++;
					
				}
				
			} else {
				
				score = score + scoreMap.get(str);
				switch (play.dir()) {
				case ACROSS:
					posY++;
					break;
				case DOWN:
					posX++;
					
				}
			}


		}
		tmpScore = score + "";
		return "Points to be scored... " + tmpScore;
		
	}

	public String checkValidity(Play play) {
		
		// Gameboard to be used for checking locations
		
		GameBoard tempBoard = board;
		boolean validPlay = true; // If play is valid or not
		ArrayList<String> validWords = new ArrayList<>(); // List of valid words
		String stringAcross = ""; // String for each row
		String checkStringAcross = ""; // String for words on each row to be validated
		String[] rowStringArray = new String[10]; // Array to hold each row's string
		String stringDown = ""; // String for each column
		String checkStringDown = ""; // String for words on each column to be validated
		String[] columnStringArray = new String[10]; // Array to hold each row's string
			
		// Check ACROSS words
		
		// Place each row of characters into a string, then into an array of strings that holds every row
		
		for (int i = 0; i < GameBoard.BOARD_DIMENSIONS; i++) {
			for (int j = 0; j < GameBoard.BOARD_DIMENSIONS; j++) {
		
				String charAtLocation = tempBoard.peekAtPosition(j, i);
				stringAcross+= charAtLocation;
				
			}
			
			rowStringArray[i] = stringAcross;
			stringAcross = "";
			
		}
		
		for (int i = 0; i < rowStringArray.length; i++) {
			
			// Reset the check string
			checkStringAcross = "";
			
			for (int j = 0; j < rowStringArray[i].length(); j++) {
				
				char character = rowStringArray[i].charAt(j);
				String characterString = "" + character;
				
				
				// If character is equal to an empty space
				if (characterString.equals("-") || characterString.equals("+")) {
					
					// See if held string is empty or one character, empty string
					if (checkStringAcross.length() == 0 || checkStringAcross.length() == 1) { 
						
						checkStringDown = "";
					
					// If a word, validate word
					} else {
						
						// If a valid word, add to valid word array, reset the string.
						if (IO.searchWord(checkStringAcross)) {
							
							validWords.add(checkStringAcross);
							checkStringAcross = "";
						
						// If Invalid Word, change validPlay to be false.
						} else {
							
							System.out.println("\nInvalid Word Across:" + checkStringAcross);
							checkStringAcross = "";
							validPlay = false;
							
						}
						
					}
				
				// If a character in space, add to string
				} else {
					
					checkStringAcross+= characterString;
					
					// If at the end of the row, check the word
					
					if (j == GameBoard.BOARD_DIMENSIONS - 1) {
						
							if (IO.searchWord(checkStringAcross)) {
							
							validWords.add(checkStringAcross);
							checkStringAcross = "";
						
						// If Invalid Word, change validPlay to be false.
						} else {
							
							System.out.println("\nInvalid Word Across:" + checkStringAcross);
							checkStringAcross = "";
							validPlay = false;
							
						}
					}
										
				} 
					
			}
			
		}
		
		// Check DOWN Words
		
		// Place each row of characters into a string, then into an array of strings that holds every row
		
		for (int i = 0; i < GameBoard.BOARD_DIMENSIONS; i++) {
			for (int j = 0; j < GameBoard.BOARD_DIMENSIONS; j++) {
				
				String charAtLocation = tempBoard.peekAtPosition(i, j);
				stringDown+= charAtLocation;
						
			}
					
			columnStringArray[i] = stringDown;
			stringDown = "";
					
		}
		
		for (int i = 0; i < columnStringArray.length; i++) {
			
			// Reset the check string
			checkStringDown = "";
			
			for (int j = 0; j < columnStringArray[i].length(); j++) {
				
				char character = columnStringArray[i].charAt(j);
				String characterString = "" + character;
				
				// If character is equal to an empty space
				if (characterString.equals("-") || characterString.equals("+")) {
					
					// See if held string is empty or one character, empty string
					if (checkStringDown.length() == 0 || checkStringDown.length() == 1) {
						
						checkStringDown = "";
					
					// If a word, validate word
					} else {
						
						// If a valid word, add to valid word array, reset the string
						if (IO.searchWord(checkStringDown)) {
							
							validWords.add(checkStringDown);
							checkStringDown = "";
						
						// If invalid word, change validPlay to be false
						} else {
							
							System.out.println("\nInvalid Word Down:" + checkStringDown);
							checkStringDown = "";
							validPlay = false;
							
						}
						
					}
				
				// If character in space, add to string	
				} else {
					
					checkStringDown+= characterString;
					
					// If at end of column, check for word
					
					if (j == GameBoard.BOARD_DIMENSIONS - 1) {
						
						// If a valid word, add to valid word array, reset the string
						if (IO.searchWord(checkStringDown)) {
							
							validWords.add(checkStringDown);
							checkStringDown = "";
						
						// If invalid word, change validPlay to be false
						} else {
							
							System.out.println("\nInvalid Word Down:" + checkStringDown);
							checkStringDown = "";
							validPlay = false;
							
						}
						
					}
					
				}
				
			}
			
		}
		
		// Once ROWS and COLUMNS are checked, return final result.
		
		
		// If no words are found, display generic error
		
		if (validWords.size() == 0) {
			
			validPlay = false;
			return "INVALID PLAY: No Valid Words Found On Board.";
			
		}
		
		if (validPlay == false) {
			
			// WILL NEED TO DISPLAY BAD LETTER SEQUENCE
			
			String invalidPlayString = "INVALID PLAY FOR LETTER SEQUENCE:\n";
			
			for (int i = 0; i < play.letterPositionsInRack().length(); i++) {
				
				invalidPlayString += rack.getLetter(Integer.parseInt(play.letterPositionsInRack().substring(i, i+1))-1);
				
			}
			
			return invalidPlayString;
			
		} else {
			
			String validPlayString = "VALID PLAY: \n";
			for (String word : validWords) {
				validPlayString+= word + "\n";
			}
			
			return validPlayString;
			
		}
		
	}
	
	private String letterToInt(String letter) 
	{
		String letterToInt = "";		//switch statement to convert the passed Letter into an integer
		
		switch (letter) {
		  case "A":
			  letterToInt = "0";
		    break;
		  case "B":
			  letterToInt = "1";
		    break;
		  case "C":
			  letterToInt = "2";
		    break;
		  case "D":
			  letterToInt = "3";
		    break;
		  case "E":
			  letterToInt = "4";
		    break;
		  case "F":
			  letterToInt = "5";
		    break;
		  case "G":
			  letterToInt = "6";
		    break;
		  case "H":
			  letterToInt = "7";
		    break;
		  case "I":
			  letterToInt = "8";
		    break;
		  case "J":
			  letterToInt = "9";
		}
		
		return letterToInt;
	}

	private String intToLetter(int flipMe) 
	{
		String intToLetter = "";		//switch statement to convert the passed Letter into an integer
		
		switch (flipMe) {
		  case 0:
			  intToLetter = "A";
		    break;
		  case 1:
			  intToLetter = "B";
		    break;
		  case 2:
			  intToLetter = "C";
		    break;
		  case 3:
			  intToLetter = "D";
		    break;
		  case 4:
			  intToLetter = "E";
		    break;
		  case 5:
			  intToLetter = "F";
		    break;
		  case 6:
			  intToLetter = "G";
		    break;
		  case 7:
			  intToLetter = "H";
		    break;
		  case 8:
			  intToLetter = "I";
		    break;
		  case 9:
			  intToLetter = "J";
		}
		
		return intToLetter;
	}
}
