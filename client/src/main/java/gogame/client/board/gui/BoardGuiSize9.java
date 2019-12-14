package gogame.client.board.gui;

import gogame.client.board.Board;

public class BoardGuiSize9 extends CustomConstants implements Board{

	public int size() {
		// TODO Auto-generated method stub
		return 9;
	}
	public int stepWidth() {
	return 65;
	}
	public int stepHeight() {
	return 65;
	}
	
	public int[] orientalCoordinates() {
		int[] output = new int [2];
		output[0]=CustomConstants.marginLeft;
		output[1]=stepWidth();
		return output;
	}

}
