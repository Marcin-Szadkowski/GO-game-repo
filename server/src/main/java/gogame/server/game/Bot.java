package gogame.server.game;

import java.util.LinkedList;
import java.util.List;

/**
 * Klasa reprezentujaca bota
 * Bot przede wszystkim wykonuje ruchy
 * @author marcin
 *
 */
public class Bot implements Playable {
	String color;
	Game game;
	
	public Bot(Game game, String color) {
		this.game = game;
		this.color = color;
	}
	public Game getGame() {
		return game;
	}

	public String getColor() {
		return color;
	}

	public void opponentMoved(int x, int y) {
		//Jak przeciwnik wykonal ruch to teraz twoja kolej
		//wykonaj ruch
		this.move(x, y);
		
	}

	public void prisoners(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void notYourTurn() {
	
	}
	
	/**
	 * Metoda wykonujaca ruch
	 * Znajdz wszystkie grupy przeciwnika. Nastepnie wybierz te ktora ma najmniej oddechow.
	 * Sprobuj przy niej postawic kamien. Jesli sie nie udalo to wybierz jakas inna grupe
	 */
	public void move(int x, int y) {
		int[] move;
		//Jezeli zaczynasz i plansza jest pusta to postaw kamien na 4 4
		if(BotMethods.isEmpty(game.table, game.size)) {
			game.move(4, 4, this);
			return;
		}			
		List<LinkedList<Stone>> groups = BotMethods.findGroups(game, "black");
		//Jesli nie ma wgl grup przeciwnika to znajdz swoja grupe i tam ustaw
		if(groups.isEmpty()) {
			groups = BotMethods.findGroups(game, "white");
		}
		LinkedList<Stone> weakGroup = BotMethods.findWeakGroup(groups, game);
		move = BotMethods.choosePlace(weakGroup, game);
		if(move != null) {
			game.move(move[0], move[1], this);
		}else {
			for(LinkedList<Stone> group: groups) {
				move = BotMethods.choosePlace(group, game);
				if(move == null)
					continue;
				game.move(move[0], move[1], this);				
				return;
			}
		}
		if(move == null)
			this.pass();
		
	}
	/**
	 * Przeciwnik spasowal wiec wykonaj ruch
	 */
	public void opponentPassed() {
		move(0,0);
	}
	/**
	 * Wykonaj pass
	 */
	public void pass() {
		game.pass(this);
		
	}
	public void youMoved(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	public void delete(LinkedList<Stone> stones) {
		// TODO Auto-generated method stub
		
	}	
	public void victory(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void defeat(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	public void tie(int x) {
		// TODO Auto-generated method stub
		
	}
	public void quit() {
		// TODO Auto-generated method stub
		
	}
	public void otherPlayerLeft() {
		// TODO Auto-generated method stub
		
	}
	public void gameStarted() {
		// TODO Auto-generated method stub
	}
}
