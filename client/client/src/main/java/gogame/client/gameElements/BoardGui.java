package gogame.client.gameElements;

import java.awt.Graphics;

public class BoardGui implements BoardInterface {
	public int widthStep=100;
	public int heightStep=100;
	public int size=9;
	public int[][] boardCoordinates= new int[(size+1)*(size+1)][2];
	
	public void background() {
		Graphics g = null ;
		//instance of class that append width and height attributes for chosen board
		CustomBoardSize boardSize = new CustomBoardSize();
		widthStep = boardSize.takenBoardSize(size)[0];
		heightStep = boardSize.takenBoardSize(size)[1];
		
		
		// draw the bacground board
		//vertical lines
		for(int i=0 ;i <= size ;i++) {
			g.drawLine(i*widthStep,heightStep,i*widthStep,size*heightStep);
				
		}
		
		//horizontal lines
		for(int i=0 ;i <= size ;i++) {
			g.drawLine(widthStep,i*heightStep,size*widthStep,i*heightStep);
				
		}
		
			
		g.fillOval(50,50,50,50);
		//return g;
		
	}
	

	public int boardSize() {
		
		return 0;
	}

	public int boardStepWidth() {
		
		return 100;
	}

	public int boardStepHeight() {
		
		return 100;
	}

	//method returns array of  (x,y) coordinates of each line cross - possible stone position
	public int[][] boardCor() {
		//coordinates of upper left corner  (0,heighStep)
		int iterator =0;
		for(int i=1 ; i<=size+1;i++)
		{
			
			for(int j=0 ; j<=size; j++)
			{
				boardCoordinates[iterator][0]=j*widthStep;
				boardCoordinates[iterator][1]=heightStep*i;
				System.out.print(" ["+boardCoordinates[iterator][0]+" , "+boardCoordinates[iterator][1]+" ]" );
				iterator ++;
			}
			System.out.println("\n");
				
		}
		System.out.println(boardCoordinates);
		return boardCoordinates;
	}

}
