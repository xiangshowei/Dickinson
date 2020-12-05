package lab06.authors;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

public class TextSignatureTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReadFile() throws IOException {
		TextSignature ts = new TextSignature("none",
				"bin/lab06/authors/documents/testfile.txt");
		String text = ts.getText();
		if (text != null) {
			text = text.trim();
		}
		String val = "This file has some words, sentences, and phrases, in it. "
				+ "This file, and others like it that you create, can be used "
				+ "for testing your TextSignature class.";
		
		assertEquals("Text not read correctly", val, text);
	}

	@Test
	public void testBadFileName() {
		try {
			new TextSignature("none",
					"bin/lab06/authors/documents/no.such.file.txt");
			fail("Should throw FileNotFoundException.");
		} catch (FileNotFoundException e) {
			// pass
		} catch (Exception e) {
			fail("Incorrect exception type thrown.");
		}
	}

	@Test
	public void testAverageWordLengthOnTestFile() throws IOException {
		TextSignature ts = new TextSignature("testfile",
				"bin/lab06/authors/documents/testfile.txt");
		assertEquals("Incorrect average word length", 4.4444,
				ts.getAveWordLength(), 0.0001);
	}

	@Test
	public void testAverageWordsPerSentenceOnTestFile() throws IOException {
		TextSignature ts = new TextSignature("testfile",
				"bin/lab06/authors/documents/testfile.txt");
		assertEquals("Incorrect average words per sentence", 13.5,
				ts.getAveWordsPerSentence(), 0.0001);
	}

	@Test
	public void testTypeTokenRatioOnTestFile() throws IOException {
		TextSignature ts = new TextSignature("testfile",
				"bin/lab06/authors/documents/testfile.txt");
		assertEquals("Incorrect type token ratio", 0.8519,
				ts.getTypeTokenRatio(), 0.0001);
	}

	@Test
	public void testHapaxLegomanaRatioOnTestFile() throws IOException {
		TextSignature ts = new TextSignature("testfile",
				"bin/lab06/authors/documents/testfile.txt");
		assertEquals("Incorrect Hapax Legomana ratio", 0.7037,
				ts.getHapaxLegomanaRatio(), 0.0001);
	}

	@Test
	public void testSentenceComplexityOnTestFile() throws IOException {
		TextSignature ts = new TextSignature("testfile",
				"bin/lab06/authors/documents/testfile.txt");
		assertEquals("Incorrect sentence complexity", 3.5,
				ts.getSentenceComplexity(), 0.0001);
	}

	@Test
	public void testAverageWordLengthOnLewisCarol() throws IOException {
		TextSignature ts = new TextSignature("lewis carol",
				"bin/lab06/authors/documents/lewis.carol.txt");
		assertEquals("Incorrect average word length", 4.0604,
				ts.getAveWordLength(), 0.0001);
	}

	@Test
	public void testAverageWordsPerSentenceOnLewisCarol() throws IOException {
		TextSignature ts = new TextSignature("lewis carol",
				"bin/lab06/authors/documents/lewis.carol.txt");
		assertEquals("Incorrect average words per sentence", 14.1162,
				ts.getAveWordsPerSentence(), 0.0001);
	}

	@Test
	public void testTypeTokenRatioOnLewisCarol() throws IOException {
		TextSignature ts = new TextSignature("lewis carol",
				"bin/lab06/authors/documents/lewis.carol.txt");
		assertEquals("Incorrect type token ratio", 0.0961,
				ts.getTypeTokenRatio(), 0.0001);
	}

	@Test
	public void testHapaxLegomanaRatioOnLewisCarol() throws IOException {
		TextSignature ts = new TextSignature("lewis carol",
				"bin/lab06/authors/documents/lewis.carol.txt");
		assertEquals("Incorrect Hapax Legomana ratio", 0.0408,
				ts.getHapaxLegomanaRatio(), 0.0001);
	}

	@Test
	public void testSentenceComplexityOnLewisCarol() throws IOException {
		TextSignature ts = new TextSignature("lewis carol",
				"bin/lab06/authors/documents/lewis.carol.txt");
		assertEquals("Incorrect sentence complexity", 2.3901,
				ts.getSentenceComplexity(), 0.0001);
	}

	@Test
	public void testDifferenceCalculationSameFile() throws IOException {
		TextSignature ts1 = new TextSignature("testfile", 1.0, 2.0, 3.0, 4.0,
				5.0);
		TextSignature ts2 = new TextSignature("testfile", 1.0, 2.0, 3.0, 4.0,
				5.0);

		assertEquals("Same feature values should have no difference", 0.0,
				ts1.computeDifference(ts2), 0.0001);
	}

	@Test
	public void testDifferenceCalculationDifferentFeatures() {
		TextSignature ts1 = new TextSignature("testfile", 1.5, 2.5, 3.5, 4.5,
				5.5);
		TextSignature ts2 = new TextSignature("testfile", 2.0, 4.0, 2.0, 6.0,
				1.0);

		assertEquals("Incorrect distance value calculated", 659.265,
				ts1.computeDifference(ts2), 0.0001);
	}
}
