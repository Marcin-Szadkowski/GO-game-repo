package gogame.server.game;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

/**
 * Klasa testujaca GameMethods
 * @see GameMethods
 * @author marcin
 *
 */
public class GameMethodsTest {

	/*
	public void test() {
		Stone stone1 = new Stone(5, 3, "black");
		Stone[][] table = new Stone[9][9];
		table[5][4] = new Stone(5,4,"black");
		table[4][4] = new Stone(4,4,"black");
		
		table[6][3] = new Stone(6,3,"white");
		table[6][4] = new Stone(6,4,"white");
		table[4][3] = new Stone(4,3,"white");
		List<Stone> group = new LinkedList<Stone>();
		
		group = GameMethods.findGroup(stone1, 9, table);
		assertEquals(3, group.size());
		
		System.out.println(group.get(0).x + " "+ group.get(0).y + " " + group.get(0).color);
		System.out.println(group.get(1).x + " "+ group.get(1).y + " " + group.get(1).color);
		System.out.println(group.get(2).x + " "+ group.get(2).y + " " + group.get(2).color);
				
	}*/
	@Test
	public void simpleTest() {
		Stone stone1 = new Stone(5, 3, "black");
		Stone[][] table = new Stone[9][9];
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
