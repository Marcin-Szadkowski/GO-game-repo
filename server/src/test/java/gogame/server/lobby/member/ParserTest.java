package gogame.server.lobby.member;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import gogame.server.game.Game;
import gogame.server.game.Player;
import gogame.server.game.Stone;

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
	@Test
	public void simpleMethodsTest() {
		Connector connector = Mockito.mock(Connector.class);
		Parser parser = new Parser(connector);
		
		Mockito.doNothing().when(connector).sendMsg(Mockito.anyString());
		//Testuje metode prisoners()
		parser.prisoners(2, 1);
		Mockito.verify(connector).sendMsg("PRISONERS 2 1");
		//Testuje metode opponentMoved()
		parser.opponentMoved(5, 5);
		Mockito.verify(connector).sendMsg("OPPONENT_MOVED 5 5");
		//Testuje metode notYourTurn()
		parser.notYourTurn();
		Mockito.verify(connector).sendMsg("NOT_YOUR_TURN");
		//Testuje metode youMoved()
		parser.youMoved(2, 3);
		Mockito.verify(connector).sendMsg("YOU_MOVED 2 3");
		//Testuje metode delete
		LinkedList<Stone> stones = new LinkedList<Stone>();
		stones.add(new Stone(3,4, "white"));
		stones.add(new Stone(5, 6, "black"));
		
		parser.delete(stones);
		Mockito.verify(connector).sendMsg("DELETE 3 4 5 6");
		
		//Testuje metode victory()
		parser.victory(10, 7);
		Mockito.verify(connector).sendMsg("VICTORY 10 7");
		//Testuje metode defeat()
		parser.defeat(4 ,5);
		Mockito.verify(connector).sendMsg("DEFEAT 4 5");
		//Testuje metode tie()
		parser.tie(2);
		Mockito.verify(connector).sendMsg("TIE 2");
		//Testuje metode othePlayerLeft()
		parser.otherPlayerLeft();
		Mockito.verify(connector).sendMsg("OTHER_PLAYER_LEFT");
		//Testuje metode opponentPasse()
		parser.opponentPassed();
		Mockito.verify(connector).sendMsg("OPPONENT_PASSED");
	
	}
	

}
