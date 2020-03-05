package gogame.server.lobby.member;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Klasa zawierajaca testy klasy Connector
 * @author marcin
 *
 */
public class ConnectorTest {

	@Test
	public void setupTest() {
		Socket socket = Mockito.mock(Socket.class);
		Connector connector = new Connector(socket);		
		InputStream input = Mockito.mock(InputStream.class);
		OutputStream output = Mockito.mock(OutputStream.class);
		
		try {
			Mockito.when(socket.getOutputStream()).thenReturn(output);
			Mockito.when(socket.getInputStream()).thenReturn(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		connector.setup();		
	}
	/**
	 * Test metody
	 * @see Connector#sendMsg(String)
	 */
	@Test
	public void sendMsgTest() {
		Socket socket = Mockito.mock(Socket.class);
		Connector connector = new Connector(socket);		
		InputStream input = Mockito.mock(InputStream.class);
		OutputStream output = Mockito.mock(OutputStream.class);
		
		try {
			Mockito.when(socket.getOutputStream()).thenReturn(output);
			Mockito.when(socket.getInputStream()).thenReturn(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		connector.setup();	
		try {
			Mockito.doNothing().when(output).write(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connector.sendMsg("HELLO");
		try {
			Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
