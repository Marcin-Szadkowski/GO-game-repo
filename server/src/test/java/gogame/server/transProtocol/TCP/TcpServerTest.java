package gogame.server.transProtocol.TCP;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;

import org.junit.Test;

import gogame.client.tcp.TcpClient;

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
	/*public void serverAcceptsConnection() throws IOException {
		
		TcpServer server = (TcpServer) TcpServer.getInstance();	
		new Thread(server).start(); // creates the `serverSocket`

		// create a `clientSocket` that will try to connect to a serverSocket
	    // that has the hostname 'localhost'
	    // and listens at port number 58901
	    try {
	    	Socket ableToConnect = new Socket("localhost", 58901);
	    	assertTrue("Connection accepted", ableToConnect.isConnected());
	      // close the `clientSocket`
	      ableToConnect.close();
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	   finally {
		   System.out.println("Zaraz sprobuje zamknac ServerSocket\n");
	    	// close the 'serverSocket'
		   
		    server.stop();
	    }     
	   
	}*/
	@Test
	/**
	 * Metoda sprawdzajaca podlaczenie dwoch Clientow do serwera
	 * oraz poprawne zakonczenie dzialania serwera
	 */
	public void twoClientsSimpleTest() {
		TcpServer server = (TcpServer) TcpServer.getInstance();
		Thread serverThread = new Thread(server);
		//Uruchamiam watek server
		serverThread.start();
		
		TcpClient client1 = new TcpClient();
		TcpClient client2 = new TcpClient();
		//Podlaczam clientow
		client1.initialize();
		client2.initialize();
		//Poczekaj az sie podlaczy
		while(!client1.isConnected()) {
			
		}
		assertTrue(client1.isConnected());
		assertTrue(client1.isConnected());
		
		client1.sendMessage("QUIT");
		client2.sendMessage("QUIT");
		
		server.stop();
		
		while(serverThread.isAlive()) {
			
		}
		//assertFalse(client1.isConnected());
		//assertFalse(client1.isConnected());
		assertFalse(serverThread.isAlive());
	}
	
	/**
	 * Test imitujacac krotkie dzialanie dwoch clientow
	 * Ma na celu doprowadzic do momentu, w ktorym gracze zostaja przydzieleni do tej samej gry
	 */
	/*public void twoClientsTest() {
		//Pobieramy instancje TcpServer
		TcpServer server = (TcpServer) TcpServer.getInstance();
		//Tworzy zmienna klasy watek potrzebna do wykonania metody run() z klasy TcpServer
		Thread serverThread = new Thread(server);
		//Uruchamiam watek server
		serverThread.start();
		
		//Tworzymy instancje klasy TcpClient
		TcpClient client1 = new TcpClient();
		TcpClient client2 = new TcpClient();
		
		//Czekam az serwer sie wlaczy
		while(!server.isRunning()) {
			
		}
		//Podlaczam clientow
		client1.initialize();
		client2.initialize();
		//Poczekaj az sie podlaczy
		while(!client1.isConnected()) {
			
		}
		//Teraz przez metody z TcpClient mozemy wysylac informacje do serwera
		assertTrue(client1.isConnected());
		assertTrue(client1.isConnected());
		
		//Wysylam do serwera komunikaty SIZE
		client1.sendMessage("SIZE 13");
		client2.sendMessage("SIZE 13");
		//Wysylam do serwera komunikaty TYPE
		client1.sendMessage("TYPE MULTI");
		client2.sendMessage("TYPE MULTI");
		
		System.out.println("Po wyslaniu danych do serwera");
		String message;
		//Teraz czekam az serwer zwroci clientowi GAME_STARTED
		while(!client1.isClosed()) {
			message = client1.recvMessage();
			if(message.startsWith("GAME_STARTED")) {
				assertTrue(message.startsWith("GAME_STARTED"));
				break;
			}
		}
		assertTrue(client1.hasNextLine());
		
		
		while(!client2.isClosed()) {
			message = client1.recvMessage();
			if(message.startsWith("GAME_STARTED")) {
				assertTrue(message.startsWith("GAME_STARTED"));
				break;
			}
		}
		assertTrue(client2.hasNextLine());
		//Teraz poprawnie koncze dzialanie gracza czyli wysylam na serwer QUIT
		client1.sendMessage("QUIT");
		client2.sendMessage("QUIT");
		
		server.stop();
		
		while(serverThread.isAlive()) {
			
		}
		assertFalse(serverThread.isAlive());
	}*/

	
}
