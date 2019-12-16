package gogame.server.transProtocol.TCP;

import java.util.Scanner;

public class ServerMain {
	public static void main(String[] args) {
		TcpServer server = (TcpServer) TcpServer.getInstance();
		Thread serverThread = new Thread(server);
		
		Scanner input = new Scanner(System.in);
		String command;
		/*System.out.println("Type command: run, to run the server");
		
		while(input.hasNextLine()) {
			command = input.next();
			if(command.startsWith("run")) {
				serverThread.start();			
				break;
			}
		}*/
		
		serverThread.start();
		System.out.println("Server uruchomiony");
		
		while(input.hasNextLine()) {
			command = input.next();
			if(command.startsWith("stop")) {
				server.stop();
				
			}else if(command.startsWith("exit")) {
				input.close();
				System.exit(0);
			}
		}		
	}
}
