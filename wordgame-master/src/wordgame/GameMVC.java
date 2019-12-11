package wordgame;

import java.io.IOException;

public class GameMVC {	
	
	// Create model, view and controller. They
	// are created once here and passed to the parts
	// that need them so there is only one copy of each.
	
	public static void main(String[] args) throws IOException {
		
		// NEEDS CLARIFICATIOn
		
		GameBoard board = new GameBoard();
		GameController controller = new GameController(board);
		TUI view = new TUI(controller);
		
		
	}
	
}
