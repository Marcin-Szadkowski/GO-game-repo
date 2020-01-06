package gogame.server.game;

/**
 * Klasa reprezentujaca kamien na planszy
 * @author marcin
 *
 */
public class Stone {
	public int x; //Wspolrzedna x na planszy
	public int y; //Wspolrzedna y na planszy
	String color; //kolor kamienia
	
	public Stone(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.color = String.valueOf(color);
	}
	
}
