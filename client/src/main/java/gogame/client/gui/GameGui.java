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
import gogame.client.pawn.Pawn;
import gogame.client.pawn.PawnPanel;
import gogame.client.transProtocol.TcpClient;
import gogame.client.transProtocol.TransferProtocol;

/**
 * Klasa fasada obs�uguj�ca po��czenia mi�dzy DataParser a wszystkimi klasami reprezentujacymi GUI.
 * Klasa zawiera implemtntuje schemat od wyboru planszy do rozpocz�cia rozgrywki przez gracza @{@link #inicialize()}
 * @author wojciech
 *
 */
public class GameGui extends JFrame {
	
	// zmienne pomocne w determinowaniu stanu gry
	 public volatile static boolean gameStartedStatus=false;
	 public volatile static boolean buildBoard=false;
     public static int turn;
     public static boolean pass=false;
     public  volatile static boolean chosen=false;
     public static boolean connect = false;
     public static boolean exit = false;
     public static int size;
     public static Color color;
     public static String gameType;
     public static boolean makeMove=false;
     public static Color yourColor; 
     public static Color oponentColor; 
     public static GameFrame gameFrame;
     public  static IntroFrame introFrame; 
     public volatile static String yourPrisoners,oponentPrisoners;
     
     GameGui gameGui;

/**
 * Metoda przekazuj�ca informacje o ruchu gracza do
 *  klasy obs�uguj�cej dodawanie element�w gry
 * @param x
 * @param y
 */
public void youMoved(int x, int y) {
	GameFrame.pawnPanel.addPawn(new Pawn(x,y,24,yourColor));
	System.out.println("doda�em twoj nowy stone na panel");
	
}
public void getPrisoners(String p1,String p2) {
	gameFrame.labelYourPrisonersUpdate(p1);
	gameFrame.labelOpponentPrisonersUpdate(p2);
}

/**
 * Metoda przekazuj�ca informacje o ruchu przeciwnika do
 *  klasy obs�uguj�cej dodawanie element�w gry
 * @param x
 * @param y
 */
public  void oponentMoved(int x,int y) {
	
	GameFrame.pawnPanel.addPawn(new Pawn(x,y,24,oponentColor));
	System.out.println("doda�em twoj nowy stone na panel");
	

}
/**
 * Metoda wywo�uj�ca od�wierzenie wy�wietlanych infromacji na obiekcie klasy {@link #gameFrame}
 * implementuj�cej panel gry.
 */
public void refresh() {
	gameFrame.refresh();
}

/**
 * Metoda przypisuj�ca graczowi odpowidni kolor stone'�w
 * na podstawie infromacji z serwera
 * @param s
 */
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

/**
 * Metoda kt�ra przekazuje tablice wsp�rzenych obiekt�w, kt�re nale�y usun�c
 * Tablica przekazywana jest do obiektu klasy obs�uguj�cej rysowanie obiekt�w
 */
public void delete(int tab[][]) {
	
	gameFrame.pawnPanel.removePawn(tab);
}


public void quit() {}

/**
 * Metoda uaktualniaj�ca liczb� Prisoners dla ka�dego gracza 
 * @param tab
 */

/**
 * Metoda informuj�ca o bierz�cym ruchu 
 * @param s
 */
public void yourTurn(String s) {
	gameFrame.labelTurnUpdate(s);
};

public void connected() {
	connect=true;
	System.out.println("info  zmienia connect w gui");
}
/**
 * Metoda kt�ra po uko�czeniu rozgrywki przekazuje ostateczne punkty dopanelu gry
 * @param yPoints
 * @param oPoints
 */
public void gameEnded(String yPoints,String oPoints) {
	gameFrame.labelYourPointsUpdate(yPoints);
	gameFrame.labelOpponentPointsUpdate(oPoints);
	GameFrame.showWinner();
	
	
}

/**
 * G��wna metoda inicjalizuj�ca wybory pocz�tkowe rozgrywki
 * W metodzie wywo�ywane s� metody tworz�ce g��wne obiekty graficzne rozgrywki wraz z plansz� gry	
 */
public static void inicialize() {
	
	  boolean status = false;
     
      DataParser parser = new DataParser();
     gameFrame = new GameFrame();
     introFrame = new IntroFrame();
     
    // gameFrame = new GameFrame();
  
    // introFrame.openingFrame();

     
   //  introFrame.setVisible(false);
     
     parser.sendSize(19);
     parser.sendType("MULTI");
	  parser.findGame();
	  // pamietac o ustaleniu sie bo karzystam w parser
	  size=19;
	   introFrame.setVisible(false);
	   gameFrame.gameFrame(19);
	  
	 
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