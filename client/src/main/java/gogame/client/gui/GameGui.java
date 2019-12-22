package gogame.client.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gogame.client.connection.DataParser;
import gogame.client.game.Game;
import gogame.client.pawn.Pawn;
import gogame.client.pawn.PawnPanel;
import gogame.client.transProtocol.TcpClient;
import gogame.client.transProtocol.TransferProtocol;

public class GameGui extends JFrame {
	public volatile static boolean gameStartedStatus=false;
	public volatile static boolean buildBoard=false;
	
     

     
     public static int turn;
     public static boolean pass=false;
     public  volatile static boolean chosen=false;
     public static boolean connect = false;
     public static boolean exit = false;
     public volatile static boolean singlePlayer =false;
     public volatile static boolean multiPlayer = false;
     public static int size=9;
     public static Color color;
     public static String gameType;
     public static boolean makeMove=false;
     public static int x,y;
     public static Color yourColor; 
     public static Color oponentColor; 
     public static GameFrame gameFrame;
     public  static IntroFrame introFrame;
     
     public volatile static String yourPrisoners,oponentPrisoners;
     
     GameGui gameGui;

public void youMoved(int x, int y) {
	GameFrame.pawnPanel.addPawn(new Pawn(x,y,24,yourColor));
	System.out.println("doda³em twoj nowy stone na panel");
	
}
public void getPrisoners(String p1,String p2) {
	yourPrisoners=p1;
	oponentPrisoners=p2;
}


public  void oponentMoved(int x,int y) {
	
	GameFrame.pawnPanel.addPawn(new Pawn(x,y,24,oponentColor));
	System.out.println("doda³em twoj nowy stone na panel");
	

}
public void refresh() {
	gameFrame.refresh();
}

public void gameStarted(String s) {
	gameStartedStatus=true;
	if(s.equals("black")) {
		yourColor=Color.black;
		oponentColor=Color.white;
	}
	else if(s.equals("white")) {
		yourColor=Color.white;
		oponentColor=Color.black;
	}
}


public void delete(int tab[][]) {
	
	gameFrame.pawnPanel.removePawn(tab);
}


public void quit() {}

public void getPrisoners(int[] tab) {
	gameFrame.labelYourStatsUpdate(String.valueOf(tab[0]));
	gameFrame.labelOpponentStatsUpdate(String.valueOf(tab[1]));
}
public void yourTurn(String s) {
	gameFrame.labelTurnUpdate(s);
};

public void connected() {
	connect=true;
	System.out.println("info  zmienia connect w gui");
}
public void gameEnded(String yPoints,String oPoints) {
	gameFrame.labelYourPointsUpdate(yPoints);
	gameFrame.labelOpponentPointsUpdate(oPoints);
	gameFrame.showWinner();
	
	
}

	
public static void inicialize() {
	
	  boolean status = false;
     
      DataParser parser = new DataParser();
     gameFrame = new GameFrame();
     introFrame = new IntroFrame();
     
    // gameFrame = new GameFrame();
  
    // introFrame.openingFrame();

     
   //  introFrame.setVisible(false);
     
     parser.sendSize(9);
     parser.sendType("MULTI");
	   parser.findGame();
	   introFrame.setVisible(false);
	   gameFrame.gameFrame(9);
	  
	 
  /*   
     while(connect!=true) {}
    while(chosen!=true) {
   	  
   	  chosen=introFrame.gameChosen();
   	 
     }
    System.out.println("Chosen game settings");
    gameFrame.gameFrame(introFrame.size);
    introFrame.setVisible(false);
    
     parser.sendSize(introFrame.size);
     parser.sendType(introFrame.gameType);
	 parser.findGame();
    // if(gameStartedStatus==true) GameFrame.gameFrame(introFrame.size);
	
	  */ 
	  
	   //System.out.println(yourColor);
	
	   
      
      
      
	}
	
}