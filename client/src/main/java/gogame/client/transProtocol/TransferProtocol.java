package gogame.client.transProtocol;

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
	
	public TransferProtocol getInstance();
	public boolean hasNextLine();
	
	public void stop();
	public boolean isConnected();
}