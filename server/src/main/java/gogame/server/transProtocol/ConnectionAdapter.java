package gogame.server.transProtocol;

/**
 * Interface sluzacy do polaczenia przesylu danych
 * w zaleznosci od typu serwera
 * @author marcin
 *
 */
public interface ConnectionAdapter {
	public void setup();
	public void sendMsg(String msg);
}
