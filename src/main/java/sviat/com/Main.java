package sviat.com;

import static java.util.stream.Collectors.joining;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import sviat.com.domain.WordUnit;
import sviat.com.parser.Parser;
import sviat.com.sevice.ConcatenatedWordInspectServiceImpl;

public class Main {

	public static void main(String[] args) {

		Parser parser = new Parser();
		ConcatenatedWordInspectServiceImpl service = new ConcatenatedWordInspectServiceImpl();
		File file = new File(parser.getClass().getClassLoader().getResource("files/wordsforproblem.txt").getFile());

		Set<String> words = parser.getWords(file);
		Map<String, WordUnit> concatenatedWords = service.getDisassembledMapOfConcatenatedWords(words);



		//report block
		List<Map.Entry<String, WordUnit>> sortedList =
				concatenatedWords.entrySet().parallelStream()
						.filter(entry -> entry.getValue() != null)
						.sorted(Comparator.comparing(Map.Entry::getValue))
						.collect(Collectors.toList());

		System.out.println(String.format("File contains %d words. And only %d consist of another words(are concatenated)",
				words.size(),
				sortedList.size()));

		System.out.println(String.format("The longest and 2nd longest concatenated words are: \n1st: %s with words -> [%s] "
						+ "\n2nd: %s with words -> [%s]",
				sortedList.get(0).getValue().getWord(),
				sortedList.get(0).getValue().getConstituents().stream().collect(joining(", ")),
				sortedList.get(1).getValue().getWord(),
				sortedList.get(1).getValue().getConstituents().stream().collect(joining(", "))));


	}
}


