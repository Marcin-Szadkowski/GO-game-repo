package gogame.client.gameElements;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import gogame.client.game.ClickAreaSquare;

public class PawnPanel extends JPanel{
public List<Pawn> pawns = new LinkedList<Pawn>();
public List<ClickAreaSquare> squares = new LinkedList<ClickAreaSquare>();

	
	
	public void addPawn(Pawn pawn) {
		pawns.add(pawn);
		this.repaint();
	}
	public void removePawn(Pawn pawn) {
		pawns.remove(pawn);
		this.repaint();
	}
	
	public void addSquare(ClickAreaSquare square) {
		squares.add(square);
		this.repaint();
	}
	
	public void addTypeAreas() {

		BoardGui board = new BoardGui("Board19");
		int size= board.size;   
		int y= board.stepHeight;
		int x= board.stepWidth;
		
		for(int i=0 ; i<(size+1)*(size+1); i++) {	
			 squares.add(new ClickAreaSquare(boardCor(size,x,y)[i][0]-12+7,boardCor(size,x,y)[i][1]-12+30,24,24));
			}
	}
	
	public void paint(Graphics g) {
		
		BoardGui board = new BoardGui("Board19");
		int size= board.size;   
		int y= board.stepHeight;
		int x= board.stepWidth;
		for(int i=0; i<= size ; i++) {
			g.drawLine(boardCor(size,x,y)[i][0]+7, boardCor(size,x,y)[i][1]+30, boardCor(size,x,y)[(size+1)*(size+1)-size-1 +i][0]+7,boardCor(size,x,y)[(size+1)*(size+1)-size-1 +i][1]+30);
		}
		
		for(int i=0; i<= size ; i++) {
			g.drawLine(boardCor(size,x,y)[(size+1)*i][0]+7,boardCor(size,x,y)[(size+1)*i][1]+30,boardCor(size,x,y)[(size+1)*i+size][0]+7,boardCor(size,x,y)[(size+1)*i][1]+30);
		}
		
		for(Pawn p  : pawns) {
			
			p.draw(g);
			
				
		}
		for(ClickAreaSquare s  : squares) {
			
			s.draw(g);
			
				
		}	
		
	}
	
	public int[][] boardCor(int size,int widthStep,int heightStep) {
		int[][] boardCoordinates= new int[(size+1)*(size+1)][2];
		//coordinates of upper left corner  (0,heighStep)
		int iterator =0;
		for(int i=1 ; i<=size+1;i++)
		{
			
			for(int j=0 ; j<=size; j++)
			{
				boardCoordinates[iterator][0]=j*widthStep+100;
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