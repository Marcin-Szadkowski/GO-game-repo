package gogame.client.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		System.out.println(e.getX()+" "+e.getY());
		 x=e.getX();y=e.getY();
		 
		 for(ClickAreaSquare s  : panel.squares) {
				
				if(s.isInsideSquare(e.getX(), e.getY())){
				System.out.println(s.getX()+"  <3  "+ s.getY());
				parser.makeMove(s.getX(), s.getY());
								
					
					break;
				}
				
					
			}//System.out.println("liczba stone "+panel.pawns.size());
	//	 listCircles();
		
	}
	public  int getX () {return x;}
	public  int getY () {return y;}
	
	
	
	
	
	
	
	

}