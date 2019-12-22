package gogame.client.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import gogame.client.connection.DataParser;
import gogame.client.game.Game;
import gogame.client.pawn.Pawn;
import gogame.client.pawn.PawnPanel;
import gogame.client.transProtocol.TcpClient;

public class GameMouseListener extends MouseAdapter{
	
	private PawnPanel panel;
	TcpClient client = new TcpClient();
	DataParser parser = new DataParser();
	
	public GameMouseListener (PawnPanel panel) {
		super();
		this.panel=panel;
	}
	public void listCircles()
	{   
		  System.out.println("-----------------------------------START");
		for(Pawn p : panel.pawns) {
		  System.out.println(p.getX()+" "+p.getY()+" "+p.getColor());System.out.println("\n");	
		}
		
		System.out.println("-----------------------------------STOP");
	}	
	public boolean checkIfExist(int x, int y) {
		for(Pawn p : panel.pawns) {
			 if((p.getX()==x)&&(p.getY()==y))return true;
			}
		return false;
	}
	int x,y;
	public void mouseClicked(MouseEvent e)
	{
		
		if (SwingUtilities.isRightMouseButton(e)) {
			
					//parser.receiveOponentMoved("OPPONENT_MOVED 5 5");
					
					parser.commandInterpreter("DELETE 1 1 2 2 3 3 4 3");
					//parser.receiveDelete("DELETE 1 1");
							parser.reDraw();	
					
						
					
			      
		}
		
		else if (SwingUtilities.isLeftMouseButton(e)) {
		
		 x=e.getX();y=e.getY();
		 
		 for(ClickAreaSquare s  : panel.squares) {
				
				if(s.isInsideSquare(e.getX(), e.getY())){
				
				parser.makeMove(s.getX(), s.getY());
				
				parser.reDraw();		
					break;
				}
		      }		
					
	    }//System.out.println("liczba stone "+panel.pawns.size());
	//	 listCircles();
		
	}
	public  int getX () {return x;}
	public  int getY () {return y;}
	
	
	
	
	
	
	
	

}