package gogame.client.connection.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import gogame.client.connection.Converters;

public class coordinatesScaleConverterFromBoardTest {

	
	//----------------------------------------------------testy dla board 9
	@Test
	public void test1() {
		Converters conv = new Converters();
		int output1 = conv.coordinatesScaleConverterFromBoard(230, 65, 9)[0];
		int output2 = conv.coordinatesScaleConverterFromBoard(230, 65, 9)[1];
		assertEquals(1,output1);
		assertEquals(3,output2);
	}
	
	
	@Test
	public void test2() {
		Converters conv = new Converters();
		int output1 = conv.coordinatesScaleConverterFromBoard(165, 195, 9)[0];
		int output2 = conv.coordinatesScaleConverterFromBoard(165, 195, 9)[1];
		assertEquals(3,output1);
		assertEquals(2,output2);
	}
	
	@Test
	public void test3() {
		Converters conv = new Converters();
		int output1 = conv.coordinatesScaleConverterFromBoard(230, 130, 9)[0];
		int output2 = conv.coordinatesScaleConverterFromBoard(230, 130, 9)[1];
		assertEquals(2,output1);
		assertEquals(3,output2);
	}
	
	
	
}
