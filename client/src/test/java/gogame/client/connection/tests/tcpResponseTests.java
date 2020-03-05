package gogame.client.connection.tests;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;

import gogame.client.connection.Converters;

import static org.junit.Assert.*;

import org.junit.Test;

import gogame.client.connection.DataParser;

public class tcpResponseTests {

	@Test
	public void testReturnSplitTable() {
		DataParser conv = new DataParser();
		String output1 = conv.splitStringIntoTable("string Ci w dupe")[1];
		//int output2 = conv.coordinatesFromBoardToPawnCovnerter(100, 65)[1];
		//we wspó³rzenych boardu 100.65
		//we wspó³rzednych pawn  95,83
		
		assertEquals("Ci",output1);
		
		
	}
	/*
	@Test
	public void testReceiveMove() {
		DataParser parser = new DataParser();
		int output1 = parser.receiveYouMoved("YOU_MOVED 1 1");
		//we wspó³rzenych boardu 100.65
		//we wspó³rzednych pawn  95,83
		// test przygotowany dla zmienionej metody receiveMove by zwróci³a wartosc y dla move
		
		assertEquals(95,output1);	
	}
	
	@Test
	public void testOponentMove() {
		DataParser parser = new DataParser();
		int output1 = parser.receiveOponentMoved("OPONENT_MOVED 2 2");
		//we wspó³rzenych boardu 100.65
		//we wspó³rzednych pawn  95,83 dla 0.0 dla size9
		// test przygotowany dla zmienionej metody receiveMove by zwróci³a wartosc y dla move
		
		assertEquals(160,output1);	
	}
	
	
	@Test
	public void testDelete() {
		DataParser parser = new DataParser();
		int output1 = parser.receiveDelete("DELETED 2 4 1 3 3 3")[0][0];
		int output2 = parser.receiveDelete("DELETED 2 3 1 3 3 3")[1][0];
		int output3 = parser.receiveDelete("DELETED 2 7 4 6 3 5")[2][0];
		
		assertEquals(4,output1);
		assertEquals(3,output2);
		assertEquals(5,output3);
	}
	
	*/
	
	
}
