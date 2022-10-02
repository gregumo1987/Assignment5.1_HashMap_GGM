import java.util.Set;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.IOException;

/** 
 * A driver class to read the contents of a CSV file into a 
 * dictionary of archaic words, print the dictionary and then
 * find an print 5 words with definitions and hashKeys.
 * 
 * @author Greg Gutierrez
 */
public class DictionaryApplication {

	public static void main(String[] args) {
		Dictionary myArchWordDict = new Dictionary();
		// Read the source file and add word-value pairs to the dictionary
		String fileName = "DictionaryWordValuePairs.csv";
		String line = "";
		String word = "";
		String nextWord = "";
		String def = "";
		
		BufferedReader reader = null; 
		try { 
			reader = new BufferedReader(new FileReader(fileName)); 
			while ((line = reader.readLine()) != null) { 
				String[] words = line.split(","); 
				word = words[0];
				def = words[1];
				myArchWordDict.addDef(word, def);
			}
		} catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} finally { 
			if (reader != null) { 
				try { 
					reader.close(); 
				} catch (IOException e) { 
					e.printStackTrace(); 
				}
			}// end if
		}// end try
		
		// Display the dictionary's contents with the toString method
		System.out.println(myArchWordDict.toString());
		
		// Create key iterator
		Set<String> archKeySet = myArchWordDict.getKeySet();
		Iterator<String> keyIt = archKeySet.iterator();
		
		/* Iterate through the keySet and select every 87th (435/5)
		 * to get five keys for printing.
		 */
		System.out.println("Using HashMap.get to retrieve data.");
		System.out.println("-----------------------------------");
		System.out.println("Note hashes calculated two ways, one using the\n"
				+ " hashCode function and one calulated directly\n using"
				+ " the \"Horner method\".");
		int counter = 1;
		while (keyIt.hasNext()) {
			nextWord = keyIt.next();
			if (counter % 87 == 0) {
				System.out.println("\nword: " + nextWord + " --> definition: "
						+ myArchWordDict.getDefinition(nextWord));
				System.out.println("hash from hashCode = " + nextWord.hashCode());
				System.out.println("manually calculated hash using Horner method = "
						+ myArchWordDict.hash(nextWord));
				System.out.println("hashIndex = " 
						+ myArchWordDict.getHashIndex(nextWord));
				System.out.println();
			}
			counter ++;
		}// end while	
	}// end main

}// end class
