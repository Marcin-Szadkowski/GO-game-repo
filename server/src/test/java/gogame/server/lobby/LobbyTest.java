package gogame.server.lobby;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import gogame.server.game.Player;
import gogame.server.transProtocol.TCP.TcpInput;
import gogame.server.transProtocol.TCP.TcpOutput;

public class LobbyTest {
	@Mock
	TcpOutput output;
	TcpInput input;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Test
	public void twoPlayersTest() throws IOException {
		Lobby lobby = Lobby.getInstance();
		
		Player player1 = new Player(output, input);
		//new Thread(lobby).start();
		lobby.addPlayer(player1);
		
		Player player2 = new Player(output, input);
		
		lobby.addPlayer(player2);
		player1.gameSize = 19;
		player2.gameSize = 19;
		//lobby.matchPlayers(player1, player2);
		//lobby.stop();
		assertEquals(lobby.howManyPlayers(), Integer.valueOf(2));
		Lobby.getInstance().findGame();
		assertEquals(lobby.howManyPlayers(), Integer.valueOf(0));
		assertEquals(player1.getGameSize(), Integer.valueOf(19));
		assertNotNull("Game is null", player2.getGame());
		assertEquals(player1.getGame(), player2.getGame());
		
		
	}

}
