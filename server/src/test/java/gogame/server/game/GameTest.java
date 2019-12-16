package gogame.server.game;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class GameTest {

	@Test
	/**
	 * Test sprawdzajacy czy po wywolaniu gameStarted w klasie Game sa wywolywane odpowiednie 
	 * metody w klasie Player
	 * @see Game#setup()
	 * @see Game#gameStarted()
	 * @see Player#gameStarted()
	 */
	public void gameStartedTest() {
		Game game = new Game(new Integer(19));
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		
		Mockito.doNothing().when(player1).gameStarted();
		Mockito.doNothing().when(player2).gameStarted();
		
		game.setup(player1, player2);
		
		Mockito.verify(player1).gameStarted();
		Mockito.verify(player2).gameStarted();
	}

}
