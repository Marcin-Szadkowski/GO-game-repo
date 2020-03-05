package gogame.client.connection.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import gogame.client.connection.Converters;

public class coordinatesFromBoardToPawnConverterTests {

	@Test
	public void test() {
		Converters conv = new Converters();
		int output1 = conv.coordinatesFromBoardToPawnCovnerter(100, 65)[0];
		int output2 = conv.coordinatesFromBoardToPawnCovnerter(100, 65)[1];
		//we wspó³rzenych boardu 100.65
		//we wspó³rzednych pawn  95,83
		
		assertEquals(95,output1);
		assertEquals(83,output2);
	}

}
