package gogame.server.game;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import gogame.server.lobby.member.*;

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
	/**
	 * Test metody
	 * @see Player#pass()
	 */
	@Test
	public void passTest() {
		Game game = Mockito.mock(Game.class);
		Data data = Mockito.mock(Data.class);
		Player player = new Player(game, data, "black");
		
		Answer<Void> answer = new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable{
				return null;
			}
		};
		//Test pass()
		Mockito.doAnswer(answer).when(game).pass(player);
		player.pass();
		Mockito.verify(game).pass(player);
	}
	@Test
	public void quitTest() {
		Game game = Mockito.mock(Game.class);
		Data data = Mockito.mock(Data.class);
		Player player = new Player(game, data, "black");
		
		Answer<Void> answer = new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable{
				return null;
			}
		};
		//Test quit()
		Mockito.doAnswer(answer).when(game).quit(player);
		player.quit();
		Mockito.verify(game).quit(player);
	}
	/**
	 * Test metody
	 * @see Player#move(int, int)
	 */
	@Test
	public void moveTest() {
		Game game = Mockito.mock(Game.class);
		Data data = Mockito.mock(Data.class);
		Player player = new Player(game, data, "black");
		
		Answer<Void> answer = new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable{
				return null;
			}
		};
		//Test move()
		Mockito.doAnswer(answer).when(game).move(1, 1, player);
		player.move(1, 1);
		Mockito.verify(game).move(1, 1, player);
	}
	@Test
	/**
	 * Test sprawdzajacy poprawnosc metody gameStarted()
	 * @see Player#gameStarted()
	 */
	public void  gameStartedTest() {
		Parser parser = Mockito.mock(Parser.class);
		Data data = new Data(parser);
		Player player = new Player(Mockito.mock(Game.class), data, "black");
		
		Answer<Void> answer = new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable{
				return null;
			}
		};
		//Test gameStarted()
		Mockito.doAnswer(answer).when(parser).gameStarted(Mockito.any(Player.class));
		player.gameStarted();
		Mockito.verify(parser).gameStarted(player);
		
	}
	/**
	 * Test metody
	 * @see Player#youMoved(int, int)
	 */
	@Test
	public void youMovedTest() {
		Parser parser = Mockito.mock(Parser.class);
		Data data = new Data(parser);
		Player player = new Player(Mockito.mock(Game.class), data, "black");
		
		Answer<Void> answer = new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable{
				return null;
			}
		};
		//Test youMoved
		Mockito.doAnswer(answer).when(parser).youMoved(3, 2);
		player.youMoved(3, 2);
		Mockito.verify(parser).youMoved(3, 2);
	}
	
	@Test
	/**
	 * Test metody 
	 * @see Player#notYourTurn()
	 */
	public void notYourTurnTest() {
		Parser parser = Mockito.mock(Parser.class);
		Data data = new Data(parser);
		Player player = new Player(Mockito.mock(Game.class), data, "black");
		
		Answer<Void> answer = new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable{
				return null;
			}
		};
		//Test notYourTurn
		Mockito.doAnswer(answer).when(parser).notYourTurn();
		player.notYourTurn();
		Mockito.verify(parser).notYourTurn();
	}
	@Test 
	/**
	 * Test metody opponentMoved z Player
	 * @see Player#opponentMoved(int x, int y)
	 */
	public void opponentMovedTest() {
		Parser parser = Mockito.mock(Parser.class);
		Data data = new Data(parser);
		Player player = new Player(Mockito.mock(Game.class), data, "black");
		
		Answer<Void> answer = new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable{
				return null;
			}
		};
		//Test opponentMoved(int x, int y)
		Mockito.doAnswer(answer).when(parser).opponentMoved(Mockito.anyInt(), Mockito.anyInt());
		player.opponentMoved(9, 10);
		Mockito.verify(parser).opponentMoved(9, 10);		
	}

}
