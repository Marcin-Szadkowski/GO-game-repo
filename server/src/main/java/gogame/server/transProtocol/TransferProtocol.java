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
	/**
	 * Zatrzymaj polaczenie
	 */
	public  void stop();
	
	public static TransferProtocol getInstance() {
		return null;
	}
	
}
