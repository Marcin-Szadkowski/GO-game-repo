package gogame.server.lobby;

import gogame.server.lobby.member.Data;
/**
 * Interface zawierajacy metody przydatne do implementacji roznego rodzaju lobby
 * @author marcin
 *
 */
public interface GamesHandler{
	public void findGame(Data data);
	public void addPlayer(Data data);
	public void deletePlayer(Data data);
	public Integer howManyPlayers();
	void matchPlayers(Data data1, Data data2);
	void prepareSingle(Data data);
	
}
