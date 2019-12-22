package gogame.client.gui;

import java.awt.Color;

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

/**
 * Klasa tworz¹cja obiekt typu JFrame, na którym rozgrywa siê gra.
 * @author wojciech
 *
 */
public class GameFrame extends JFrame {
	
	//inicjalizowanie komponentów JFrame'a
	static JFrame frame;
    static JButton buttonConnect,buttonSize9,buttonSize13,buttonSize19,buttonGameMulti,buttonGameSingle, buttonExit,buttonPass;
    static JLabel labelTurn,labelYourPrisoners,labelOpponentPrisoners,labelYourPoints,labelOpponentPoints,labelWinner;
    static JPanel panel1,panel2,panel3,panel4;
    static PawnPanel pawnPanel;
    
    public static boolean pass,exit;
    public volatile static int size;
    
    /**
     * Metoda inicjalizuj¹ca obiekt typu JFrame na którym rozgrywa siê gra  
     * @param size
     */
	public static void gameFrame(int size){
		
		 
	   frame = new JFrame();
	   frame.setSize(800,800);
	   frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	   SwingUtilities.updateComponentTreeUI(frame);

	   buttonExit = new JButton("EXIT");buttonExit.setBounds(500,725,80,30);
	   buttonPass = new JButton("PASS");
	   labelTurn = new JLabel("TURN");
	   labelYourPrisoners = new JLabel("YOUR PRISONERS 0");	
	   labelYourPoints = new JLabel("YOUR POINTS");
	   labelOpponentPoints= new JLabel("OPPONENT POINTS");
	   labelOpponentPrisoners = new JLabel("OPPONENT PRISONERS 0");
	   labelWinner = new JLabel("Winner");
	    
		panel1= new JPanel();
		panel2= new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel1.setBackground(Color.BLACK);
		panel1.setBounds(0,725,850,150);
		panel2.setBounds(50,725,250,40);
		panel3.setBounds(500,725,250,40);
		panel4.setBounds(750,725,250,40);
	
		pawnPanel = new PawnPanel();
		pawnPanel.boardSize=size;
		pawnPanel.addMouseListener(new GameMouseListener(pawnPanel));
		frame.add(panel1);
		frame.add(pawnPanel);
		
		panel1.add(panel2);
		panel1.add(panel3);
		panel1.add(panel4);

	    panel2.add(labelYourPrisoners);
	    panel2.add(labelYourPoints);
	    panel3.add(buttonExit);
	    panel3.add(buttonPass);
	    panel3.add(labelWinner);
	    panel3.add(labelTurn);
	    panel4.add(labelOpponentPrisoners);
	    panel4.add(labelOpponentPoints);
	  
	    labelOpponentPoints.setVisible(false);
	    labelYourPoints.setVisible(false);
	    labelWinner.setVisible(false);
	    
	    frame.setBackground(Color.DARK_GRAY);
	    frame.setVisible(true);
	    
	    final DataParser parser = new DataParser();
	
	    
         buttonPass.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	
        	  parser.makePass();
    
         		}
        });
        
       
         buttonExit.addActionListener(new ActionListener() {
             
             public void actionPerformed(ActionEvent e) {
          	parser.makeQuit();
             	}
            });
         
           
	}
	
	/**
	 * Metoda pobieraj¹ca wspó³rzêdne oraz kolor,
	 *  na podstawie których tworzy nowego stone'a na planszy
	 * @param x
	 * @param y
	 * @param color
	 */
	public static void addPawn(int x, int y,Color color) {
				
	 pawnPanel.addPawn(new Pawn(x,y,24,color));
			
	}
	
	
	/**
	 * Metoda pobieraj¹ca tablicê wspó³rzêdnych,
	 *  na podstawie których usuwa stone'y z planszy
	 * @param x
	 * @param y
	 * @param color
	 */
	public void removePawn(int tab[][]) {
				
	pawnPanel.removePawn(tab);
			
	}
	
	/**
	 * Metoda uaktualnia dane na obiekcie labelTurn
	 * @param s info do przekazania
	 */
	public void  labelTurnUpdate(String s) {
	labelTurn.setText(s);
	}
			
			
	/**
	 * Metoda uaktualnia dane na obiekcie labelYourPrisoners
	 * @param s info do przekazania
	 */
	public void  labelYourPrisonersUpdate(String s) {
		labelYourPrisoners.setText("YOUR PRISONERS: " +s);
	}
	/**
	 * Metoda uaktualnia dane na obiekcie labelOpponentPrisoners
	 * @param s info do przekazania
	 */
	public void  labelOpponentPrisonersUpdate(String s) {
		labelOpponentPrisoners.setText("OPPONENT PRISONERS: " +s);
	}
	/**
	 * Metoda uaktualnia dane na obiekcie labelYourPoints
	 * @param s info do przekazania
	 */
	public void  labelOpponentPointsUpdate(String s) {
		labelYourPoints.setText("OPPONENT POINTS: " +s);
	}
	
	/**
	 * Metoda uaktualnia dane na obiekcie labelOpponentPoints
	 * @param s info do przekazania
	 */
	public void  labelYourPointsUpdate(String s) {
		labelOpponentPoints.setText("YOUR POINTS: " +s);
	}
	
	/**
	 * Metoda ustala widocznosc obiektów po zakoñczeniu rozgrywki
	 * 
	 */
	public static void showWinner() {
		labelYourPrisoners.setVisible(false);
		labelOpponentPrisoners.setVisible(false);
		labelWinner.setVisible(true);
		labelYourPoints.setVisible(true);
		labelOpponentPoints.setVisible(true);
		buttonExit.setVisible(false);
		buttonPass.setVisible(false);
		labelTurn.setVisible(false);
		
	}
	
	/**
	 * Metoda updatuje dane z panelu gry
	 */
	public void refresh() {
		frame.repaint();
	}
	

}
