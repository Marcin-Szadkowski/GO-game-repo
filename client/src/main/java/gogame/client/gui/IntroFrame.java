package gogame.client.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gogame.client.pawn.PawnPanel;

public class IntroFrame  extends JFrame{

	
		
		
	
	static JFrame introFrame;
    JButton buttonConnect,buttonSize9,buttonSize13,
    buttonSize19,buttonGameMulti,buttonGameSingle;
 

    public static int size;
    public static boolean singlePlayer=false,multiPlayer=false,connect;
    public static String gameType;
    
    
	 public void openingFrame() {
	    	
	    	

		    introFrame = new JFrame();
		    introFrame.setSize(500,500);
		    introFrame.setVisible(true);
		    introFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    introFrame.setVisible(true);	
	    	
			 buttonConnect = new JButton("connect ");
		     buttonSize9 = new JButton("9");
		     buttonSize13 = new JButton("13");
		     buttonSize19 = new JButton("19");
		     buttonGameMulti = new JButton("Multiplayer");
		     buttonGameSingle = new JButton("Singleplayer");
		     
		     introFrame.setLayout(new GridLayout(2,3));
		     introFrame.add(buttonConnect);
		     introFrame.add(buttonSize9);
		     introFrame.add(buttonSize13);
		     introFrame.add(buttonSize19);
		     introFrame.add(buttonGameMulti);
		     introFrame.add(buttonGameSingle);
		     introFrame.setVisible(true);
		     
	 buttonSize9.addActionListener(new ActionListener() {
	             
	             public void actionPerformed(ActionEvent e) {
	            	 
	            	 size=9;
	            	 System.out.println("wybrano 9");
	            	
	            	 
	             		}
	            });
	         buttonSize13.addActionListener(new ActionListener() {
	             
	             public void actionPerformed(ActionEvent e) {
	            	 System.out.println("wybrano 13");
	            	 size =13;
	             		}
	            });
	         buttonSize19.addActionListener(new ActionListener() {
	             
	             public void actionPerformed(ActionEvent e) {
	            	 size =19;
	            	// System.out.println("wybrano 19");
	             		}
	            });
	         buttonGameSingle.addActionListener(new ActionListener() {
	             
	             public void actionPerformed(ActionEvent e) {
	            	 
	            	
	             	 singlePlayer=true;
	             	 gameType="SINGLE";
	             	
	             		}
	            });
	         buttonGameMulti.addActionListener(new ActionListener() {
	             
	             public void actionPerformed(ActionEvent e) {
	            	
	            	 multiPlayer=true;
	            	 gameType="MULTI";
	            
	            	 
	            	 
	             		}
	            });
	         buttonConnect.addActionListener(new ActionListener() {
	             
	             public void actionPerformed(ActionEvent e) {
	            	   connect =true;
	            	 
	             		}
	            });
	         
	         
		     
	    	
	    } 
	 
	// -----------------------------------------------------------------------------------metody
	 public boolean gameChosen() {
			if((size!=0)  && ((singlePlayer==true) || (multiPlayer==true))) {return true;}
			else return false;
			
			
		}
		
	
}
