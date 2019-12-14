package gogame.client.connection;

public interface SendDataParser {
	public void makeMove(int x,int y);
	public void makePass();
	public void makeExit();
	public void sendSize(int size);
	public void sendType(String type);
	public void findGame();

}
