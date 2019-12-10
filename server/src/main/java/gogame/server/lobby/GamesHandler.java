package gogame.server.lobby;

import gogame.server.game.Player;

public interface GamesHandler{
	public void findGame();
	public void addPlayer(PlayerInLobby player);
	public void deletePlayer(PlayerInLobby player);
	public Integer howManyPlayers();
	void matchPlayers(PlayerInLobby player1, PlayerInLobby player2);
	
}
