package gogame.server.transProtocol.TCP;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;

import org.junit.Test;

import gogame.client.tcp.TcpClient;


public class TcpServerTest {
	@Test
	public void twoClientsTest() {
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
	/*@SuppressWarnings("resource")
	@Test
	public void serverAcceptsConnection() throws IOException {
		
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
	     
	    try {
	      // now that `serverSocket` is closed
	      // try to connect another `clientSocket` to the same `serverSocket`
	      new Socket("localhost", 58901);
	      fail("Cannot connect if server socket is not listening");
	    } catch (Exception e) {
	      // assert that the exception is thrown and is the right exception
	      assertEquals("Connection refused", e.getMessage().trim());
	    }
	  }*/
}
