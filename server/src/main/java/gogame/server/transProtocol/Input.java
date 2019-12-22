package gogame.server.transProtocol;

/**
 * Interface odbioru danych
 * @author marcin
 *
 */
public interface Input {
	public String nextLine();
	public boolean hasNextLine();
	public void closeSocket();
}
