package gogame.server.lobby;

import gogame.server.lobby.member.Data;

public interface GamesHandler{
	public void findGame(Data data);
	public void addPlayer(Data data);
	public void deletePlayer(Data data);
	public Integer howManyPlayers();
	void matchPlayers(Data data1, Data data2);
	
}
