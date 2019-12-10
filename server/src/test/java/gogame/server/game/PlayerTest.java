package gogame.server.game;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import gogame.server.lobby.Lobby;
import gogame.server.transProtocol.TCP.TcpInput;
import gogame.server.transProtocol.TCP.TcpOutput;

/**
 * Klasa testujaca poprawnosc metod z klasy Player
 * @author marcin
 * @see Player
 */

public class PlayerTest {
	
	
	/**
	 * Test metody setSize() z klasy @see Player
	 * @see Player#setSize()
	 */
	/*
	public void setSizeTest() {
		TcpInput input = Mockito.mock(TcpInput.class);
		TcpOutput output = Mockito.mock(TcpOutput.class);
		Lobby lobby = Mockito.mock(Lobby.class);
			
		Player player = new Player(output, input);
		Mockito.when(input.hasNextLine()).thenReturn(true);
		Mockito.when(input.nextLine()).thenReturn("SIZE 19");
		
		new Thread(player).start();
		
		Mockito.doAnswer((Answer) invocation ->{
			player.set(new Game(19), new Player(output, input), "Black");
			System.out.println("zostaje wywolana ta metoda");
			return null;
		}).when(lobby).findGame();
						
		assertEquals(Integer.valueOf(19), player.getGameSize());		
	}*/
	
	@Test
	/**
	 * Test metody set() z klasy @see Player
	 * @see Player#set()
	 */
	public void setTest() {
		TcpInput input = Mockito.mock(TcpInput.class);
		TcpOutput output = Mockito.mock(TcpOutput.class);
		
		Player player = new Player(output, input);
		
		player.set(new Game(19), Mockito.mock(Player.class), "Black");
		
		assertNotNull("Gracz nie ma gry", player.getGame());
		assertEquals(player.color, "Black");
	}
	@Test
	public void runTest() {
		TcpInput input = Mockito.mock(TcpInput.class);
		TcpOutput output = Mockito.mock(TcpOutput.class);
		Player player = new Player(output, input);
		player.set(new Game(19), Mockito.mock(Player.class), "Black");
		Mockito.when(input.hasNextLine()).thenReturn(false);
		Thread temp = new Thread(player);
		temp.start();
		
		while(temp.isAlive()) {
			
		}
		assertFalse("Watek dziala", temp.isAlive());
		
	}

}
