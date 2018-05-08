import java.io.*;
import java.util.*;

class FileStats {
	private Scanner input = null;
	private ArrayList <String> wordList=new ArrayList<String>();	// stores all the words in the file, can have duplicates
	private HashSet <String> wordSet=new HashSet<String>();		// only stores distinct words without duplicates
	private ArrayList <Entry<String>> entryList=new ArrayList<Entry<String>>();

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
	}
}



