import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileIO {
	public static void main(String args[]) throws IOException{
		FileStats fs = new FileStats("basketball.txt");
		fs.printDictionary();

		Map <String, Character> m1 = fs.getDictionary();
		FileCompressor.compress("basketball.txt", "compressed.txt", m1);

		Map<Character, String> m2 = new HashMap<>();
		for(Map.Entry<String, Character> entry: m1.entrySet()) {
			m2.put(entry.getValue(), entry.getKey());
		}
		FileCompressor.decompress("compressed.txt", "decompressed.txt", m2);
	}
}