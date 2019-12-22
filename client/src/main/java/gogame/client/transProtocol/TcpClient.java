package gogame.client.transProtocol;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import gogame.client.connection.DataParser;
import gogame.client.gui.GameGui;


/**
 * Klasa implementujaca wzorzec singleton
 * Za pomocą instancji klasy przekazywane sa informacje pomiędzy klientem a serwerem
 * Polaczenie z serwerem jest inicjalizowane dopiero przez wywolanie metody initialize()
 * @author wojciech
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
	public static TransferProtocol getInstance() {
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
	 * Metoda przez którą wysyłamy informacje do serwera
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
	 * 
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
	/**
	 * Metoda odpowiedzialna za nasłuchiwanie informacji od serwera.
	 * W momencie rozpoczęcia gry generuje planszę .
	 * 
	 * @throws Exception
	 */
	public void listen() throws Exception{
	    try {
	    	System.out.println("listen");
	    	initialize();		
	    	String response;
	    	DataParser parser = new DataParser();
	  
	    	
	    if(socket.isConnected()) {
	    	// parser.receiveConnected();
		    	
	    	  System.out.println("connected");
	    	  GameGui game = new GameGui();
	    	  game.inicialize();
		    	  
		      }
		
	    	while(hasNextLine()){
	    		 response=recvMessage();
	    		 System.out.println("przesyłam do command interpeter" + response);
	    		 parser.commandInterpreter(response);
	     
	   }stop();   		
	 }
	
	 catch (Exception e){e.printStackTrace();}
	    
     finally {
	stop();	
		    		
		   }	 
   }
	
	
}