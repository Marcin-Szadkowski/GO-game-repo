package gogame.server.lobby;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;
import org.mockito.Mockito;
import gogame.server.transProtocol.TCP.TcpInput;
import gogame.server.transProtocol.TCP.TcpOutput;

public class LobbyTest {
	@Test
	public void twoPlayersTest() throws IOException {
		Lobby lobby = Lobby.getInstance();
		TcpInput input = Mockito.mock(TcpInput.class);
		TcpOutput output = Mockito.mock(TcpOutput.class);
		
		PlayerInLobby player1 = new PlayerInLobby(output, input);
		Mockito.when(input.hasNextLine()).thenReturn(true);
		Mockito.when(input.nextLine()).thenReturn("");
		lobby.addPlayer(player1);
		
		PlayerInLobby player2 = new PlayerInLobby(output, input);
		
		lobby.addPlayer(player2);
		
		assertEquals(Integer.valueOf(2), lobby.howManyPlayers());
		lobby.closeLobby();
		
		while(!Lobby.pool.isTerminated()) {
			
		}
		assertTrue(Lobby.pool.isTerminated());
	}

}
