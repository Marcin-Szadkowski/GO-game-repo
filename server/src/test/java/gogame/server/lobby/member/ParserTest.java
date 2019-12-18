package gogame.server.lobby.member;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import gogame.server.game.Game;
import gogame.server.game.Player;

/**
 * Klasa odpowiadajaca za test klasy Parser
 * @see Parser
 * @author marcin
 *
 */
public class ParserTest {

	/**
	 * Test prostych wywolan z klasy Data
	 */
	@Test
	public void simpleTest() {
		Connector connector = Mockito.mock(Connector.class);
		Parser parser = new Parser(connector);
		
		parser.interpret("SIZE 19");
		parser.interpret("TYPE MULTI");
		parser.interpret("FIND_GAME");
		
	}
	@Test
	public void testWithPlayer() {
		Connector connector = Mockito.mock(Connector.class);
		Parser parser = new Parser(connector);
		
		Player player = Mockito.mock(Player.class);
		Game game = Mockito.mock(Game.class);
		parser.getData().addPlayer(player);
		parser.getData().addGame(game);
		
		Answer<Void> answer = new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable{
				return null;
			}
		};
		
		Mockito.doAnswer(answer).when(player).quit();
		Mockito.doAnswer(answer).when(player).pass();
		Mockito.doAnswer(answer).when(player).move(6, 12);
		//Mockito.doNothing().when(player).move(6, 12);
		
		parser.interpret("QUIT");
		parser.interpret("PASS");
		parser.interpret("MOVE 5 12");
		
		Mockito.verify(player).quit();
		Mockito.verify(player).pass();
		Mockito.verify(player).move(5, 12);
		
	}

}
