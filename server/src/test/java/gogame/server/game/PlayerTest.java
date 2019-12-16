package gogame.server.game;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import gogame.server.lobby.Lobby;
import gogame.server.lobby.member.Data;
import gogame.server.transProtocol.TCP.TcpInput;
import gogame.server.transProtocol.TCP.TcpOutput;

/**
 * Klasa testujaca poprawnosc metod z klasy Player
 * @author marcin
 * @see Player
 */

public class PlayerTest {

	@Test
	/**
	 * Test sprawdzajacy poprawnosc metod z klasy Player
	 * @see Player#getGame()
	 * @see Player#getColor()
	 */
	public void setTest() {
		Game game = Mockito.mock(Game.class);
		Data data = Mockito.mock(Data.class);
		Player player = new Player(game, data, "black");
		
		assertEquals(game, player.getGame());
		assertEquals(data, player.data);
		assertEquals("black", player.getColor());
		
	}
}
