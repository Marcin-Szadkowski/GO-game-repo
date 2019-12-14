package gogame.client.connection;

public interface ReceiveDataParser {
	public void receiveYouMoved(String s);
	public void receiveOponentMoved(String s);
	public void receiveGameStarted();
	public void receiveYourTurn();
	public void receiveNotYourTurn();
	public void receiveDelete(String s);
	public void receiveOtherPlayerLeft();
	public void receiveVictory();
	public void receivePrisoners(String s);
	public void receiveDefeat();
	public void receiveConnected();

}
