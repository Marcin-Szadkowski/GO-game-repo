package gogame.server.factories;

import gogame.server.game.Player;
import gogame.server.lobby.PlayerInLobby;

public class PlayerFactory {
	public static Player makePlayer(PlayerInLobby player) {
		Player newPlayer = new Player(player.getOutput(), player.getInput());
		return newPlayer;
	}
}
