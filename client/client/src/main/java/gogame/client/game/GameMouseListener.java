package gogame.client.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameMouseListener extends MouseAdapter{
	int x,y;
	public void mouseClicked(MouseEvent e)
	{
		System.out.println(e.getX()+" "+e.getY());
		 x=e.getX();y=e.getY();
	}
	public  int getX () {return x;}
	public  int getY () {return y;}

}
