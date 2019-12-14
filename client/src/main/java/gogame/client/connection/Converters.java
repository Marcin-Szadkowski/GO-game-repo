package gogame.client.connection;

import gogame.client.board.gui.CustomConstants;

public class Converters {

    CustomConstants constant = new CustomConstants();
    
    
	public int[] coordinatesScaleConverterFromBoard(int x, int y, int size) {
		int[] output = new int [2];
		int orientX=0,orientY = 0;
		if(size == 9) {orientX=100 ; orientY=65;}
		else if(size == 13) {orientX= 100; orientY=47;}
		else if(size == 19) {orientX= 100; orientY=33;}
		
		
		output[0]=y/orientY;
		output[1]=(x-100)/orientY+1;
		
		
		return output;	
	}
	public int[] coordinatesScaleConverterFromServer(int x, int y, int size) {
		
		int[] output = new int [2];
		int orientX=0,orientY = 0;
		if(size == 9) {orientX=100 ; orientY=65;}
		else if(size == 13) {orientX= 100; orientY=47;}
		else if(size == 19) {orientX= 100; orientY=33;}
		output[0]=orientX+(y-1)*orientY;
		output[1]=x*orientY;
		
		
		return output;	
	}
	
	public int[] coordinatesFromBoardToPawnCovnerter(int x, int y) {
		int[] output = new int [2];
		output[0] = x+constant.shiftWidth-constant.squareSideLength/2;
		output[1]=y+constant.shiftHeight-constant.squareSideLength/2;
		return output;
	}
	public int[] coordinatesFromPawnToBoardCovnerter(int x, int y) {
		int[] output = new int [2];
		output[0] = x-constant.shiftHeight+-constant.squareSideLength/2;
		output[1]=y-constant.shiftHeight+constant.squareSideLength/2;
		return output;
	}
	
	public String[] splitStringIntoTable(String string) {

		String[] tab = string.split(" ");
		return tab;
		
		}
		
	
	
	}
	
	
	

