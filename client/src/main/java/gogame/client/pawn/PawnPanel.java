package gogame.client.pawn;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javax.swing.JPanel;

import gogame.client.board.BoardGui;
import gogame.client.connection.DataParser;
import gogame.client.gui.ClickAreaSquare;

public class PawnPanel extends JPanel{
	
	
public List<Pawn> pawns = new LinkedList<Pawn>();
public List<ClickAreaSquare> squares = new LinkedList<ClickAreaSquare>();
public static int type;
public int boardSize;
DataParser parser = new DataParser();

	
	
	public void addPawn(Pawn pawn) {
		pawns.add(pawn);
		this.repaint();
	}
	public void removePawn(int tab[][]) {
		int temp=0;
		int k=0;
		while(k!=tab.length) {
		
			//stosuj� wzorzec iterator aby zapobiec ConcurrentModificationException
			for (Iterator<Pawn> it = pawns.iterator(); it.hasNext();) {
	            Pawn next = it.next();
	            if (next.getX()==tab[k][0] && next.getY()==tab[k][1]  )  {
	                it.remove();
	              
	            }  
	        }this.repaint(); 
			
		k++;
		}
		this.repaint();
		

		
		
	}
	
	public void addSquare(ClickAreaSquare square) {
		squares.add(square);
		this.repaint();
	}
	
	
	
	public void paint(Graphics g) {
		
		
		// przypisanie danych z pierwszego frame co do typuy rogrywki
		
		
		BoardGui board = new BoardGui(boardSize);
		
		int size=board.size();
		int x=board.stepWidth();
		int y=board.stepHeight();
		
		
		
		for(int i=0; i<= size ; i++) {
			g.drawLine(boardCor(size,x,y)[i][0]+7, boardCor(size,x,y)[i][1]+30, boardCor(size,x,y)[(size+1)*(size+1)-size-1 +i][0]+7,boardCor(size,x,y)[(size+1)*(size+1)-size-1 +i][1]+30);
		}
		
		for(int i=0; i<= size ; i++) {
			g.drawLine(boardCor(size,x,y)[(size+1)*i][0]+7,boardCor(size,x,y)[(size+1)*i][1]+30,boardCor(size,x,y)[(size+1)*i+size][0]+7,boardCor(size,x,y)[(size+1)*i][1]+30);
		}
		
		//square width i height na 24 na sztywniaka  
		for(int i=0 ; i<(size+1)*(size+1); i++) {	
			 squares.add(new ClickAreaSquare(boardCor(size,x,y)[i][0]-12+7,boardCor(size,x,y)[i][1]-12+30,24,24));
			}
		
		
		
		for(Pawn p  : pawns) {
			
			p.draw(g);
			
				
		}
		/*for(ClickAreaSquare s  : squares) {
			
			s.draw(g);
			
				
		}	*/
		
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
			//	System.out.print(" ["+boardCoordinates[iterator][0]+" , "+boardCoordinates[iterator][1]+" ]" );
				iterator ++;
			}
		//	System.out.println("\n");
				
		}
		//System.out.println(boardCoordinates);
		return boardCoordinates;
	}
	
	
	
}