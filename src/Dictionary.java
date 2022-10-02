import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

/** 
 * A class that creates a dictionary of archaic words and
 * their definitions. The list of words to be used will be 
 * from a CSV source file.
 * 
 * @author Greg Gutierrez
 **/
public class Dictionary {
	private HashMap<String, String> archaicWordDictionary;
	
	/** Empty argument constructor for a dictionary to contain the 
	 * set of definitions that was supplied for this assignment.
	 */
	public Dictionary () {
		/* Data file contains 436 entries so set hash table size at 439,
		 * the next higher prime number.
		 */
		archaicWordDictionary = new HashMap<String, String>(439);
	}// end empty-argument constructor

	/** Method to add an archaic word and its definition to the 
	 * dictionary.
	 
	 * @param archWord The word to be added to the dictionary.
	 * @param archDefinition The definition of the word to be added.
	 * @return The previous definition associated with this word or 
	 * null if there was no previous mapping for this word.
	 */
	public String addDef(String archWord, String archDefinition) {
		return archaicWordDictionary.put(archWord, archDefinition);
	}// end addDef
	
	/** Method to get the definition of a specified word.
	 * 
	 * @param archWord The word to be defined.
	 * @return The definition of the word
	 */
	public String getDefinition(String archWord) {
		return (archaicWordDictionary.get(archWord));
	}// end getDefinition
	
	/** Method to get the set of keys (words) for this dictionary, 
	 * which can then be used to create an iterator.
	 * 
	 * @return The set of key for this dictionary.
	 */
	public Set<String> getKeySet() {
		return archaicWordDictionary.keySet();
	}// end getKeySet()
	
	/** Gets the size (number of entries) of the dictionary.
	 * 
	 * @return The size of the dictionary.
	 */
	public int getSize() {
		return archaicWordDictionary.size();
	}// end getSize
	
	/** Method to calculate a hash for a string each character's Unicode
	 * value is multiplied by a factor based on the character's position in 
	 * string and the results for each character are summed to produce the 
	 * hash. 
	 * @param key The string to be hashed.
	 * @return The string's hash as an integer.
	 */
	public int hash(String key) {
	   int hash = 0;
	   for (int i=0; i<key.length(); i++) {
		   hash = 31*hash + key.charAt(i);
	   }
	   return hash;
	} // end hash
	
	/** Hashes are compressed into a hashIndex to accommodate the size of the 
	 * hash table, this is usually done by finding the modulus of the hash 
	 * after division by the hash table size. 
	 * @param key The string key being hashed.
	 * @return The compressed hash. 
	 */
	public int getHashIndex(String key)
	{
	   int hashIndex = key.hashCode() % 101;
	   if (hashIndex < 0)
	      hashIndex = hashIndex + 101;
	    
	   return hashIndex;
	} // end getHashIndex
	
	/** Method to output the dictionary's contents as a string.
	 * 
	 * @return The contents of the dictionary as a string.
	 */
	public String toString() {
		String str = "";
		String nextWord = "";
		Set<String> archKeySet = getKeySet();
		Iterator<String> keyIt = archKeySet.iterator();
		str += String.format("     %-20s" + "DEFINITION\n", "ARCHAIC WORD");
		str += String.format("     %-20s" + "----------\n", "------------");
		int i=0;
		while (keyIt.hasNext()) {
			nextWord = keyIt.next();
			i++;
			str += String.format(" %3d %-20s" + getDefinition(nextWord) + "\n",i, nextWord);
		}
		return str;
	}// end toString

}// end class
