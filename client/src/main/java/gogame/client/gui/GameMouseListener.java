package gogame.client.gui;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import gogame.client.connection.DataParser;
import gogame.client.pawn.Pawn;
import gogame.client.pawn.PawnPanel;
import gogame.client.transProtocol.TcpClient;

/**
 * Klasa rozszerzająca  MouseAdapter, implementująca metody
 * przez które gracz wysyła informacje o ruchu, który zamierza wykonać
 * 
 * @author wojciech
 *
 */
public class GameMouseListener extends MouseAdapter{
	
	private PawnPanel panel;
	TcpClient client = new TcpClient();
	DataParser parser = new DataParser();
	
	public GameMouseListener (PawnPanel panel) {
		super();
		this.panel=panel;
	}
	
	int x,y;
	public void mouseClicked(MouseEvent e)
	{
		
		if (SwingUtilities.isLeftMouseButton(e)) {
		
		 x=e.getX();y=e.getY();
		 
		 for(ClickAreaSquare s  : panel.squares) {
				
				if(s.isInsideSquare(e.getX(), e.getY())){
				
				parser.makeMove(s.getX(), s.getY());
				
				parser.reDraw();		
					break;
				}
		      }		
					
	    }

		
	}
	public  int getX () {return x;}
	public  int getY () {return y;}
	
	

}