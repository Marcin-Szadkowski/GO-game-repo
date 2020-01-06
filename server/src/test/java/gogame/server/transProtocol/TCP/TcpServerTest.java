package gogame.server.transProtocol.TCP;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;

import org.junit.Test;


/**
 * Klasa testujaca zachowanie serwera
 * Uwaga: od poprawnego dzialania testow zalezy nie tylko poprawne dzialanie klasy TcpServer
 * @author marcin
 * @see Lobby
 * @see TcpServer
 * @see PlayerInLobby
 */
public class TcpServerTest {
	
	/**
	 * Metoda sprawdzajaca prawidlowe podlaczenie socketa do serwera
	 * @throws IOException
	 */
	@Test
	public void serverAcceptsConnection() throws IOException {
		
		TcpServer server = (TcpServer) TcpServer.getInstance();	
		new Thread(server).start(); // creates the `serverSocket`


	    try {
	    	Socket ableToConnect = new Socket("localhost", 58901);
	    	assertTrue("Connection accepted", ableToConnect.isConnected());
	      ableToConnect.close();
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	   finally {
		   System.out.println("Zaraz sprobuje zamknac ServerSocket\n");
	    	// close the 'serverSocket'		   
		 //   server.stop();
	    }     
	   
	}	
}
