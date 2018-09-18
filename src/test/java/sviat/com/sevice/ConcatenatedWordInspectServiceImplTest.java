package sviat.com.sevice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sviat.com.domain.WordUnit;
import sviat.com.parser.Parser;

public class ConcatenatedWordInspectServiceImplTest {

	ConcatenatedWordInspectServiceImpl service;

	@Before
	public void init() {
		service = new ConcatenatedWordInspectServiceImpl();
	}

	@Test
	public void getDisassembledMapOfConcatenatedWords() throws Exception {
		Set<String> testSet = new HashSet<>();
		testSet.add("cat");
		testSet.add("cats");
		testSet.add("catsdogcats");
		testSet.add("dog");
		testSet.add("dogcatsdog");
		testSet.add("hippopotamuses");
		testSet.add("rat");
		testSet.add("ratcatdogcat");
		testSet.add("");
		Map<String, WordUnit> unitMap = service.getDisassembledMapOfConcatenatedWords(testSet);
		assertEquals(unitMap.entrySet().size(), 3);
		assertTrue(unitMap.keySet().containsAll(Arrays.asList("catsdogcats", "dogcatsdog", "ratcatdogcat")));

	}

}
