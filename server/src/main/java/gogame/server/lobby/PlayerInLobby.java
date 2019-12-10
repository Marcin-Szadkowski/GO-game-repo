package gogame.server.lobby;

import gogame.server.transProtocol.Input;
import gogame.server.transProtocol.Output;

public class PlayerInLobby implements Runnable {
	Integer gameSize;
	String gameType;
	Input input;
	Output output;
	String state;
	
	public PlayerInLobby(Output output, Input input) {
		this.input = input;
		this.output = output;
		this.state = "WAITING";
	}
	
	@Override
	public void run() {
		String command;
		//Dopoki nie ma ustawionych rozmiaru planszy ani typu gry
		//To nasluchuj sygnalow w celu ich ustawienia
		while(state == "WAITING"){
			if(input.hasNextLine()) {
				command = input.nextLine();
				if(command.startsWith("SIZE")) {
					setGameSize(command);
				}else if( command.startsWith("TYPE")) {
					setType(command);
				}else if(command.startsWith("QUIT")) {
					state = "QUIT";
					Lobby.getInstance().deletePlayer(this);
					input.closeSocket();				
					break;
				}
			}
			if(isReady())
				Lobby.getInstance().findGame();
		}		
	}
	private void setType(String command){
		gameType = command.substring(5);
	}
	private void setGameSize(String command) {
		gameSize = Integer.parseInt(command.substring(5));
	}
	public boolean isReady() {
		if(gameSize != null && gameType != null)
			return true;
		return false;
	}
	public Integer getGameSize() {
		if(gameSize != null)
			return gameSize;
		return null;
	}
	public Output getOutput() {
		if(output != null)
			return this.output;
		return null;
	}
	public Input getInput() {
		if(input != null)
			return this.input;
		return null;
	}

}
