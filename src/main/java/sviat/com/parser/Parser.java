package sviat.com.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Parser {

	public Set<String> getWords(File file) {
		try (Scanner s = new Scanner(file)) {
			HashSet<String> set = new HashSet<>();
			while (s.hasNext()) {
				set.add(s.next());
			}
			return set;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
