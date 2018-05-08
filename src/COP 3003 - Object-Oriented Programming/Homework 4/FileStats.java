import java.io.*;
import java.util.*;

class FileStats {
	private Scanner input;
	private ArrayList <String> wordList = new ArrayList<String>();
	private HashSet <String> wordSet = new HashSet<String>();
	private ArrayList <Entry<String>> entryList = new ArrayList<Entry<String>>();
	private Map <String, Character> dictionary = new TreeMap<String, Character>();

	private class Entry <T> implements Comparable<Entry<T>> {
		public T word;
		public int frequency;
		public Entry(T word, int f) {
			this.word = word;
			frequency = f;
		}
		public int compareTo(Entry<T> e) {
			if(e.frequency == this.frequency) {	// compare two words by alphabetical priority
				return ((String)this.word).compareTo((String)e.word);
			}
			return new Integer(e.frequency).compareTo(this.frequency);	// compare two words by frequency		
		}
	}

	public FileStats(String path) {
		String line;
		try {
			input = new Scanner(new File(path));
		}		
		catch(FileNotFoundException e) {
			System.out.println("Error opening file...");
			System.exit(1);
		}		
		try {
			while((line = input.nextLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "[ .,;:!?(){]");   // splits tokens at non-word characters
				while (st.hasMoreTokens()) {                
					String word = st.nextToken().toLowerCase();					
					wordList.add(word);
					wordSet.add(word);
				}
			}
		}		
		catch(NoSuchElementException e) {
			// no more lines in the file
			// no handler is necessary 
		}		

		count();
	}

	private void count() {
		// find the frequency of each word in the file
		for(String word : wordSet) {
			int f = Collections.frequency(wordList, word);	
			entryList.add(new Entry(word, f));
		}	
				
		Collections.sort(entryList);	// sort the entry list in ascending order	
				
		// display the four most frequent words and their frequencies
		for(int i = 0; i < 4; i++) {
			System.out.println(entryList.get(i).word + " appears " + entryList.get(i).frequency + " times(s).");
		}
		// construct the dictionary that contains four key-value pairs
		char charValues [] = {'%', '$', '#', '*'};
		for(int i = 0; i < 4; i++) {
			dictionary.put(entryList.get(i).word, charValues[i]);	// assign characters to most frequent words
		}
	}		

	public Map<String, Character> getDictionary() {
		return dictionary;
	}
	
	public void printDictionary() {		// print all keys and values from the dictionary
		for(Map.Entry<String, Character> entry : dictionary.entrySet()) {
			System.out.printf("Key: %s%n" + "Value: %s%n", entry.getKey(), entry.getValue());
		}
	}
}

class FileCompressor {
	public static void compress(String src, String dest, Map<String, Character> dictionary) throws IOException {		
		String line;	
		try {
			File infile = new File(src);
			File outfile = new File(dest);
			BufferedReader reader = new BufferedReader(new FileReader(infile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(outfile));
			
			while((line = reader.readLine()) != null) {			 // while there is a line to read
				StringTokenizer st = new StringTokenizer(line);  // splits tokens at non-word characters
				while (st.hasMoreTokens()) {
					String word = st.nextToken().toLowerCase();	
					if(dictionary.containsKey(word)) {				// if the string contains the word
						writer.write(dictionary.get(word) + " ");	// replace the word with the character
					}
					else {							// if the string does not contain the word
						writer.write(word + " ");	// keep it as it is
					}
				}
				writer.newLine();			
			}
			reader.close();			
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}	

	public static void decompress(String src, String dest, Map<Character, String> dictionary) throws IOException {
		String line;	
		try {
			File infile = new File(src);
			File outfile = new File(dest);
			BufferedReader reader = new BufferedReader(new FileReader(infile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(outfile));
			
			while((line = reader.readLine()) != null) {			  // while there is a line to read
				StringTokenizer st = new StringTokenizer(line);   // splits tokens at non-word characters
				while (st.hasMoreTokens()) {
					String word = st.nextToken().toLowerCase();	
					if(dictionary.containsKey(word.charAt(0))) {				// if the string contains the character at the first index	
						writer.write(dictionary.get(word.charAt(0)) + " ");		// replace the character with the word
					}
					else {							// if the string does not contain the character
						writer.write(word + " ");	// keep it as it is
					}
				}
				writer.newLine();
			}			
			reader.close();			
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}
}


