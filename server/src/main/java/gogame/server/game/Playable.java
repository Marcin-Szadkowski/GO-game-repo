package gogame.server.game;

import java.util.LinkedList;

/**
 * Interface gracza, ktorym moze byc client lub bot
 * @author marcin
 *
 */
public interface Playable {
		//Zwroc instancje rozgrywanej gry
		public Game getGame();
		//Zwroc color gracza
		public String getColor();
		//Wysli informacje o rozpoczatej grze
		public void gameStarted();
		//Wyslij informacje o ruchu przeciwnika
		public void opponentMoved(int x, int y);
		//Wyslij informacje o wiezniach
		public void prisoners(int x, int y);
		//Wyslij informacje o niemoznosci wykonania ruchu
		public void notYourTurn();
		//Sprobuj wykonac ruch
		public void move(int x, int y);
		//Wyslij informacje o wykonaniu ruchu
		public void youMoved(int x, int y);
		//Wyslij liste zbitych kamieni
		public void delete(LinkedList<Stone> stones);
		//Wykonaj pass
		public void pass();
		//Wyslij informacje o zwyciestwie
		public void victory(int x, int y);
		//Wyslij informacje o porazce
		public void defeat(int x, int y);
		//Wyslij ifnormacje o remisie
		public void tie(int x);
		//Wyslij quit do gry
		public void quit();
		//Wyslij do clienta info o opusczeniu gry przez przeciwnika
		public void otherPlayerLeft();
		//Wyslij informacje o kolejce gracza
		public void opponentPassed();
}
