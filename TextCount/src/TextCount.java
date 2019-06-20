import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import static java.util.stream.Collectors.*;
import java.util.concurrent.TimeUnit;

public class TextCount {
	public static void main(String[] args) throws FileNotFoundException {
		
		long startTime = System.nanoTime();
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		Scanner textFile = new Scanner(new File("bin\\BTVN-5.txt"));
		while(textFile.hasNext()) {
			String word = textFile.next();
			word = word.replaceAll("\\W", "");
			if (hmap.containsKey(word))
				{ int count=hmap.get(word)+1;
				hmap.put(word, count);}
			else {hmap.put(word,1);}
		}
		textFile.close();
		
	    Map<String, Integer> sorted = hmap
	            .entrySet()
	            .stream()
	            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
	            .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,LinkedHashMap::new));
	    for (Map.Entry<String, Integer> entry : sorted.entrySet()) {System.out.println(entry);}
	    long endTime = System.nanoTime();
	    long durationInNano = (endTime - startTime);
	    long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);
	    System.out.println("Running Time: " +durationInMillis);
	}
}