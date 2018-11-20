import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnagramFinder2 {

	public static void main(String[] args) {
		
		Scanner userInputScanner = new Scanner(System.in); 
		Path path;
		
		try{
			AnagramFinder2 p = new AnagramFinder2();

            //Print Title to screen
            System.out.println("Welcome to the Anagram Finder");
            System.out.println("-----------------------------");

            //path to the dictionary file
            Path currentDir = Paths.get(".");
            path = currentDir.resolve(args[0]);                       
            //path = Paths.get("C:/Users/wlukenba/Desktop/Dev/AnagramFinder2/AnagramFinder2", "dictionary.txt");

            //Timer for measuring how long it takes to load the dictionary
            long startTime = System.nanoTime();
            //Create a dictionary from the file
            HashMap<String, String> anagramDict = p.CreateDictionary(path);

            long endTime = System.nanoTime();
            long timeElapsed = (endTime - startTime) / 1000000;

            //Show the user the time it took to load the dictionary
            System.out.println("Dictionary loaded in " + timeElapsed + " ms");

            System.out.print("AnagramFinder>");      
            //Record the user input
            String userInput = userInputScanner.nextLine(); 

            while (!userInput.equals("exit"))
            {
                List<String> resultList  = new ArrayList<String>();

                //Timer for measuring how long it takes to find anagrams from the dictionary
                startTime = System.nanoTime();

                String orderAnagramToFind = p.sortString(userInput);

                //Finds the anagram in the dictionary for the user input
                if (anagramDict.containsKey(orderAnagramToFind))
                {                                        
                    resultList = new ArrayList<String>(Arrays.asList(anagramDict.get(orderAnagramToFind).split(",")));
                }

                endTime = System.nanoTime();
                timeElapsed = (endTime - startTime) / 1000000;

                //Shows the results to the user
                p.ResultOutput(resultList, userInput, timeElapsed);

                System.out.print("AnagramFinder>");      
                userInput = userInputScanner.nextLine(); 
            }
        }        
        catch (Exception e)
        {
            System.out.println("ERROR: Unexpected Error!");            
        }
		finally {
		    if(userInputScanner!=null)
		    	userInputScanner.close();
		}
	}
	
	public String sortString(String strToSort) 
    { 
        char tempCharArray[] = strToSort.toCharArray(); 
        Arrays.sort(tempCharArray); 
        return new String(tempCharArray); 
    }
	
	public String CreateDictValue(String currentValue, String newValue)
    {
        return currentValue + "," + newValue;
    }
	
	public void ResultOutput(List<String> resultListToPrint, String userInput, long timeToGetResult)
    {
        if (resultListToPrint != null && resultListToPrint.size() > 0)
        {
        	System.out.println(resultListToPrint.size() + " Anagrams found for " + userInput + " in " + timeToGetResult + "ms");
        	System.out.println(String.join(",", resultListToPrint));
        }
        else
        	System.out.println("No anagrams found for " + userInput + " " + timeToGetResult + "ms");
    }
	
	public HashMap<String, String> CreateDictionary(Path path)
    {
		try {
	        List<String> dictFromFile = Files.readAllLines(path);
			
			HashMap<String, String> anagramDict = new HashMap<String, String>();
			
	        for (String i : dictFromFile)
	        {
	            String orderAnagramToFind = sortString(i);
	
	            if (anagramDict.containsKey(orderAnagramToFind))
	            {
	                anagramDict.replace(orderAnagramToFind, CreateDictValue(anagramDict.get(orderAnagramToFind), i));
	            }
	            else
	            {
	                anagramDict.put(orderAnagramToFind, i);
	            }
	        }
	
	        return anagramDict;
		}
		catch (Exception e)
        {
            System.out.println("CreateDictionary ERROR: Unexpected Error!");
            return null;            
        }
		
    }

}
