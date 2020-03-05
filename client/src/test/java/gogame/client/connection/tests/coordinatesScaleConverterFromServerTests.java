package gogame.client.connection.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import gogame.client.connection.Converters;

public class coordinatesScaleConverterFromServerTests {

	@Test
	public void test1() {
		Converters conv = new Converters();
		int output1 = conv.coordinatesScaleConverterFromServer(1, 1, 9)[0];
		int output2 = conv.coordinatesScaleConverterFromServer(1, 1, 9)[1];
		//we wspó³rzenych boardu 100.65
		//we wspó³rzednych pawn  95,83
		
		assertEquals(100,output1);
		assertEquals(65,output2);
	}

	@Test
	public void test2() {
		Converters conv = new Converters();
		int output1 = conv.coordinatesScaleConverterFromServer(1, 3, 9)[0];
		int output2 = conv.coordinatesScaleConverterFromServer(1, 3, 9)[1];
		assertEquals(230,output1);
		assertEquals(65,output2);	
		}
	
	@Test
	public void test3() {
		Converters conv = new Converters();
		int output1 = conv.coordinatesScaleConverterFromServer(2, 3, 9)[0];
		int output2 = conv.coordinatesScaleConverterFromServer(2, 3, 9)[1];
		assertEquals(230,output1);
		assertEquals(130,output2);
	}
}
