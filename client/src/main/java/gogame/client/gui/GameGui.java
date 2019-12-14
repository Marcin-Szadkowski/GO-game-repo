package gogame.client.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gogame.client.connection.DataParser;
import gogame.client.game.Game;
import gogame.client.pawn.Pawn;
import gogame.client.pawn.PawnPanel;
import gogame.client.transProtocol.TcpClient;
import gogame.client.transProtocol.TransferProtocol;

public class GameGui extends JFrame {
	public static boolean gameStarted=false;
	public static boolean gameLasts=false;
	
     

     
     public static int turn;
     public static boolean pass=false;
     public static boolean connect = false;
     public static boolean exit = false;
     public volatile static boolean singlePlayer =false;
     public volatile static boolean multiPlayer = false;
     public static int size=9;
     public static Color color;
     public static String gameType;
     public static boolean makeMove=false;
     public static int x,y;
     public static Color yourColor=Color.BLACK; 
     public static Color oponentColor=Color.GREEN; 
     
     
public void youMoved(int x, int y) {
	GameFrame.pawnPanel.addPawn(new Pawn(x,y,24,yourColor));
	System.out.println("doda³em twoj nowy stone na panel");
	
}
public void moveAccepted() { makeMove=true;}


public void oponentMoved(int x,int y) {
	
	GameFrame.pawnPanel.addPawn(new Pawn(x,y,24,oponentColor));
	System.out.println("doda³em  nowy stone przeciwnika na panel");

}


public void quit() {}
public void gameStarted() {System.out.println("GAME STARTED");}
public void delete(int[][] tab) {}
public void getPrisoners(int[] tab) {}
public void yourTurn() {};
public void oponentTurn() {};
public void connected() {
	connect=true;
	System.out.println("info  zmienia connect w gui");
}
public void setVar(int[] tab) {
	x=tab[0];
	y=tab[1];
}



	
public static void inicialize() {
	
	  boolean status = false;
      GameGui gameGUI= new GameGui();
      DataParser parser = new DataParser();
     IntroFrame introFrame = new IntroFrame();
     GameFrame  gameFrame = new GameFrame();
  
     introFrame.openingFrame();
     
     //connect =introFrame.connect;
    
    
      while(connect!=true) {
    	
    	//System.out.println("waiting for connect");
      }
      //gdy otrzymam info od serwera ze gracz po³¹czony 
      System.out.println("client connected");
      
      //wysy³am z palca dane do servera  przez metody w klasie DataParser 
      parser.sendType("MULTI");
      parser.sendSize(9);
      parser.findGame();
      
      /*  while(introFrame.gameChosen()!=true) {
    	 
      }
      size=IntroFrame.size;
	   gameType=IntroFrame.gameType;
      System.out.println("Chosen game settings");
      System.out.println(size + " "+ gameType);
      while(status!=true) {
    	 
    	  if((introFrame.size!=0)&&((introFrame.singlePlayer !=false) ||
    			  (introFrame.multiPlayer!=false))) {
    		    size=introFrame.size;
    		    gameType=introFrame.gameType;
    		//  System.out.println(size);
    		 status=true;	  
    		// parser.sendSize(size);
    		// parser.sendType(gameType);
    	  }
    	//System.out.println("waiting !!! add size and type of a game");
      }
      
      System.out.println("Chosen game settings");
      
    
     
   //  GameFrame.size=size;*/
  /*  
     gameFrame.gameFrame(size);
     
     
    
	   
	   System.out.println("poszlo");
     //PÊTLA GRY
     while(true) {
    	// parser.runreceiveYouMoved("YOU_MOVED 2 2");
    	// 
    	 GameFrame.pawnPanel.repaint();
    	 //if(makeMove==true) {
    	//	 gameGUI.youMoved(x,y);makeMove=false;
    	//	 gameFrame.repaint();
    	// }
    	 
     }
     // wysy³am  conector.move(5)    conector przerabia na  MOVE 5  i na server;
   
    */
		
      //*size=IntroFrame.size;
	/*  gameType=IntroFrame.gameType;
      introFrame.setVisible(false);
      gameFrame.gameFrame(9);
     // parser.sendType("MULTI");
     // parser.sendSize(9);
     // parser.findGame();
      //PÊTLA GRY
      while(true) {
     	gameFrame.pawnPanel.repaint();
      }*/
      
      
      
	}
	
}