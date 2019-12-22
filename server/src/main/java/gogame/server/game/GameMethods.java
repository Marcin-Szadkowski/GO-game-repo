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
	public static LinkedList<Stone> beatStones(List<LinkedList<Stone>> groups, Game game) {
		LinkedList<Stone> stonesToBeat = new LinkedList<Stone>();
		
		for(LinkedList<Stone> group: groups) {
			if(GameMethods.isAlive(group, game.size, game.table) == false) {
				for(Stone stone: group) {
					stonesToBeat.add(stone);
					game.table[stone.x][stone.y] = null;
					if(stone.color.equals("white"))
						game.whitePrisoners++;
					else
						game.blackPrisoners++;
				}
			}				
		}
		return stonesToBeat;
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
	/**
	 * Metoda obliczajaca terytorium dla graczy na danej planszy
	 * @param game
	 */
	public static void countTerritory(Game game) {
		int blackTerritory = 0;
		int whiteTerritory = 0;
		Stone[][] table = game.table;
		boolean[][] visited = new boolean[game.size][game.size];
		Queue<int[]> stos = new LinkedList<int[]>();
		String color= "";
		int change=0;
		int count = 0;
		
		//Zaczynam liczenie od punktu w lewym gornym rogu
		for(int i=0; i< game.size; i++) {
			for(int j=0; j< game.size; j++) {
				if(table[i][j] != null || visited[i][j] == true)
					continue;
				change = 0;
				count=0;
				color = "";
				stos.add(new int[] {i, j});//Umieszczam na stosie wierzcholek startowy	
				count++;
				visited[i][j] = true;
				while(!stos.isEmpty()) {
					int[] v = stos.poll();//Pobieram wierzcholek ze stosu
				
					for(int[] vector: vectors) {
						int X = v[0] + vector[0];
						int Y = v[1] + vector[1];
						
						if(X < 0 || X >= game.size || Y < 0 || Y>= game.size)
							continue;
						if(visited[X][Y] == true)
							continue;
						if(table[X][Y] == null) {
							stos.add(new int[] {X, Y});
							visited[X][Y] = true; //jako visited oznaczam tylko wolne oddechy
							count++;
						}else {
							if(change == 0) {
								color = table[X][Y].color;
								change++;								
							}else {
								if(!table[X][Y].color.equals(color))
									change++;
							}
						}						
					}
				}//Jezeli change jest rowna 1 to znaczy, ze na brzegach jest tylko jeden kolor
				if(change == 1) {
					System.out.println("Dla wierzcholka: "+ i + " "+ j+" dodaje: "+ count);
					//To znaczy ze, ze mamy poprawnie otoczony teren
					if(color.equals("white"))
						whiteTerritory += count;
					else
						blackTerritory += count;
				}
			}
		}
		//Dodajemy terytorium bialego do zbitych czarnych, bo to suma jego punktow i vice versa
		game.blackPrisoners += whiteTerritory;
		game.whitePrisoners += blackTerritory;
	}
}
