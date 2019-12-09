package gogame.client.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gogame.client.gameElements.Board9;
import gogame.client.gameElements.BoardGui;
import gogame.client.gameElements.CustomBoardSize;
import gogame.client.gameElements.Pawn;
import gogame.client.gameElements.PawnPanel;
import gogame.client.transProtocol.TCP.TcpClient;

public class Game extends JFrame{

	
	private int gameType;
	private int size;
	private int turn=1;
	
	// nwm co to headless xd
	public Game(int gameType, int size) throws HeadlessException {
		super();
		this.gameType = gameType;
		this.size = size;
	}


	public void frameGame() {
		
	  //  getContentPane().addMouseListener(new GameMouseListener());
		setSize(900,900);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		PawnPanel panel = new PawnPanel();
		setContentPane(panel);
		panel.addMouseListener(new GameMouseListener(panel));
		panel.addTypeAreas();
		setResizable(false);
		
		setVisible(true);
		
	}
	public void Action() {
		
		
		
		
	}
	
	public static void main(String[] args) {
	
        OpeningWindow data = new OpeningWindow();
		Game game = new Game(data.sendChosenSettings()[1],data.sendChosenSettings()[0]);
		game.frameGame();
		game.Action();
	 
		
	}
}
