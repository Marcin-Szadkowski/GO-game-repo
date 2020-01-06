package gogame.server.lobby;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import gogame.server.game.Player;
import gogame.server.lobby.member.Connector;
import gogame.server.lobby.member.Data;
import gogame.server.lobby.member.Parser;

public class LobbyTest extends Lobby{
	public String status;
	@Test
	/**
	 * Metoda testujaca dzialanie addPlayer()
	 * @see Lobby#addPlayer()
	 */
	public void addPlayerTest() {
		Data data1 = Mockito.mock(Data.class);
		Data data2 = Mockito.mock(Data.class);
		
		Lobby.getInstance().addPlayer(data1);
		Lobby.getInstance().addPlayer(data2);
		//Sprawdzam czy rzeczywiscie jest teraz dwoch graczy
		assertEquals(Integer.valueOf(2), Lobby.getInstance().howManyPlayers());
		Lobby.getInstance().deletePlayer(data1);
		Lobby.getInstance().deletePlayer(data2);
		//Sprawdzam czy teraz nie ma graczy
		assertEquals(Integer.valueOf(0), Lobby.getInstance().howManyPlayers());
		
		assertNotNull(data1);
		
	}
	@Test
	public void findGameTest() {
		Data data1 = Mockito.mock(Data.class);
		Data data2 = Mockito.mock(Data.class);
		
		Lobby.getInstance().addPlayer(data1);
		Lobby.getInstance().addPlayer(data2);
		
		Mockito.when(data1.isReady()).thenReturn(true);
		Mockito.when(data2.isReady()).thenReturn(false);
		
		Lobby.getInstance().findGame(data1);
		Lobby.getInstance().findGame(data2);
		
		Lobby.getInstance().deletePlayer(data1);
		Lobby.getInstance().deletePlayer(data2);		
	}
	@Test
	/**
	 * Metoda testujaca dzialanie matchPlayers()
	 * @see Lobby#matchPlayers()
	 */
	public void matchPlayersTest() {
		Connector connector1 = Mockito.mock(Connector.class);
		Connector connector2 = Mockito.mock(Connector.class);
		
		Parser parser1 = new Parser(connector1);
		Parser parser2 = new Parser(connector2);
	
		parser1.interpret("SIZE 19");
		parser2.interpret("SIZE 19");
		
		parser1.interpret("TYPE MULTI");
		parser2.interpret("TYPE MULTI");
		
		Answer<Void> answer = new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable{
				System.out.println("Wywolanie sendMsg() z Connector");
				return null;
			}
		};
		Mockito.doAnswer(answer).when(connector1).sendMsg(Mockito.anyString());
		Mockito.doAnswer(answer).when(connector2).sendMsg(Mockito.anyString());
		
		parser1.interpret("FIND_GAME");
				
		assertEquals(Integer.valueOf(0), Lobby.getInstance().howManyPlayers());
		assertNotNull(parser1.getData().getPlayer());
		assertNotNull(parser2.getData().getPlayer());
		
	}
	@Test
	/**
	 * Metoda testujaca dzialanie comparePlayers()
	 * @see Lobby#matchPlayers()
	 */
	public void comparePlayersTest() {
		Data data1 = Mockito.mock(Data.class);
		Data data2 = Mockito.mock(Data.class);
		
		Lobby.getInstance().addPlayer(data1);
		Lobby.getInstance().addPlayer(data2);
		
		Mockito.when(data1.isReady()).thenReturn(true);
		Mockito.when(data2.isReady()).thenReturn(true);
		
		Mockito.when(data1.getGameSize()).thenReturn(19);
		Mockito.when(data2.getGameSize()).thenReturn(19);
		
		Mockito.when(data1.getGameType()).thenReturn("MULTI");
		Mockito.when(data2.getGameType()).thenReturn("MULTI");
		
		
		assertTrue(Lobby.getInstance().comparePlayers(data1, data2));
		
		Lobby.getInstance().deletePlayer(data1);
		Lobby.getInstance().deletePlayer(data2);
	}
	@Test
	public void prepareSingleTest() {
		Data data = Mockito.mock(Data.class);
		Lobby.getInstance().addPlayer(data);
		Parser parser = Mockito.mock(Parser.class);
		Mockito.doNothing().when(parser).gameStarted(Mockito.any(Player.class));
		
		Mockito.when(data.getGameSize()).thenReturn(9);
		Mockito.when(data.getGameType()).thenReturn("SINGLE");
		Mockito.when(data.isReady()).thenReturn(true);
		Mockito.when(data.getParser()).thenReturn(parser);
		
		Lobby.getInstance().prepareSingle(data);
		
		assertEquals(Integer.valueOf(0), Lobby.getInstance().howManyPlayers());
	}
	
	
}
