package gogame.client.tcp;

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
	
	public static TransferProtocol getInstance() {
		return null;
	}
	public boolean hasNextLine();
	
	public void stop();
}
