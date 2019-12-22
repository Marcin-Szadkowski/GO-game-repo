package gogame.client.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Klasa zawieraj¹ca metody parametryzuj¹ce stan szukanej rozgrywki
 * @author wojciech
 *
 */
public class IntroFrame  extends JFrame{
	
	
	static JFrame introFrame;
    JButton buttonFindGame,buttonSize9,buttonSize13,
    buttonSize19,buttonGameMulti,buttonGameSingle;
 

    public static int size;
    public volatile static boolean singlePlayer=false,multiPlayer=false,findGame,chosen=false;
    public static String gameType="MULTI";
    
  /**
   * Metoda tworz¹ca obiekt typu Jframe na którym gracz wybiera parammetry gry które go interesuj¹
   */
	 public void openingFrame() {
	    	
	    	

		    introFrame = new JFrame();
		    introFrame.setSize(500,500);
		    introFrame.setVisible(true);
		    introFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    introFrame.setVisible(true);	
	    	
			 buttonFindGame = new JButton("FIND GAME");
		     buttonSize9 = new JButton("9");
		     buttonSize13 = new JButton("13");
		     buttonSize19 = new JButton("19");
		     buttonGameMulti = new JButton("Multiplayer");
		     buttonGameSingle = new JButton("Singleplayer");
		     
		     introFrame.setLayout(new GridLayout(2,3));
		     introFrame.add(buttonFindGame);
		     introFrame.add(buttonSize9);
		     introFrame.add(buttonSize13);
		     introFrame.add(buttonSize19);
		     introFrame.add(buttonGameMulti);
		     introFrame.add(buttonGameSingle);
		     introFrame.setVisible(true);
		     
		    // obs³uga zdarzeñ onClick dla buttonów
	        buttonSize9.addActionListener(new ActionListener() {
	             
	             public void actionPerformed(ActionEvent e) {
	            	 
	            	 size=9;
	            	
	            	
	            	 
	             		}
	            });
	         buttonSize13.addActionListener(new ActionListener() {
	             
	             public void actionPerformed(ActionEvent e) {
	            	 
	            	 size =13;
	            	
	             		}
	            });
	         buttonSize19.addActionListener(new ActionListener() {
	             
	             public void actionPerformed(ActionEvent e) {
	            	 size =19;
	            	
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
	         buttonFindGame.addActionListener(new ActionListener() {
	             
	             public void actionPerformed(ActionEvent e) {
	            	   findGame =true;
	            	   buttonGameMulti.setEnabled(false);
	            	   buttonGameSingle.setEnabled(false);
	            	   buttonSize9.setEnabled(false);
	            	   buttonSize13.setEnabled(false);
	            	   buttonSize19.setEnabled(false);
	            	  
	            	   System.out.println(size);
	            	   System.out.println(gameType);
	             		}
	            });
	         
	         
		     
	    	
	    } 
	 //------------------------------------------------------------------------------------------------------
     /**
      * Metoda boolowska, która zwraca true gdy gracz wybierze parametry gry
      * @return
      */
	 public boolean gameChosen() {
			if((size!=0) && findGame==true && ((singlePlayer==true) || (multiPlayer==true))) {return true;}
	    return false;
		}
	
		
	
}
