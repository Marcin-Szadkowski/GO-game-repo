package gogame.server.game;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;


import org.junit.Test;
import org.mockito.Mockito;

/**
 * Klasa testujaca GameMethods
 * @see GameMethods
 * @author marcin
 *
 */
public class GameMethodsTest {
		
	@Test
	public void beatStones() {
		Game game = Mockito.mock(Game.class);
		Stone stone1= new Stone(1, 0, "white");
		Stone[][] table = new Stone[9][9];
		table[1][0] = stone1;
		table[0][0] = new Stone(0,0, "black");
		table[0][1] = new Stone(0,1, "white");
		game.table = table;
		game.size = 9;
		game.whitePrisoners =0;
		game.blackPrisoners = 0;
		
		
		List<LinkedList<Stone>> groups = new LinkedList<LinkedList<Stone>>();
		groups = GameMethods.findGroups(stone1, 9, table);
		
		assertEquals(1, groups.size());
		
		LinkedList<Stone> stonesToBeat = new LinkedList<Stone>();
		stonesToBeat = GameMethods.beatStones(groups, game);
		
		assertEquals(1, stonesToBeat.size());
		System.out.println("Zbity kamien: "+ stonesToBeat.get(0).x+ " "+ stonesToBeat.get(0).y);
		assertNull(table[0][0]);
		assertEquals(1, game.blackPrisoners);
		assertEquals(0, game.whitePrisoners);
		
	}
	/**
	 * Test metody areDead
	 * @see GameMethods#areDead(List, int, Stone[][])
	 */
	@Test
	public void areDeadTest() {
		Stone stone1 = new Stone(5, 3, "black");
		Stone[][] table = new Stone[9][9];
		table[5][3] = stone1;
		table[5][4] = new Stone(5,4,"black");
		table[4][4] = new Stone(4,4,"black");
		
		table[6][3] = new Stone(6,3,"white");
		table[6][4] = new Stone(6,4,"white");
		table[4][3] = new Stone(4,3,"white");
		table[3][2] = new Stone(3,2,"white");
		table[3][3] = new Stone(3,3,"white");
		
		List<LinkedList<Stone>> groups = new LinkedList<LinkedList<Stone>>();
		
		groups = GameMethods.findGroups(stone1, 9, table);
		//Dla tego ulozenia mamy dwie grupy ktore sa zywe
		assertFalse(GameMethods.areDead(groups, 9, table));
	}
	/**
	 * Metoda testujaca isAlive
	 * @see GameMethods#isAlive(List, int, Stone[][])
	 */
	@Test
	public void isAliveTest() {
		Stone stone1 = new Stone(5, 3, "black");

		Stone[][] table = new Stone[9][9];
		table[5][3] = stone1;
		table[5][4] = new Stone(5,4,"black");
		table[4][4] = new Stone(4,4,"black");
		
		table[6][3] = new Stone(6,3,"white");
		table[6][4] = new Stone(6,4,"white");
		table[4][3] = new Stone(4,3,"white");
		List<Stone> group = new LinkedList<Stone>();
		group = GameMethods.findGroup(stone1, 9, table);
		
		assertTrue(GameMethods.isAlive(group, 9, table));
		
		//Teraz odbieramy grupie oddechy i sprawdzamy czy zyje
		table[5][2] = new Stone(5,2, "white");
		table[5][5] = new Stone(5, 5 , "white");
		table[4][5] = new Stone(4, 5, "white");
		table[3][4] = new Stone(3, 4, "white");
		
		assertFalse(GameMethods.isAlive(group, 9, table));
		
		Stone stone2 = new Stone(0, 0, "black");
		Stone[][] table2 = new Stone[9][9];
		table2[0][0] = stone2;
		table2[0][1] = new Stone(0, 1, "white");
		table2[1][0] = new Stone(1, 0, "white");
		assertNotNull(table2[1][0]);
		assertNull(table2[1][1]);

		group = GameMethods.findGroup(stone2, 9, table2);
		assertEquals(1, group.size());
		
		assertFalse(GameMethods.isAlive(group, 9, table2));
	}
	@Test
	public void test() {
		Stone stone1 = new Stone(5, 3, "black");

		Stone[][] table = new Stone[9][9];
		table[5][3] = stone1;
		table[5][4] = new Stone(5,4,"black");
		table[4][4] = new Stone(4,4,"black");
		
		table[6][3] = new Stone(6,3,"white");
		table[6][4] = new Stone(6,4,"white");
		table[4][3] = new Stone(4,3,"white");
		List<Stone> group = new LinkedList<Stone>();
		
		group = GameMethods.findGroup(stone1, 9, table);
		assertEquals(3, group.size());
		
	//	assertTrue(GameMethods.isAlive(group, 9, table));
		
		System.out.println(group.get(0).x + " "+ group.get(0).y + " " + group.get(0).color);
		System.out.println(group.get(1).x + " "+ group.get(1).y + " " + group.get(1).color);
		System.out.println(group.get(2).x + " "+ group.get(2).y + " " + group.get(2).color);
				
	}
	@Test
	public void simpleTest() {
		Stone stone1 = new Stone(5, 3, "black");
		Stone[][] table = new Stone[9][9];
		table[5][3] = stone1;
		table[5][4] = new Stone(5,4,"black");
		table[4][4] = new Stone(4,4,"black");
		
		table[6][3] = new Stone(6,3,"white");
		table[6][4] = new Stone(6,4,"white");
		table[4][3] = new Stone(4,3,"white");
		table[3][2] = new Stone(3,2,"white");
		table[3][3] = new Stone(3,3,"white");
		
		List<LinkedList<Stone>> groups = new LinkedList<LinkedList<Stone>>();
		
		groups = GameMethods.findGroups(stone1, 9, table);
		
		assertEquals(2, groups.size());
		List<Stone> group1 = groups.get(0);
		
		System.out.println(group1.get(0).x + " "+ group1.get(0).y + " " + group1.get(0).color);
		System.out.println(group1.get(1).x + " "+ group1.get(1).y + " " + group1.get(1).color);
		
		assertEquals(2, group1.size());
		
		List<Stone> group2 = groups.get(1);
		System.out.println(" ");
		
		assertEquals(3, group2.size());
		System.out.println(group2.get(0).x + " "+ group2.get(0).y + " " + group2.get(0).color);
		System.out.println(group2.get(1).x + " "+ group2.get(1).y + " " + group2.get(1).color);
		System.out.println(group2.get(2).x + " "+ group2.get(2).y + " " + group2.get(2).color);
		
	}

}
