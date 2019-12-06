package gogame.client.game;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gogame.client.gameElements.BoardGui;
import gogame.client.gameElements.CustomBoardSize;
import gogame.client.transProtocol.TCP.TcpClient;

public class Game extends JFrame{

	
	public static int xCorStone;
	public static int yCorStone;
	
	public static int widthStep;
	public static int heightStep;
	public static int size=9;

	
	private static JFrame frame;
	private static JPanel gamePanel;
	
	public Game() {
		
	    getContentPane().addMouseListener(new GameMouseListener());
		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	//	setResizable(false);
		
		gamePanel = new JPanel();
		gamePanel.setSize(1000,1000);
		gamePanel.setVisible(true);
		
		frame.add(gamePanel);
		frame.setContentPane(gamePanel);
		//gamePanel.addMouseListener(new GameMouseListener());
		frame.setVisible(true);
		
		
	}
	//cala metdoa do przeniesienia do boardgui---------------------------------------------------!!!
	public void paint(Graphics g) {
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
		
			
		
	
	}
	
	public static void main(String[] args) {
	/*	Game game = new Game();
		TcpClient client = new TcpClient("localhost");
		try {client.initialize();}
		catch(Exception e){
			
		}
		System.out.println(client.input());
		
		*/
		
		
		BoardGui board = new BoardGui();
		board.boardCor();

		
		
		Game game = new Game();
		
	}
}
