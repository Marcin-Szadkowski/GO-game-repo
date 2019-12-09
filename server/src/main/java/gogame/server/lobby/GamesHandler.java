package gogame.server.lobby;

import gogame.server.game.Player;

public interface GamesHandler{

	public abstract void run();
	public abstract void stop();
	public abstract void addPlayer(Player player);
	public abstract void deletePlayer(Player player);
	
}
