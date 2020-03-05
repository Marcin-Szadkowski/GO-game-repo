package gogame.client.game;

import gogame.client.gui.GameGui;
import gogame.client.transProtocol.TcpClient;

/**
 * Klasa wywo³uj¹ca metodê listen() ,
 * która nas³uchuje informacji od serwera oraz inicjalizuje grê.
 * @author wojciech
 *
 */
public class GameMain {
	  public static void main(String[] args) throws Exception {
		
		  TcpClient client = (TcpClient) TcpClient.getInstance();
		  client.listen();
	
	  }
	}