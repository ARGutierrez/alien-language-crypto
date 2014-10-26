import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class CorpusParser {
	
	public static void main(String[] args) {
		
		int totalWords;
		
		String filePath = "../../../../Downloads/encrypted/corpus.txt";
		
		Map<String, Integer> words = new HashMap<String, Integer>();
		Scanner in = null;
		
		try {
			in = new Scanner(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	
		while(in.hasNext()) {
			String token = in.next();
			
			if(words.containsKey(token)) {
				words.put(token, words.get(token) + 1);
			} else {
				words.put(token, 1);
			}
		}
		
		List<Entry<String, Integer>> wordSet = new ArrayList<Entry<String, Integer>>(words.entrySet());
		
		Collections.sort(wordSet, 
	            new Comparator<Entry<String, Integer>>() {
	                @Override
	                public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
	                    return e2.getValue().compareTo(e1.getValue());
	                }
	            }
	    );
		totalWords = wordSet.size();
		// System.out.println(wordSet.size() + " words.");
		
		
		
		// Save all this info
		try(BufferedWriter w = new BufferedWriter(new FileWriter(new File("Corpus Analysis.txt")))) {
			for(Entry<String, Integer> s : wordSet) {
				float percentage = s.getValue() / totalWords;
				w.write(s.getKey() + " appears " + s.getValue() + " times. (" + percentage + "%)");
				w.newLine();
			}
		} catch(IOException e) {};
		
	}

}
