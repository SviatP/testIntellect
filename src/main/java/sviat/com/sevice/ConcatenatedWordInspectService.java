package sviat.com.sevice;

import java.util.Map;
import java.util.Set;

import sviat.com.domain.WordUnit;

public interface ConcatenatedWordInspectService {

	Map<String, WordUnit> getDisassembledMapOfConcatenatedWords(Set<String> words);
}
