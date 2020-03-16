package gogame.server.game;

/**
 * Klasa reprezentujaca kamien na planszy
 * @author marcin
 *
 */
public class Stone {
	private int id; 
	public int x; //Wspolrzedna x na planszy
	public int y; //Wspolrzedna y na planszy
	String color; //kolor kamienia
	private int gameId; //Id gry
	
	public Stone(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.color = String.valueOf(color);
	}
	
	public Stone() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	};
		
}
