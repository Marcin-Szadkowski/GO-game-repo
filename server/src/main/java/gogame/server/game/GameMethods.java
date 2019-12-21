package gogame.server.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Klasa zawierajaca metody implementujaca algorytm DFS
 * @author marcin
 *
 */
public class GameMethods {
	private static int[][] vectors = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	
	/**
	 * Metoda zwraca grupe stawianego kamienia	
	 * @param stone stawiany kamien
	 * @param size rozmiar gry	
	 * @param table graf
	 * @return grupa koloru stawianego kamienia
	 */
	public static List<Stone> findGroup(Stone stone, int size, Stone[][] table){
		boolean[][] visited = new boolean[size][size];
		Queue<Stone> stos = new LinkedList<Stone>();
		List<Stone> group = new LinkedList<Stone>();
		
		stos.add(stone);
		visited[stone.x][stone.y] = true;
		while(!stos.isEmpty()) {
			Stone v = stos.poll();
			group.add(v);
			for(int[] vector: vectors) {
				//assert stos.peek() != null;
				int X = v.x + vector[0];
				int Y = v.y + vector[1];
				
				if(X < 0 || X >= size || Y < 0 || Y>= size)
					continue;
				if(visited[X][Y])
					continue;
				if(table[X][Y] != null) {
					if(table[X][Y].color.equals(stone.color))
						stos.add(table[X][Y]);						
				}
				visited[X][Y] = true;
			}			
		}
		return group;
	}
	/**
	 * Metoda sprawdzajaca czy jakas grupa jest martwa
	 * @param groups
	 * @param size
	 * @param table
	 * @return true jesli jest chociaz 1 martwa grupa
	 */
	public static boolean areDead(List<LinkedList<Stone>> groups, int size, Stone[][] table) {
		int dead =0;
		for(LinkedList<Stone> group: groups) {
			if(GameMethods.isAlive(group, size, table) == false)
				dead++;
		}
		if(dead == 0)
			return false;
		return true;
	}
	/**
	 * Metoda obslugujaca bicie grup kamieni
	 * @param groups
	 * @param game
	 * @return listy grup do zbicia
	 */
	public static List<LinkedList<Stone>> beatStones(List<LinkedList<Stone>> groups, Game game) {
		List<LinkedList<Stone>> groupsToBeat = new LinkedList<LinkedList<Stone>>();
		
		for(LinkedList<Stone> group: groups) {
			if(GameMethods.isAlive(group, game.size, game.table) == false) {
				groupsToBeat.add(group);
				for(Stone stone: group) {
					game.table[stone.x][stone.y] = null;
					if(stone.color.equals("white"))
						game.whitePrisoners++;
					else
						game.blackPrisoners++;
				}
			}				
		}
		return groupsToBeat;
	}
	/**
	 * Funkcja sprawdzajaca czy podana grupa ma jakies oddechy
	 * @param group
	 * @return true jesli grupa ma przynajmniej 1 oddech
	 */
	public static boolean isAlive(List<Stone> group, int size, Stone[][] table) {
		boolean[][] counted = new boolean[size][size];
		int breaths = 0;
		
		for(Stone stone: group) {
			counted[stone.x][stone.y] = true;
			for(int[] vector: vectors) {
				int X = stone.x + vector[0];
				int Y = stone.y + vector[1];
				
				if(X < 0 || X >= size || Y < 0 || Y>= size)
					continue;
				if(counted[X][Y])
					continue;
				if(table[X][Y] == null) {
					breaths++;
					counted[X][Y] = true;					
				}				
			}
		}
		if(breaths == 0)
			return false;
		else return true;
	}
	/**
	 * 
	 * @param stone stawiany kamien
	 * @param size
	 * @param table
	 * @return zwraca sasiednie grupy przeciwnika
	 */
	public static List<LinkedList<Stone>> findGroups(Stone stone, int size, Stone[][] table){
		boolean[][] visited = new boolean[size][size];
		Queue<Stone> stos = new LinkedList<Stone>();
		List<LinkedList<Stone>> groups = new LinkedList<LinkedList<Stone>>();
		String color;
		//Teraz szukamy grup przeciwnika
		if(stone.color.equals("black"))
			color = "white";
		else
			color = "black";
		
		for(int[] vect: vectors) {
			LinkedList<Stone> group = new LinkedList<Stone>();
			visited[stone.x][stone.y] = true;
			int X1 = stone.x + vect[0];
			int Y1 = stone.y + vect[1];
			
			if(X1 < 0 || X1 >= size || Y1 < 0 || Y1>= size)			
				continue;
			
			if(table[X1][Y1] != null && table[X1][Y1].color.equals(color)) {
				Stone u = table[X1][Y1];
				visited[X1][Y1] = true;
				stos.add(u);
			}
			while(!stos.isEmpty()) {				
				Stone v = stos.poll();
				group.add(v);
				for(int[] vector: vectors) {
					//assert stos.peek() != null;
					int X = v.x + vector[0];
					int Y = v.y + vector[1];
					
					if(X < 0 || X >= size || Y < 0 || Y>= size)
						continue;
					if(visited[X][Y])
						continue;
					if(table[X][Y] != null) {
						if(table[X][Y].color.equals(color))
							stos.add(table[X][Y]);						
					}
					visited[X][Y] = true;
				}			
			}
			if(!group.isEmpty())
				groups.add(group);
			
		}
		
		return groups;
	}
}
