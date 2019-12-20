package gogame.server.game;

/**
 * Klasa reprezentujaca kamien
 * @author marcin
 *
 */
public class Stone {
	int x;
	int y;
	String color;
	
	public Stone(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.color = String.valueOf(color);
	}
	
}
