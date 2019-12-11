package wordgame;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class GameBoard {

	// 2D Array of String representing each space.
	//[row][col]
	public static final int BOARD_DIMENSIONS = 10;
	private String[][] board;
	private Random random;
	
	
	public GameBoard() {
		
		random = new Random();
		
		// Board is 10x10, A-J, 1-10. Uses "-" to represent empty space. All begin empty.
		board = new String[10][10];
		
		fillBoard(); // Fill board with special spaces and empty spaces

		
	}
	
	
	public void fillBoard() {
		
		for(int i=0; i < board.length; i++) {
	        for(int j=0; j < board[i].length; j++) {
	        	board[i][j] = "-";
	        }
	    }
		
		//set bonus tiles, these are at fixed locations
		board[1][4] = "+";
		board[1][5] = "+";
		board[3][3] = "+";
		board[6][3] = "+";
		board[3][6] = "+";
		board[6][6] = "+";
		board[8][4] = "+";
		board[8][5] = "+";
	}
	
	
	public String getBoard() {
		
		String boardString = "";
		
		for(int i=0; i < board.length; i++) {
	        for(int j=0; j < board[i].length; j++) {
	            boardString+= board[i][j];
	            if (j == 9) {
	            	boardString+= "\n"; // Add line break at end of each row
	            }
	        }
	    }
		
		return boardString;
		
	}
	
	
	public void setPosition(int character, int number, String setAs) 
	{
		//set the cell at the relevant location to a certain value
		board[character][number] = setAs;
	}
	
	public String peekAtPosition(int x, int y) 
	{
		
		int row = y;
		int col = x;
		
		String contents = "";
		
		//return a copy of the value at a position on the board
		contents = board[row][col];
		return contents;
	}
	
	public boolean checkDouble(int x, int y) {
		
		int row = y;
		int col = x;
		
		if(peekAtPosition(row, col).equals("+")) {
			return true;
		}else {
			return false;
		}
	}
	
}
