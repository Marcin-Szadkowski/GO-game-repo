package gogame.client.game;

import java.awt.Graphics;

import javax.swing.JFrame;

import gogame.client.gameElements.CustomBoardSize;
import gogame.client.transProtocol.TCP.TcpClient;

public class Game extends JFrame{

	
	public static int xCorStone;
	public static int yCorStone;
	
	public static int widthStep;
	public static int heightStep;
	public static int size=13;

	public Game() {
		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void paint(Graphics g) {
		//instance of class that append width and height attributes for chosen board
		CustomBoardSize boardSize = new CustomBoardSize();
		widthStep = boardSize.takenBoardSize(size)[0];
		heightStep = boardSize.takenBoardSize(size)[1];
		
		// draw the bacground board
		for(int i=0 ;i <= size ;i++) {
			g.drawLine(i*widthStep,heightStep,i*widthStep,size*heightStep);
		}
		
		
		for(int i=0 ;i <= size ;i++) {
			g.drawLine(widthStep,i*heightStep,size*widthStep,i*heightStep);
		}
		
		
		
		g.fillOval(50,50,50,50);
		
	}
  
	
	public static void main(String[] args) {
		Game game = new Game();
		TcpClient client = new TcpClient("localhost");
		try {client.initialize();}
		catch(Exception e){
			
		}
		System.out.println(client.input());
		
		
	}
}
