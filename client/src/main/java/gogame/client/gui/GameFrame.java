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

public class GameFrame extends JFrame {
	
	static JFrame frame,introFrame;
    JButton buttonConnect,buttonSize9,buttonSize13,
    buttonSize19,buttonGameMulti,buttonGameSingle;
	static JButton buttonExit;
	static JButton buttonPass;
	JButton start;
    
    
    static JLabel labelTurn,labelYourStats,labelOponentStats;
    static JPanel panel1,panel2,panel3,panel4;
    static PawnPanel pawnPanel;
    
    public static boolean pass,exit;
   // public volatile static int size;
    
    
    
	public static void gameFrame(int size){
		
		 
	    frame = new JFrame();
		frame.setSize(800,800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);	
	//	frame.setLayout(new BorderLayout());
		
    	
	    
	     buttonExit = new JButton("EXIT");buttonExit.setBounds(500,725,80,30);
	     buttonPass = new JButton("PASS");
	     labelTurn = new JLabel("TURN");
	     labelYourStats = new JLabel("YourStats");
	     labelOponentStats = new JLabel("OponentStats");
	    
		panel1= new JPanel();
		panel2= new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel1.setBackground(Color.BLACK);
		panel1.setBounds(0,725,850,150);
		panel2.setBounds(0,725,250,40);//panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel3.setBounds(500,725,250,40);//panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel4.setBounds(750,725,250,40);//panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel1.setLayout(new GridLayout());
		pawnPanel = new PawnPanel();
		pawnPanel.boardSize=size;
		pawnPanel.addMouseListener(new GameMouseListener(pawnPanel));
		frame.add(panel1);
		frame.add(pawnPanel);
		
		panel1.add(panel2);
		panel1.add(panel3);
		panel1.add(panel4);
		
		//panel2.setLayout(new GridLayout());
		//panel3.setLayout(new GridLayout(1,3));
		//panel4.setLayout(new GridLayout());
		
	    panel2.add(labelYourStats);
	    panel3.add(buttonExit);
	    panel3.add(buttonPass);
	    panel3.add(labelTurn);
	    panel4.add(labelOponentStats);
	  
	    
	    frame.setVisible(true);
	    frame.repaint();
	    
	    
	    final TcpClient client = new TcpClient(); 
		
       
         
         buttonPass.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
        	 DataParser parser = new DataParser();
        	   pass=true;
        	   parser.receiveYouMoved("OPONENT_MOVED 1 3");
        	  // DataParser parser = new DataParser();
        	//  addPawn(95,87,Color.black);
        	   //parser.receiveConnected();
        	   
        	  
         		}
        });
        
       
         buttonExit.addActionListener(new ActionListener() {
             
             public void actionPerformed(ActionEvent e) {
            	 DataParser parser = new DataParser();
          	   parser.receiveOponentMoved("OPONENT_MOVED 1 1");
            	 exit=true;
             	}
            });
         
           
	}
	
	public static void addPawn(int x, int y,Color color) {
		
		 pawnPanel.addPawn(new Pawn(x,y,24,color));
		 pawnPanel.repaint();
	}
	public void removePawn(int x, int y ,Color color) {
		
		 pawnPanel.addPawn(new Pawn(x,y,24,color));
		 pawnPanel.repaint();
	}
	
	

}
