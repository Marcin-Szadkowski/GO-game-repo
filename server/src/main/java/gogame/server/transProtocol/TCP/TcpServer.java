package gogame.server.transProtocol.TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.net.ssl.internal.www.protocol.https.Handler;

import gogame.server.game.Game;
import gogame.server.transProtocol.TransferProtocol;
import gogame.server.game.Player;
/**
 * Klasa rozszerzajaca interface TransferProtocol
 * W oparciu o TCP
 * @author marcin
 * @see TransferProtocol
 */
public class TcpServer implements TransferProtocol {
	private ServerSocket listener;
	private volatile static TcpServer instance;
	private boolean execute = true;
	
	public void initialize() {
		Socket sock1, sock2;
		try{
			listener = new ServerSocket(58901);
			System.out.println("Go game Server is running...");
			ExecutorService pool = Executors.newFixedThreadPool(20);
			
			while (execute) {
				Game game = new Game();
				sock1 = listener.accept();
				Player player1 = new Player(new TcpOutput(sock1), new TcpInput(sock1), "Black" );
				pool.execute(player1);
				
				sock2 = listener.accept();
				Player player2 = new Player(new TcpOutput(sock2), new TcpInput(sock2), "White" );
				pool.execute(player2);
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}					
	}

	public void sendMessage() {
		// TODO Auto-generated method stub
		
	}

	public void recvMessage() {
		// TODO Auto-generated method stub
		
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
		//System.out.println("Server closing...");
		try {
			System.out.println("Server closing...");
			if(listener != null)
				listener.close();
		}catch(IOException e) {
			e.printStackTrace();		
		}		
	}
	
	public static void main(String[] args) throws Exception {
		TcpServer server = (TcpServer) TcpServer.getInstance();
		server.initialize();
	}
	

}
