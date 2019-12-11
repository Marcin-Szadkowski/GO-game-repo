package gogame.client.gui;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gogame.client.game.pawn.Pawn;
import gogame.client.game.pawn.PawnPanel;
import gogame.client.game.performance.Game;
import gogame.client.transProtocol.TcpClient;


public class GameMouseListener extends MouseAdapter{
	
	private PawnPanel panel;
	TcpClient client = new TcpClient();
	
	public GameMouseListener (PawnPanel panel) {
		super();
		this.panel=panel;
	}
	public void listCircles()
	{   
		  System.out.println("-----------------------------------START");
		for(Pawn p : panel.pawns) {
		  System.out.println(p.getX()+" "+p.getY());System.out.println("\n");	
		}
		
		System.out.println("-----------------------------------STOP");
	}	
	
	int x,y;
	public void mouseClicked(MouseEvent e)
	{
		System.out.println(e.getX()+" "+e.getY());
		 x=e.getX();y=e.getY();
		 
		 for(ClickAreaSquare s  : panel.squares) {
				
				if(s.isInsideSquare(e.getX(), e.getY())){
					
					
					panel.addPawn(new Pawn(s.getX(),s.getY(),24,Color.black));
					Game game= Game.getInstance();
					game.xMoved=s.getX(); game.yMoved=s.getY();
					
					// 7 oraz 30 to dziwne przesuniêcia 
					// odejmujemy 12 bo kwadraty rysowalismy na przecieciach wiec naturalnie punkt o kotry nam chodzi jest w srodku kwadratu
					// wiec musimy zrobic szift o polowe width czyli 24/2 =12 
					int setX=s.getX()+12-7;
					int setY=s.getY()+12-30;
					
					
					panel.coordinatesScaleConverter(setX, setY, panel.boardSize);
					String tempx=String.valueOf(panel.coordinatesScaleConverter(setX, setY, panel.boardSize)[0]);
					String tempy=String.valueOf(panel.coordinatesScaleConverter(setX, setY, panel.boardSize)[1]);
					
					//client.sendMessage("MOVE "+tempx+" "+tempy);
					System.out.println("MOVE "+tempx+" "+tempy);break;   // break za³atwi¹ sprawê tworzenia multi pawnów z fora
				}
				
					
			}System.out.println(panel.pawns.size()); listCircles();
		
	}
	public  int getX () {return x;}
	public  int getY () {return y;}
	
	
	
	
	
	
	
	

}
