package gogame.client.transProtocol;

/**
 * Interface implementuj¹cy metody do kontaktu z serwerem
 * @author wojciech
 *
 */
public interface TransferProtocol {
	/*
	 * Initialize connection 
	 */
	public void initialize();
	/*
	 * Sends message to the client
	 */
	public void sendMessage(String message);
	/*
	 * Receives message from client
	 */
	public String recvMessage();
	

	public boolean hasNextLine();
	
	public void stop();
	public boolean isConnected();
}