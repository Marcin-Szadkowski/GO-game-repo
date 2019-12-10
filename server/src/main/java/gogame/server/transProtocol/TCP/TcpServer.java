package gogame.server.transProtocol.TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import gogame.server.transProtocol.TransferProtocol;
import gogame.server.lobby.*;
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
	
	public void initialize() {
				
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
		Lobby.getInstance().closeLobby();
		try {
			System.out.println("Server closing...");
			if(listener != null)
				listener.close();
		}catch(IOException e) {
			e.printStackTrace();		
		}		
	}

	@Override
	public void run() {
		Socket sock1;
		try{
			listener = new ServerSocket(58901);
			System.out.println("Go game Server is running...");
			//Tworzymy instancje lobby
			GamesHandler lobby = Lobby.getInstance();
			System.out.println("Uruchomiono lobby");
			
			while (execute) {
				sock1 = listener.accept();
				PlayerInLobby player1 = new PlayerInLobby(new TcpOutput(sock1), new TcpInput(sock1));
				lobby.addPlayer(player1);
												
			}			
		}catch(SocketException e) {
			//Nie rob nic, wyjatek zostal wywolany, poniewaz administrator wylacza serwer
		}
		catch(IOException e) {
			e.printStackTrace();
		}							
	}	
}
