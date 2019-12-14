package gogame.server.transProtocol.TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import gogame.server.transProtocol.TransferProtocol;
import gogame.server.lobby.member.Connector;
/**
 * Klasa rozszerzajaca interface TransferProtocol
 * W oparciu o TCP
 * @author marcin
 * @see TransferProtocol
 */
public class TcpServer implements TransferProtocol {
	private ServerSocket listener;
	private volatile static TcpServer instance;
	private volatile boolean execute = true;
	static ExecutorService pool;
	
	public void initialize() {
				
	}
	/**
	 * Metoda zwracajaca instancje klasy TcpServer
	 * lub tworzaca jej instancje jesli jeszcze nie istnieje
	 */
	public static TransferProtocol getInstance() {
		if(instance == null) {
			synchronized (TcpServer.class) {
				if(instance == null) {
					instance = new TcpServer();
				}
			}
		}
		return instance;
	}
	public void stop(){
		
		this.execute = false;
		pool.shutdown();
		try {
			System.out.println("Server closing...");
			if(listener != null)
				listener.close();
		}catch(IOException e) {
			e.printStackTrace();		
		}		
	}
	public boolean isRunning() {
		if(listener.isBound())
			return true;
		return false;
	}

	public void run() {
		Socket sock1;
		try{
			listener = new ServerSocket(58901);
			System.out.println("Go game Server is running...");
			//Tworzymy instancje lobby
			pool = Executors.newFixedThreadPool(20);
			
			while (execute) {
				sock1 = listener.accept();
				if(sock1.isConnected()) {
					Connector connector = new Connector(sock1);
					pool.execute(connector);
				}											
			}			
		}catch(SocketException e) {
			//Nie rob nic, wyjatek zostal wywolany, poniewaz administrator wylacza serwer
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		try {
			if(!pool.awaitTermination(10, TimeUnit.SECONDS))
				pool.shutdownNow();
		}catch(InterruptedException e) {
			pool.shutdownNow();
			//Thread.currentThread().interrupt();
		}
	}	
}
