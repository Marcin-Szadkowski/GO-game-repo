package gogame.client.game.performance;

public class GameMain {
  public static void main(String[] args) throws Exception {
	  Game game= Game.getInstance();
	 // game.play();
	  game.createBoard();
	  game.play();
  }
 
}
