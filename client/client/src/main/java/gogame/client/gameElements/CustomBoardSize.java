package gogame.client.gameElements;

public class CustomBoardSize{

	 public int[] takenBoardSize(int size)
	    {
	    	//return as a table of argument  width and height of singular checker box
	    	int[] parametersOfBoard = new int[2];
	    	
	    	if(size == 9) {parametersOfBoard[0] = 100; parametersOfBoard [1] = 100;}
	    	else if (size == 13) {parametersOfBoard[0] = 75; parametersOfBoard [1] = 75;}
	    	else if (size == 19) {parametersOfBoard[0] = 50; parametersOfBoard [1] = 50;}
	    	return parametersOfBoard;
	    }
	    // return a scaled size of pawn width and heigh based on different  board size 
	    public int[] takenPawnSize(int size) {
	    	int[] parametersOfPawn = new int[2];
	    	
	    	if(size == 9) {parametersOfPawn[0] = 75; parametersOfPawn [1] = 75;}
	    	else if (size == 13) {parametersOfPawn[0] = 50; parametersOfPawn [1] = 50;}
	    	else if (size == 19) {parametersOfPawn[0] = 25; parametersOfPawn [1] = 25;}
	    	return parametersOfPawn;
	    }
		public int boardSize() {
			// TODO Auto-generated method stub
			return 0;
		}
		public int[][] boardCor() {
			// TODO Auto-generated method stub
			return null;
		}
	
}
