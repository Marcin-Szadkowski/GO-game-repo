package gogame.server.transProtocol;

/**
 * Interface odbioru danych
 * @author marcin
 *
 */
public interface Input {
	//Pobierz nastepna linie danych
	public String nextLine();
	//Sprawdz czy jest kolejna linia danych
	public boolean hasNextLine();
	//Zamknij polczenie
	public void closeSocket();
}
