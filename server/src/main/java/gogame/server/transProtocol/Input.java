package gogame.server.transProtocol;

public interface Input {
	public String nextLine();
	public boolean hasNextLine();
	public void closeSocket();
}
