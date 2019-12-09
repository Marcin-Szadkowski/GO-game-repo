package gogame.client.gameElements;

public abstract class AbstractBoard {
	
	protected abstract Board createBoard(String type) ;
	
	
	 public void manufactureBoard(String type) {
		 Board board = createBoard(type);
		
	 }

	
	 
	 
}
