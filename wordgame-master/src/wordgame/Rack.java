package wordgame;

import java.util.Random;

public class Rack {
	
	private String[] rack; // Rack Object holds 5 characters
	private String alphabet = "abcdefghijklmnopqrstuvwxyz";		// Alphabet String created displaying the alphabet, fill Rack with random letter for each index
	private Random random; // Random generator for filling tiles
	
	public Rack() {
		
		// Initialise the rack object, empty, filled by GameController
		rack = new String[5];
		random = new Random();
		
	}
	
	// To fill the rack with random characters
	public String fillRack() {
		
		String rackString = "";
		
		for (int i = 0; i < rack.length; i++) {
			String letter = "" + alphabet.charAt(random.nextInt(alphabet.length())); // Convert char to string
			rack[i] = letter;
			rackString+= letter; // Add Letter to a string to be returned to represent the rack
		}
		
		return rackString;
	}
	
	// Accessor Methods
	
	public String getLetter(int index) {
		
		return rack[index];
		
	}
	
	public String getRack() {
		
		String rackString = "";
		
		for (int i = 0; i < rack.length; i++) {
			rackString+= rack[i];
		}
		
		return rackString;
		
	}
	
}
