package gogame.server.game;

import java.util.LinkedList;

public interface Playable {
		
		public Game getGame();
		public String getColor();
		public void gameStarted();
		public void opponentMoved(int x, int y);
		public void prisoners(int x, int y);
		public void notYourTurn();
		public void move(int x, int y);
		public void youMoved(int x, int y);
		public void delete(LinkedList<Stone> stones);
		public void pass();
		public void victory(int x, int y);
		public void defeat(int x, int y);
		public void tie(int x);
		public void quit();
}
