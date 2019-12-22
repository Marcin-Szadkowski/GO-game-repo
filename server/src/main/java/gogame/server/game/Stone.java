package gogame.server.game;

/**
 * Klasa reprezentujaca kamien na planszy
 * @author marcin
 *
 */
public class Stone {
	public int x;
	public int y;
	String color;
	
	public Stone(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.color = String.valueOf(color);
	}
	
}
