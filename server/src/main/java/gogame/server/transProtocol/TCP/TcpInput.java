package gogame.server.transProtocol.TCP;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import gogame.server.transProtocol.Input;
/**
 * Klasa implementujaca interface Input
 * @author marcin
 * @see Input
 */
public class TcpInput implements Input {
	Socket socket;
	Scanner input;
	
	/**
	 * Metoda zwracajaca wiadomsoc przesylana przez socket
	 * @return String
	 */
	public String in() {
		return input.nextLine();		
	}
	public TcpInput(Socket socket) {
		this.socket = socket;
		try {
			set();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metoda ustawiajaca wejscie stumienia danych 
	 * @throws IOException
	 */
	private void set() throws IOException {
		input = new Scanner(socket.getInputStream());
	}	
}
