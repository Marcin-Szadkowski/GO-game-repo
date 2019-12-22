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
	
	public static int[] makeMove(Game game) {
		return null;
	}
	public LinkedList<Stone> findWeakGroup( List<LinkedList<Stone>> groups){
		LinkedList<Stone> group = new LinkedList<Stone>();
		
		
		return null;
	}
	/**
	 * Metoda znajdujaca slaba grupe przeciwnika
	 * @param game
	 * @return slaba grupa przeciwnika
	 */
	public List<LinkedList<Stone>> findGroups(Game game){
		int size = game.size;
		String color = "black";
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
}
