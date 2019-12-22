package gogame.server.lobby.member;

import java.io.IOException;
import java.net.Socket;

import gogame.server.transProtocol.TCP.TcpInput;
import gogame.server.transProtocol.TCP.TcpOutput;
/**
 * Klasa odpowiedzialna za polaczenie Clienta z gra
 * @author marcin
 *	Nasluchuje wiadomosci od Clienta i przekazuje je do interpretera polecen
 */
public class Connector implements Runnable {
	private Socket socket;
	private TcpOutput output;
	private TcpInput input;
	private Parser parser;
	
	//Konstruktor przyjmujacy obiekt klasy Socket
	public Connector(Socket socket) {
		this.socket = socket;
	}
	public Connector() {};
	
	public void run() {
		//Ustaw potrzebne zmienne
		setup();

			String command;
			//Nasluchuj przychodzacych wiadomosci
			//Dodac ewentualnie warunek sprawdzajacy czy watkowi nie probuje przeszkodzic server, czyli probuje go wylaczyc
			while(input.hasNextLine() && !Thread.currentThread().isInterrupted()) {

					command = input.nextLine();
					if(command.startsWith("QUIT")) {
						//Sproboj wyslac wiadomosc do przeciwnika o opuszczeniu gry przez tego gracza
						parser.interpret(command);
						break;
					}				
					else {
						parser.interpret(command);
					}
				
				
					
			}
			System.out.println("Watek przerwany\n");
		
		//Jezeli opuscilismy petle to znaczy ze ExecutorService probuje zamknac dzialajace watki
		//Wysylamy wiec do Clienta QUIT
		output.out("QUIT");
							
		try {
			//Zamknij Socket
			socket.close();
		}catch(IOException e) {};
			
	}
	/**
	 * Metoda odpowiadajaca za ustanowienie strumieni wejsciowych
	 * Oraz stowrzenie obiektu klasy Parser
	 */
	private void setup() {
		output = new TcpOutput(socket);
		input = new TcpInput(socket);
		parser = new Parser(this);	

		
	}
	/**
	 * Metoda przesylajaca wiadomosc przez socket
	 * @param msg
	 */
	public void sendMsg(String msg) {
		output.out(msg);
	}
	
}
