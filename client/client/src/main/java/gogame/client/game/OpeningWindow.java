package gogame.client.game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OpeningWindow extends JFrame {
	//type    multi -2    single -1
	private static int type=0;
	private static int size= 0;

	public OpeningWindow() {
		setSize(730,780);
		//setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLayout(new GridLayout());
		
	    JPanel gameType = new JPanel();
	   
	   // gameType.setBackground(Color.WHITE);
	    gameType.setVisible(true);
	    gameType.setSize(800,200);
	    gameType.setLayout(null);
	    add(gameType);
	   
	    JButton buttonSingleplayer = new JButton("Singleplayer");
	    JButton buttonMultiplayer = new JButton("Multiplayer");
	    JButton buttonSize9 = new JButton("Size9");
	    JButton buttonSize13 = new JButton("Size13");
	    JButton buttonSize19 = new JButton("Size19");
	    JLabel image = new JLabel("Image");
	    image.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\goImage.png"));
	    image.setBounds(50,75,600,600);
	    
	    buttonSize9.setBounds(50,650,200,50);
	    buttonSize13.setBounds(250,650,200,50);
	    buttonSize19.setBounds(450,650,200,50);
	    buttonSingleplayer.setBounds(50,50,300,50);
        buttonMultiplayer.setBounds(350,50,300,50);
        
        
	    gameType.add(buttonMultiplayer);
	    gameType.add(buttonSingleplayer);
	    gameType.add(buttonSize9);
	    gameType.add(buttonSize13);
	    gameType.add(buttonSize19);
	    gameType.add(image);
         repaint();
         
       buttonSize9.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   
    		   size =9;if(readyToSend()) {System.out.println("readytosand");}
    	   }
       });
       buttonSize13.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   
    		   size =13;
    		   if(readyToSend()) {System.out.println("readytosand");}
    	   }
       });
       buttonSize19.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   
    		   size =19;
    		   if(readyToSend()) {System.out.println("readytosand");}
    	   }
       });
       buttonSingleplayer.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   
    		   type=1;
    		   if(readyToSend()) {System.out.println("readytosand");}
    	   }
       });
       buttonMultiplayer.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   
    		   type=2;
    		   if(readyToSend()) {System.out.println("readytosand");}
    	   }
       });
	}
	private  boolean readyToSend() {
		if((type!=0)  &&( size != 0))return true;
		else return false;
	}
	public int[] sendChosenSettings() {
		int[] chosen = new int[2];
		chosen[0]=size; chosen[1]=type;
		return chosen;
		
	}
	
	public static void main(String args[]) {
		OpeningWindow window = new OpeningWindow();
		if(window.readyToSend()) {System.out.println("readytosand");}
		
	}
}
