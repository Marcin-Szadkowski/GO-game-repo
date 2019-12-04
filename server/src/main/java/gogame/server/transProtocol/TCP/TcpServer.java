package gogame.server.transProtocol.TCP;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	
	private volatile static TcpServer instance;
	
	public void initialize() {
		Socket sock1, sock2;
		try{
			ServerSocket listener = new ServerSocket(58901);
			System.out.println("Go game Server is running...");
			ExecutorService pool = Executors.newFixedThreadPool(20);
			while (true) {
				Game game = new Game();
				sock1 = listener.accept();
				Player player1 = new Player(new TcpOutput(sock1), new TcpInput(sock1), "Black" );
				pool.execute(player1);
				
				sock2 = listener.accept();
				Player player2 = new Player(new TcpOutput(sock2), new TcpInput(sock2), "White" );
				pool.execute(player2);
				
			}
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
	public TransferProtocol getInstance() {
		if(instance == null) {
			synchronized (TcpServer.class) {
				if(instance == null) {
					instance = new TcpServer();
				}
			}
		}
		return instance;
	}
	public static void main(String[] args) throws Exception {
		
	}
	

}
