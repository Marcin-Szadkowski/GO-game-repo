package gogame.client.game;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gogame.client.gameElements.Pawn;
import gogame.client.gameElements.PawnPanel;

public class GameMouseListener extends MouseAdapter{
	
	private PawnPanel panel;
	
	public GameMouseListener (PawnPanel panel) {
		super();
		this.panel=panel;
	}
	
	
	int x,y;
	public void mouseClicked(MouseEvent e)
	{
		System.out.println(e.getX()+" "+e.getY());
		 x=e.getX();y=e.getY();
		 
		 for(ClickAreaSquare s  : panel.squares) {
				
				if(s.isInsideSquare(e.getX(), e.getY())){
					panel.addPawn(new Pawn(s.getX(),s.getY(),s.getHeight(),Color.black));
				}
				
					
			}
		
	}
	public  int getX () {return x;}
	public  int getY () {return y;}
	
	
	
	
	
	
	
	

}