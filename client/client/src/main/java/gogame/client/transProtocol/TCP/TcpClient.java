package gogame.client.transProtocol.TCP;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import gogame.client.transProtocol.TransferProtocol;

public class TcpClient implements TransferProtocol {
private String serverAdres;
	
	private Socket socket;
    private Scanner in;
    private PrintWriter out; 
    
    
	public TcpClient(String serverAdres) {
		this.serverAdres= serverAdres;
	
	}
	
	public void initialize() throws Exception {
		// TODO Auto-generated method stub
		
		
		socket = new Socket(serverAdres, 8888);
		in = new Scanner(socket.getInputStream());
		out = new PrintWriter(socket.getOutputStream(),true);
		
	
	
		
	}

	public void sendMessage() {
		// TODO Auto-generated method stub
		
	}

	public void recvMessage() {
		// TODO Auto-generated method stub
		
	}
	public String input() {
		return in.nextLine();
		
		
	}
	public void  output(String string) {
		out.println(string);
		
		
	}

}
