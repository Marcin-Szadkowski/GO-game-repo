package gogame.client.game;

import gogame.client.gui.GameGui;
import gogame.client.transProtocol.TcpClient;

/**
 * Klasa wywo�uj�ca metod� listen() ,
 * kt�ra nas�uchuje informacji od serwera oraz inicjalizuje gr�.
 * @author wojciech
 *
 */
public class GameMain {
	  public static void main(String[] args) throws Exception {
		
		  TcpClient client = (TcpClient) TcpClient.getInstance();
		  client.listen();
	
	  }
	}