package gogame.server.lobby.member;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import gogame.server.game.Game;
import gogame.server.game.Player;

public class DataTest {

	@Test
	public void test() {
		Connector connector = Mockito.mock(Connector.class);
		Parser parser = new Parser(connector);
		Data data = new Data(parser);
		
		assertEquals(parser, data.getParser());
		data.setGameSize(19);
		assertEquals(19, data.getGameSize().intValue());
		
		data.setGameType("MULTI");
		assertEquals("MULTI", data.getGameType());
		assertTrue(data.isReady());
		
		Game game = new Game(19);
		data.addGame(game);
		Player player =  new Player(Mockito.mock(Game.class), Mockito.mock(Data.class), "black");
		
		data.addPlayer(player);
		
		assertEquals(player, data.getPlayer());	
		assertEquals(game, data.getGame());
	
		assertNotNull(data.getGame());
		assertNotNull(data.getPlayer());
		assertNotNull(data.getPlayer().getColor());
		assertEquals("black", data.getPlayer().getColor().toString());
	}

}
