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
	public String nextLine() {
		return input.nextLine();		
	}
	public boolean hasNextLine() {
		if(input.hasNextLine())
			return true;
		return false;
	}
	public TcpInput(Socket socket) {
		this.socket = socket;
		try {
			set();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void closeSocket() {
		try {
			this.socket.close();
		}catch(IOException e) {}
	}
	/**
	 * Metoda ustawiajaca wejscie strumienia danych 
	 * @throws IOException
	 */
	private void set() throws IOException {
		input = new Scanner(socket.getInputStream());
	}	
}
