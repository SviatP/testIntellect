package sviat.com.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WordUnit implements Comparable<WordUnit> {
	String word;
	List<String> constituents;

	@Override
	public int compareTo(WordUnit o) {
		return -Integer.compare(constituents.size(), o.constituents.size());
	}
}
