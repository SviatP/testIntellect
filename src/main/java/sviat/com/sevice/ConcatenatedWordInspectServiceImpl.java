package sviat.com.sevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import sviat.com.domain.WordUnit;

public class ConcatenatedWordInspectServiceImpl implements ConcatenatedWordInspectService {

	@Override
	public Map<String, WordUnit> getDisassembledMapOfConcatenatedWords(Set<String> words) {
		HashMap<String, WordUnit> map = new HashMap<>();

		words.forEach(element -> {
			WordUnit unit = getUnit(element, words);
			if (unit != null) {
				map.put(element, unit);
			}
		});

		return map;
	}

	private WordUnit getUnit(String word, Set<String> set) {

		WordUnit wordUnit = WordUnit.builder().word(word).constituents(new ArrayList<>()).build();

		int startIndex = 0;
		int endIndex = 1;
		boolean isAssembled = false;


		while (word.length() >= endIndex) {

			String subWord = word.substring(startIndex, endIndex);

			if (set.contains(subWord)) {
				endIndex = lastSymbolOfSubWord(word, set, startIndex, endIndex);
				subWord = word.substring(startIndex, endIndex);
				startIndex = endIndex++;
				isAssembled = true;
				wordUnit.getConstituents().add(subWord);
			} else {
				isAssembled = false;
				endIndex++;
			}
		}

		return isAssembled
				? wordUnit
				: null;
	}

	private int lastSymbolOfSubWord(String word, Set<String> set, int start, int end) {
		if (word.length() >= end + 1 && set.contains(word.substring(start, end + 1))) {
			end++;
			end = lastSymbolOfSubWord(word, set, start, end);
		}
		return end;
	}
}
