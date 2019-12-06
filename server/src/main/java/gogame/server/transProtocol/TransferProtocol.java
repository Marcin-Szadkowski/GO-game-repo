package gogame.server.transProtocol;
/**
 * Public interface for different transmission protocols
 * @author marcin
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
	public void sendMessage();
	/*
	 * Receives message from client
	 */
	public void recvMessage();
	
	public  void stop();
	
	public static TransferProtocol getInstance() {
		return null;
	}
	
}
