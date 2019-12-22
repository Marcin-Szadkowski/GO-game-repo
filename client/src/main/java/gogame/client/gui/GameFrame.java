package gogame.client.gui;

import java.awt.Color;
import java.awt.Graphics;
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

public class GameFrame extends JFrame {
	
	static JFrame frame,introFrame;
    JButton buttonConnect,buttonSize9,buttonSize13,
    buttonSize19,buttonGameMulti,buttonGameSingle;
	static JButton buttonExit;
	static JButton buttonPass;
	JButton start;
    
    
    static JLabel labelTurn,labelYourPrisoners,labelOpponentPrisoners,labelYourPoints,labelOpponentPoints,labelWinner;
    static JPanel panel1,panel2,panel3,panel4;
    static PawnPanel pawnPanel;
    
    public static boolean pass,exit;
    public volatile static int size;
    
    
    
	public static void gameFrame(int size){
		
		 
	    frame = new JFrame();
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 SwingUtilities.updateComponentTreeUI(frame);
		

		
	//	frame.setLayout(new BorderLayout());
		
    	
	    
	     buttonExit = new JButton("EXIT");buttonExit.setBounds(500,725,80,30);
	     buttonPass = new JButton("PASS");
	     labelTurn = new JLabel("TURN");
	     labelYourPrisoners = new JLabel("YOUR PRISONERS");	
	     labelYourPoints = new JLabel("YOUR POINTS");
	     labelOpponentPoints= new JLabel("OPPONENT POINTS");
	     labelOpponentPrisoners = new JLabel("OPPONENT PRISONERS");
	     labelWinner = new JLabel("Winner");
	    
		panel1= new JPanel();
		panel2= new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel1.setBackground(Color.BLACK);
		panel1.setBounds(0,725,850,150);
		panel2.setBounds(50,725,250,40);//panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel3.setBounds(500,725,250,40);//panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel4.setBounds(750,725,250,40);//panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	//	panel1.setLayout(new GridLayout());
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
	
	public static void addPawn(int x, int y,Color color) {
		
		 pawnPanel.addPawn(new Pawn(x,y,24,color));

		
	}
	public void removePawn(int tab[][]) {
		
		 pawnPanel.removePawn(tab);
	

	}
	public void reDraw() {
		pawnPanel.repaint();
	}
	public void  labelTurnUpdate(String s) {
		labelTurn.setText(s);
	}
	public void  labelYourStatsUpdate(String s) {
		labelYourPrisoners.setText("YOUR PRISONERS:" +s);
	}
	public void  labelOpponentStatsUpdate(String s) {
		labelOpponentPrisoners.setText("OPPONENT PRISONERS:" +s);
	}
	public void  labelOpponentPointsUpdate(String s) {
		labelYourPoints.setText("OPPONENT POINTS:" +s);
	}
	public void  labelYourPointsUpdate(String s) {
		labelOpponentPoints.setText("YOUR POINTS:" +s);
	}
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
	//odœwierzenie kropek np
	public void refresh() {
		frame.repaint();
	}
	
	
	
	
	
	

}
