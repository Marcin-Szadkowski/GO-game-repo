package gogame.client.game.performance;

import gogame.client.gui.GameGUI;
import gogame.client.transProtocol.TcpClient;

public class Game {
	
	      public int xMoved;
	      public int yMoved;
	      public boolean gameReady=false;
		  private static Game instance = null;
		  TcpClient client;
		  private  Game(){

		  }
		  public static Game getInstance(){
		    if(instance==null){
		       instance = new Game();
		      }
		      return instance;
		  }
		  
		  public void createBoard() {
			  GameGUI gui = new GameGUI();
		    	 gui.inicialize();
		  }
		  
		/*  
		  public void play() throws Exception{
		    try {
		    	client = new TcpClient();
		    	client.initialize();	
		    	
		    	String response;
		    	
		    	while(client.hasNextLine()){
		    		 response=client.recvMessage();
		    		 
		      if(response.startsWith("GAME_STARTED")) {
		    	   gameReady=true;
		    	///tutaj  metoda ktora buduje plansze
		    	  //motoda ustawia kolor   po wybraniu size/typ
		    	  //przyciski
		      }else if(response.startsWith("YOU_MOVED")) {
		    	  // otrzymuje x,y  na ktorym mam zbufowac pawn
		    	  
		      }else if(response.startsWith("OPONENT_MOVED")) {
		    	  // x i y zeby narysowac plansze przeciwnika
		    	  
		    	  
		      }else if(response.startsWith("YOUR_TURN")) {
		    	  // na labelu wyswietlic
		      }else if(response.startsWith("NOT_YOUR_TURN")) {
		    	  
		    	  // wyspisywanie na labelu
		      }else if(response.startsWith("DELETE")) {
		    	  // pobieranie jednego  stringa z parami wspó³ 
		      }else if (response.startsWith("OTHER_PLAYER_LEFT")) {
		    	  // gracz wyszed³ metoda czy coœ 
		    	  // oddanie siê 
		    	  break;
		      }else if(response.startsWith("VICTORY")) {
		    	  // zapamiêtaj gdzies liczbe prisoners
		    	  // w jednym Stringu 
		    	  // metoda split`-- w tablicy string[]
		    	  //0 - PRISONERS  1 - moje  2- przeciwnik
		    	  break;
		      }else if(response.startsWith("PRISONERS")) {
		    	  
		    	  /// motda update pól z prisonersami i updat egui
		      }else if(response.startsWith("DEFEAT")) {
		    	  // def info
		    	 break;
		      }
		    		
		   }client.sendMessage("QUIT");    		
		 }
		    
	     catch (Exception e){e.printStackTrace();}
		    
		finally {
		    	 client.stop();	
		    		
		    	}	 
		  }*/
		 
		     
		     
		  public static boolean exit() {
			  return true;
		  }
		  
	      
		  public void makeMove(int x, int y) {
				xMoved=x;
				yMoved=y;
			   }
		  public  String getSize(int size) {
			  return String.valueOf(size);
		  }
		  public  String quit() {
			  return "QUIT";
			  
		  }
		  public  String pass() {
			  return "PASS";
		  }
		  public  String single() {
			  return "TYPE_SINGLE";
		  }
		  public  String multi() {
			  return "TYPE_MULTI";
		  }
		  public void sendMove() {
			 
			//return "Move"+" "+String.valueOf(xMoved)+" "+String.valueOf(yMoved);
			  System.out.println("Move"+" "+String.valueOf(xMoved)+" "+String.valueOf(yMoved));
		  }
		  
		  
		  
		 // public String gameStarted() {
			  
		  //}
	} 

   

	



