package gogame.client.transProtocol;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import gogame.client.connection.DataParser;
import gogame.client.game.Game;
import gogame.client.gui.GameGui;


/**
 * Klasa implementujaca wzorzec singleton
 * W bloku dzialania klienta pobrac instancje klasy
 * Polaczenie z serwerem jest inicjalizowane dopiero przez wywolanie metody initialize()
 * @author marcin
 *
 */
public class TcpClient implements TransferProtocol {
	private Socket socket;
	private Scanner input;
	private PrintWriter output;
	private volatile static TcpClient instance;
	
	/**
	 * Metoda zwracajaca instancje klasy TcpServer
	 * lub tworzaca jej instancje jesli jeszcze nie istnieje
	 */
	public TransferProtocol getInstance() {
		if(instance == null) {
			synchronized (TcpClient.class) {
				if(instance == null) {
					instance = new TcpClient();
				}
			}
		}
		return instance;
	}
	/**
	 * Tutaj laczymy sie z serwer i ustawiamy strumienie wejscia i wyjscia
	 */
	public  void initialize(){
		try {
			//To troche srednie, mozna by do initialize przekazywac hosta i port zeby bylo bardziej abstrakcyjnie
			socket = new Socket("localhost", 58901);
			input = new Scanner(socket.getInputStream());
			output = new PrintWriter(socket.getOutputStream(), true);
		} catch(IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * Kiedy chcesz wyslac wiadomosc na serwer uzyj tej metody mordeczko
	 */
	public void sendMessage(String message) {
		output.println(message);	
	}
	
	/**
	 * Kiedy chcesz pobrac wiadomosc z serwera to uzyj tej metody
	 * Jesli nie da sie pobrac wiadomosci bo np socket zostal jakos zamkniety przez blad 
	 * to zwroci null
	 */
	public String recvMessage() {
		if(input.hasNextLine()) {
			return input.nextLine();
		}
		return null;		
	}
	/**
	 * Ta metoda jest dodana po to zeby ulatwic dzialanie Clienta
	 * Dzieki niej mozesz dac warunek zeby nasluchiwac danych z serwera dopoki to prawda
	 * Obaczaj TicTacToeClient metoda play()
	 */
	public boolean hasNextLine() {
		if(input == null)
			return false;
		if(input.hasNextLine())
			return true;
		return false;
	}
	/**
	 * To jest dodane zeby zamknac socket na koniec dzialania klienta
	 * Co prawda gdzies tam pisza ze java moze robic to sama bla bla ale dobrze to wywolac
	 * Wywolac kiedy np. Client dostal info od serwera ze gra skonczona, no to wtedy elo zamykamy wszystko
	 */
	public void stop() {
		sendMessage("QUIT");
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isConnected() {
		if(socket.isConnected())return true;
		else return false;
		
	}
	public void listen() throws Exception{
	    try {
	    	System.out.println("listen");
	    	//client = new TcpClient();
	    	initialize();		
	    	String response;
	    	DataParser parser = new DataParser();
	  
	    	
	    if(socket.isConnected()) {
		    	  parser.receiveConnected();
		    	//  connected=true;  
		    	  //GameGui game = new GameGui();
		    	 // game.inicialize();
		    	 // game.createBoard();
		    	  System.out.println("connected");
		    	  GameGui game = new GameGui();
		    	  game.inicialize();
		    	  
		      }
		     
	    	
	    	
	    	while(hasNextLine()){
	    		 response=recvMessage();
	    		 
	      if(response.startsWith("GAME_STARTED")) {
	    	   parser.receiveGameStarted();
	    	///tutaj  metoda ktora buduje plansze
	    	  //motoda ustawia kolor   po wybraniu size/typ
	    	  //przyciski
	      }
	      else if(response.startsWith("YOU_MOVED")) {
	    	  parser.receiveYouMoved(response);
	    	  
	      }
	      else if(response.startsWith("OPONENT_MOVED")) {
	    	  parser.receiveOponentMoved(response);
	    	  
	    	  
	      }
	      else if(response.startsWith("YOUR_TURN")) {
	    	  parser.receiveYourTurn();
	    	 
	      }
	      else if(response.startsWith("NOT_YOUR_TURN")) {
	    	  
	    	  parser.receiveNotYourTurn();
	      }
	      else if(response.startsWith("DELETE")) {
	    	  parser.receiveDelete(response);
	      }
	      else if (response.startsWith("OTHER_PLAYER_LEFT")) {
	    	  parser.receiveOtherPlayerLeft();
	    	  break;
	      }
	      else if(response.startsWith("VICTORY")) {
	    	  parser.receiveVictory();
	    	  // zapamiêtaj gdzies liczbe prisoners
	    	  // w jednym Stringu 
	    	  // metoda split`-- w tablicy string[]
	    	  //0 - PRISONERS  1 - moje  2- przeciwnik
	    	  break;
	      }
	      else if(response.startsWith("PRISONERS")) {
	    	  parser.receivePrisoners(response);
	    	  /// motda update pól z prisonersami i updat egui
	      }
	      else if(response.startsWith("DEFEAT")) {
	    	  parser.receiveDefeat();
	    	 break;
	      }
	    		
	   }stop();   		
	 }
	
	    catch (Exception e){e.printStackTrace();}
	    
		finally {
			stop();	
		    		
		    	}	 
		  }
	
	
}