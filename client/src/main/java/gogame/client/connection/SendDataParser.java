package gogame.client.connection;

/**
 * Interface implementuj¹ce metody obslugujace
 * wysy³anie informacji do serwera
 * @author wojciech
 *
 */

public interface SendDataParser {
	public void makeMove(int x,int y);
	public void makePass();
	public void makeQuit();
	public void sendSize(int size);
	public void sendType(String type);
	public void findGame();

}
