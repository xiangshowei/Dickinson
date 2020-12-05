package lab06.authors;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class SignatureCollectionTest {
	
	private static final String FILE = "src/lab06/authors/scTestFile.txt";
	private SignatureCollection sigCol;
	private TextSignature inputLewisCarol;
	private TextSignature inputShakespeare;
	private TextSignature inputJaneAustin;
	private TextSignature inputJamesJoyce;
	private TextSignature inputHarrietStowe;
	
	
	@Before
	public void setUp() throws Exception {
		sigCol = new SignatureCollection();	
		inputLewisCarol = new TextSignature("lewis carol", 4.060433217899097, 14.256534365924491, 0.09608202621036192, 0.0408433489509065, 2.3901258470474347);
		inputShakespeare = new TextSignature("william shakespere", 4.097739143138902, 10.32780082987552, 0.14726615289090178, 0.08645312100514993,2.4436061863447756);
		inputJaneAustin = new TextSignature("jane austin",4.403594033477543,17.142274134534198,0.05277761815241395,0.02045758662868495,2.524486349563749);
		inputJamesJoyce = new TextSignature("james joyce",4.4748573331516885,11.336336336336336,0.11170397953468658,0.06060881279990312,1.9757185757185758);
		inputHarrietStowe = new TextSignature("harriet stowe",4.187597707139135,16.695942851909514,0.06670689229587778,0.029686514357806972,3.266233171535855);		
	}

	@Test
	public void testSaveAndLoadSignature() throws IOException, ParseException{
		sigCol.addSignature(inputLewisCarol); 
		sigCol.saveSignatures(FILE);
		sigCol.loadSignatures(FILE);
		
		TextSignature outputLewisCarol = sigCol.getSignature("lewis carol");
		assertEquals("the author should be lewis carol", "lewis carol", outputLewisCarol.getAuthorName());
		assertEquals("the average word length for lewis carol should be 4.0604", 4.0604, outputLewisCarol.getAveWordLength(), 0.0001);
		assertEquals("the average sentence length for lewis carol should be 14.2565", 14.2565, outputLewisCarol.getAveWordsPerSentence(), 0.0001);
		assertEquals("the average type token ratio for lewis carol should be 0.0960", 0.0960, outputLewisCarol.getTypeTokenRatio(), 0.0001);
		assertEquals("the Hapax Legomana ratio for lewis carol should be 0.0408", 0.0408, outputLewisCarol.getHapaxLegomanaRatio(), 0.0001);
		assertEquals("the average sentence complexity for lewis carol should be 2.3901", 2.3901, outputLewisCarol.getSentenceComplexity(), 0.0001);
		
//		TextSignature inputLewisCarol2 = new TextSignature("lewis carol", 1, 2, 3, 4, 5);
//		sigCol.addSignature(inputLewisCarol2);
//		sigCol.saveSignatures(FILE);
//		sigCol.loadSignatures(FILE);
//		
//		assertEquals("the author should be lewis carol", "lewis carol", outputLewisCarol.getAuthorName());
//		assertEquals("the average word length for lewis carol should be 1", 1, outputLewisCarol.getAveWordLength(), 0.0001);
//		assertEquals("the average sentence length for lewis carol should be 2", 2, outputLewisCarol.getAveWordsPerSentence(), 0.0001);
//		assertEquals("the average type token ratio for lewis carol should be 3", 3, outputLewisCarol.getTypeTokenRatio(), 0.0001);
//		assertEquals("the Hapax Legomana ratio for lewis carol should be 4", 4, outputLewisCarol.getHapaxLegomanaRatio(), 0.0001);
//		assertEquals("the average sentence complexity for lewis carol should be 5", 5, outputLewisCarol.getSentenceComplexity(), 0.0001);
	}
	
	@Test
	public void testFindMostSimilarAuthor() {
		sigCol.addSignature(inputShakespeare);
		sigCol.addSignature(inputJaneAustin);
		sigCol.addSignature(inputJamesJoyce);
		sigCol.addSignature(inputHarrietStowe);	
		
		//using Lewis Carol as the base author to compare to the other authors in signature collection
		TextSignature mostSimTextSig = sigCol.findMostSimilar(inputLewisCarol);
		assertEquals("the author with the most silimar with Lewis Carol is Harriet Stowe", "harriet stowe",mostSimTextSig.getAuthorName()); 
	}
	
	
}
