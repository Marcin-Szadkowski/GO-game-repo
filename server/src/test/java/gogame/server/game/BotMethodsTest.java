package gogame.server.game;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class BotMethodsTest {
	@Test
	public void simpleTest() {
		Bot bot = new Bot(Mockito.mock(Game.class), "white");
		Playable currentPlayer;
		currentPlayer = bot;
		assertEquals(currentPlayer, bot);
	}
	/**
	 * Test metody
	 * @see BotMethods#findGroups(Game)
	 */
	@Test
	public void findGroupsTest() {
		Game game = Mockito.mock(Game.class);
		game.size = 9;
		Stone[][] table = new Stone[9][9];
		game.table = table;
		
		table[5][4] = new Stone(5,4,"white");
		table[4][4] = new Stone(4,4,"white");
		
		table[6][3] = new Stone(6,3,"black");
		table[6][4] = new Stone(6,4,"black");
		table[4][3] = new Stone(4,3,"black");

				
		//Teraz odbieramy grupie oddechy i sprawdzamy czy zyje
		table[5][2] = new Stone(5,2, "black");
		table[5][5] = new Stone(5, 5 , "black");
		table[5][4] = new Stone(5, 4 , "black");
		table[4][5] = new Stone(4, 5, "black");
		table[3][4] = new Stone(3, 4, "black");
		
		List<LinkedList<Stone>> groups = BotMethods.findGroups(game, "black");
		assertEquals(4, groups.size());
		
		LinkedList<Stone> weakGroup = BotMethods.findWeakGroup(groups, game);
		assertEquals(1, weakGroup.size());
		System.out.println("Wspolrzedne wierzcholka: " + weakGroup.get(0).x + " "+ weakGroup.get(0).y);
		
		int[] move = BotMethods.choosePlace(weakGroup, game);
		System.out.println("Tutaj chce polozyc kamien: "+ move[0] + " "+ move[1]);
	}
}
