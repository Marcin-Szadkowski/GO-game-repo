package gogame.server.game;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
/**
 * Testy klasy Bot
 * @see Bot
 * @author marcin
 *
 */
public class BotTest {
	
	/**
	 * Metoda testujaca
	 * @see Bot#getGame()
	 */
	@Test
	public void getGameTest() {
		Game game = new Game(9);
		Bot bot = new Bot(game, "white");
		assertEquals(game, bot.getGame());
	}
	/**
	 * Metoda testujaca
	 * @see Bot#getColor()
	 */
	@Test
	public void getColorTest() {
		Game game = new Game(9);
		Bot bot = new Bot(game, "white");
		assertTrue(bot.getColor().equals("white"));
	}
	/**
	 * Metoda testujaca
	 * @see Bot#opponentMoved(int, int)
	 */
	@Test
	public void opponentMovedTest() {
		Bot bot = Mockito.mock(Bot.class);
		Mockito.doNothing().when(bot).move(Mockito.anyInt(), Mockito.anyInt());
		bot.opponentMoved(4, 4);
		Mockito.verify(bot).opponentMoved(4, 4);
	}
	/**
	 * Test metody @see Bot#move(int, int)
	 * Test testuje wszystkie mozliwe przypadki wywolania metody move
	 */
	@Test
	public void moveTest() {
		Game game =  Mockito.mock(Game.class);
		game.size = 9;
		game.table = new Stone[9][9];
		Bot bot = new Bot(game, "white");
		
		Mockito.doNothing().when(game).move(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Bot.class));
		bot.move(0, 0);
		
		Mockito.verify(game).move(4, 4, bot);
		
		game.table[2][3] = new Stone(2, 3, "white");
		bot.move(0, 0);
		Mockito.verify(game, Mockito.times(2)).move(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Bot.class));
		
		game.table[2][3] = null;
		
		game.table[1][0] = new Stone(1,0, "white");
		game.table[0][1] = new Stone(0,1, "white");
		game.table[1][1] = new Stone(1,1, "black");
		game.table[1][2] = new Stone(1,2, "white");
		game.table[2][1] = new Stone(2,1, "white");
		game.table[7][1] = new Stone(7,1, "black");
		
		bot.move(0, 0);
		Mockito.verify(game, Mockito.times(3)).move(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Bot.class));
		//Teraz bot spasuje
		game.table[7][1] = null;
		bot.move(0, 0);
		Mockito.verify(game, Mockito.times(3)).move(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Bot.class));
		
		
	}
	@Test
	public void simpleTests() {
		Bot bot = Mockito.mock(Bot.class);
		bot.opponentMoved(0, 0);
		Mockito.verify(bot).opponentMoved(Mockito.anyInt(), Mockito.anyInt());
		bot.opponentPassed();
		Mockito.verify(bot).opponentPassed();
	}


}
