package gogame.server.dataBase;

import static org.junit.Assert.*;

import org.junit.Test;

import gogame.server.game.Game;

public class ManageGameTest {

	@Test
	public void test() {
		Game game = new Game(9);
		ManageGame.addGame(game);
	}

}
