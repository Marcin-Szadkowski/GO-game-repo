package gogame.server.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameMethods {
	private static int[][] vectors = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	
		
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
