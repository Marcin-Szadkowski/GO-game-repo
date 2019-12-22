package gogame.server.transProtocol.TCP;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import gogame.server.transProtocol.Output;
/**
 * Klasa rozszerzajaca interface Output
 * @author marcin
 * @see Output
 */
public class TcpOutput implements Output {
	
	private Socket socket;
	private PrintWriter output;
	
	/**
	 * Metoda wypisujaca wiadomosc przez socket
	 * @param message
	 */
	public void out(String message) {
		output.println(message);
		
	}
	/**
	 * Konstruktor klasy TcpOutput
	 * @param socket
	 */
	public TcpOutput(Socket socket) {
		this.socket = socket;
		try {
			set();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metoda ustawiajaca wyjscie do wypisywania 
	 * wiadomosci
	 * @throws IOException
	 */
	private void set() throws IOException {
		output = new PrintWriter(socket.getOutputStream(), true);
	}
	
}
