package wordgame.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryInput {

	// INPUT AND OUTPUT RESEARCH NEEDED
	private HashMap<Integer, String> wordList;
	private ArrayList<String> wordListArray;
	
	
	public DictionaryInput() throws IOException
	{
		//Initialise the HashMap to be returned later - NEEDS TO BE RE-IMPLEMENTED
		wordList = new HashMap<Integer, String>();
		wordListArray = new ArrayList<>();
		
		
		//read in each line
	    try (Stream<String> fileStream = Files.lines( Paths.get("wordlist.txt"), StandardCharsets.UTF_8))
	    {
	        fileStream.forEach(y -> wordListArray.add(y));
	    }
	    catch (IOException printException)									//print exception when error occurs
	    {
	    	printException.printStackTrace();								// print stack trace in exception throw
	    } 
	}
	
	
	public boolean searchWord(String word)
	{
		return wordListArray.contains(word);
	}
	
	
	
	
}
