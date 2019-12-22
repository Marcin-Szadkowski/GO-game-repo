package gogame.server.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Klasa odpowiadajaca za ruchy bota
 * @author marcin
 *
 */
public class BotMethods {
	private static int[][] vectors = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	
	/**
	 * Metoda zwracajaca grupe kamieni o najmniejszej liczbie oddechow
	 * @param groups
	 * @param game
	 * @return
	 */
	public static LinkedList<Stone> findWeakGroup(List<LinkedList<Stone>> groups, Game game){
		LinkedList<Stone> weakGroup = new LinkedList<Stone>();
		int min = countBreaths(groups.get(0), game);
		int breaths;
		weakGroup = groups.get(0);
		
		for(LinkedList<Stone> group: groups) {
			breaths = countBreaths(group, game);
			if(breaths < min) {
				min = breaths;
				weakGroup = group;
			}
		}
		return weakGroup;
	}
	/**
	 * Metoda wybierajaca miejsce 
	 * @param enemyGroup
	 * @param game
	 * @return
	 */
	public static int[] choosePlace(LinkedList<Stone> enemyGroup, Game game) {
		int size = game.size;
		Stone[][] table = game.table;
		boolean[][] considered = new boolean[size][size];
		int[] place = null;
		
		for(Stone stone: enemyGroup) {
			considered[stone.x][stone.y] = true;
			for(int[] vector: vectors) {
				int X = stone.x + vector[0];
				int Y = stone.y + vector[1];
				
				if(X < 0 || X >= size || Y < 0 || Y>= size)
					continue;
				if(considered[X][Y])
					continue;
				if(table[X][Y] == null) {
					//Trzeba by sprawdzic czy to nie ruch samobojczy oraz czy to nie KO
					considered[X][Y] = true;
					Stone stone2 = new Stone(X, Y, "white");
					//Mam grupe, ktora powstanie po postawieniu tam kamienia przez bota
					if(game.lastBeat != null)
						if(game.lastBeat.x == X && game.lastBeat.y == Y)
							continue;
					if(isCorrect(stone2, size, table))
						place = new int[] {X, Y};									
				}				
			}
		}
		return place;
	}
	/**
	 * Metoda sprawdzajaca czy stawiajac kamien nie popelniamy ruchu samobojczego
	 * @param stone
	 * @param size
	 * @param table
	 * @return
	 */
	public static boolean isCorrect(Stone stone, int size, Stone[][] table) {
		//Sprawdzam czy grupa jest zywa
		//Dodaje tymczasowo kamien na plansze
		table[stone.x][stone.y] = stone;
		List<Stone> group = GameMethods.findGroup(stone, size, table);
		
		if(GameMethods.isAlive(group, size, table)) {
			table[stone.x][stone.y] = null;
			return true;
		}else {
			//To sprawdzam czy ten ruch daje bicie
			if(GameMethods.areDead(GameMethods.findGroups(stone, size, table), size, table)) {
				//To znaczy ze ten ruch daje jakies bicie, wiec mozna go wykonac
				table[stone.x][stone.y] = null;
				return true;
			}
		}
		return false;
	}
	/**
	 * Metoda liczaca oddechy podanej grupy
	 * @param group
	 * @param game
	 * @return liczba oddechow grupy
	 */
	public static int countBreaths(LinkedList<Stone> group, Game game) {
		int size = game.size;
		Stone[][] table = game.table;
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
		return breaths;
	}
	/**
	 * Metoda znajdujaca wszystkie grupy o podanym kolorze
	 * @param game
	 * @param color kolor szukanej grupy
	 * @return grupy o podanym kolorze
	 */
	public static List<LinkedList<Stone>> findGroups(Game game, String color){
		int size = game.size;
		Stone[][] table = game.table;
		
		List<LinkedList<Stone>> groups = new LinkedList<LinkedList<Stone>>(); //Grupy wynikowe
		boolean[][] visited = new boolean[size][size];
		Queue<Stone> stos = new LinkedList<Stone>();
		
		for(int i=0; i< size; i++) {
			for(int j=0; j <size; j++) {
				for(int[] vect: vectors) {
					LinkedList<Stone> group = new LinkedList<Stone>();
					visited[i][j] = true;
					int X1 = i + vect[0];
					int Y1 = j + vect[1];
					
					if(X1 < 0 || X1 >= size || Y1 < 0 || Y1>= size)			
						continue;
					if(visited[X1][Y1])
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
			}
		}
		return groups;
	}
	/**
	 * Metoda sprawdzajaca czy tablica jest pusta
	 * @param table
	 * @param size
	 * @return
	 */
	public static boolean isEmpty(Stone[][] table, int size) {
		for(int i=0; i<size; i++) {
			for(int j =0; j<size; j++) {
				if(table[i][j] != null)
					return false;
			}
		}
		return true;
	}
}
