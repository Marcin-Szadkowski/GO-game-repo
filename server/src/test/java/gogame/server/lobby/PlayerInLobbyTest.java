package gogame.server.lobby;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import gogame.server.transProtocol.TCP.TcpInput;
import gogame.server.transProtocol.TCP.TcpOutput;

public class PlayerInLobbyTest {

	@Test
	/**
	 * Metoda testujaca czy watek zostal zakonczony wtedy kiedy 
	 * ma skonczyc swoje dzialanie
	 */
	public void runAndCloseTest() {
		TcpInput input = Mockito.mock(TcpInput.class);
		TcpOutput output = Mockito.mock(TcpOutput.class);
		PlayerInLobby player = new PlayerInLobby(output, input);
		
		Mockito.when(input.hasNextLine()).thenReturn(false);
		
		Thread playerThread = new Thread(player);
		playerThread.start();
		player.state="STARTED";
		while(playerThread.isAlive()) {
			
		}
		assertFalse(playerThread.isAlive());
		
	}
	@Test
	/**
	 * Test polegajaca na sprawdzeniu czy watek PlayerInLobby
	 * zostanie zakonczony kiedy Client przesle sygnal QUIT
	 */
	public void quitSignalTest() {
		TcpInput input = Mockito.mock(TcpInput.class);
		TcpOutput output = Mockito.mock(TcpOutput.class);
		PlayerInLobby player = new PlayerInLobby(output, input);
		
		Thread playerThread = new Thread(player);
		
		Mockito.when(input.hasNextLine()).thenReturn(true);
		Mockito.when(input.nextLine()).thenReturn("QUIT");
		
		playerThread.start();
		
		while(playerThread.isAlive()) {
			
		}
		assertFalse(playerThread.isAlive());
	}
	@Test
	/**
	 * Test sprawdzajacy poprawnosc dzialania metody isReady()
	 */
	public void isReadyTest() {
		TcpInput input = Mockito.mock(TcpInput.class);
		TcpOutput output = Mockito.mock(TcpOutput.class);
		PlayerInLobby player = new PlayerInLobby(output, input);
		
		player.gameType="SINGLE";
		player.gameSize = 19;
		
		assertTrue(player.isReady());
	}
	@Test
	/**
	 * Test sprawdzajacy poprawnosc dzialania metody getGameSize()
	 */
	public void getGameSizeTest() {
		TcpInput input = Mockito.mock(TcpInput.class);
		TcpOutput output = Mockito.mock(TcpOutput.class);
		PlayerInLobby player = new PlayerInLobby(output, input);
		
		player.gameSize =13;
		assertEquals(Integer.valueOf(13), player.getGameSize());
	}
	

}
