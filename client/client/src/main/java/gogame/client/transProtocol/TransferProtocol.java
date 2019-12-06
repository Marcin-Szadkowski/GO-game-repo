package gogame.client.transProtocol;

public interface TransferProtocol {

	/*
	 * Initialize connection 
	 */
	public void initialize() throws Exception;
	/*
	 * Sends message to the client
	 */
	public void sendMessage();
	/*
	 * Receives message from client
	 */
	public void recvMessage();	

}
