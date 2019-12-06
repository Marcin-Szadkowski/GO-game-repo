package gogame.server.transProtocol.TCP;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import junit.framework.TestCase;

public class TcpOutputTest extends TestCase {
	byte[] emptyPayload = new byte[1001];
	
	

	public void testSimpleOutput() {
		final Socket socket = Mockito.mock(Socket.class);
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		TcpOutput output = new TcpOutput(socket);
		
		//when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
	}



}
	
