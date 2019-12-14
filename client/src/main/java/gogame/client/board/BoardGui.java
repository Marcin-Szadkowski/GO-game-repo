package gogame.client.board;

import gogame.client.board.gui.BoardGuiSize13;
import gogame.client.board.gui.BoardGuiSize19;
import gogame.client.board.gui.BoardGuiSize9;

public class BoardGui extends BoardFactory {

	@Override
	public Board createBoard(int type) {
		if(type == 9) {
			return new BoardGuiSize9();
		}
		if(type == 13) {
			return new BoardGuiSize13();
		}
		if(type == 19) {
			return new BoardGuiSize19();
		}
		else {throw new IllegalArgumentException("Unknow board type."+ type);
		
		}
	}
	
	@Override
	protected int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected int stepWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected int stepHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
   

}