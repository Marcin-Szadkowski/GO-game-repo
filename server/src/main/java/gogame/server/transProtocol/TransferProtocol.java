package gogame.server.transProtocol;
/**
 * Public interface for different transmission protocols
 * @author marcin
 *
 */
public interface TransferProtocol extends Runnable {
	
	/*
	 * Initialize connection 
	 */
	public void initialize();

	public  void stop();
	
	public static TransferProtocol getInstance() {
		return null;
	}
	
}
