package gogame.client.game;

import gogame.client.gui.GameGui;
import gogame.client.transProtocol.TcpClient;

public class GameMain {
	  public static void main(String[] args) throws Exception {
		 GameGui game = new GameGui();
		  TcpClient client = (TcpClient) TcpClient.getInstance();
		  client.listen();
		 
		 
	  
	  }
	}