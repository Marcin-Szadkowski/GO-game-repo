package gogame.client.connection;

import gogame.client.game.Game;
// tcpClient  (listen )    -> parser(comand interpreter)  -> game(data ) - > (gui)
import gogame.client.gui.GameGui;
import gogame.client.transProtocol.TcpClient;


//interpretacja danych z tcp client
public  class DataParser extends Converters implements SendDataParser,ReceiveDataParser {
 
	
	GameGui gui=new GameGui();
	TcpClient client = new TcpClient();
	Converters convert = new Converters();
	
	
	 
	public void receiveYouMoved(String string) {
		int size=gui.size;
		int[] output=new int[2];
		String[] splitString = splitStringIntoTable(string);
		output[0]=Integer.parseInt(splitString[1]);
		output[1]=Integer.parseInt(splitString[2]);
		System.out.println(output[0]+" "+output[1]);
		//tamte byly w skali serverowej, teraz konwersja na skale gui
		 int temp1=output[0]; int temp2=output[1];
		output[0]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[0];
		output[1]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[1];
		System.out.println(output[0]+" "+output[1]);
		 temp1=output[0]; temp2=output[1];
		output[0] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[0];
		output[1] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[1];
		System.out.println(output[0]+" "+output[1]);
			gui.youMoved(output[0], output[1]);
		//	gui.setVar(output);
		//	gui.moveAccepted();
			
		
	}

	public void receiveOponentMoved(String string) {
		int size=gui.size;
		int[] output=new int[2];
		String[] splitString = splitStringIntoTable(string);
		output[0]=Integer.parseInt(splitString[1]);
		output[1]=Integer.parseInt(splitString[2]);
		//tamte byly w skali serverowej, teraz konwersja na skale gui
		output[0]=convert.coordinatesScaleConverterFromServer(output[0], output[1],size)[0];
		output[1]=convert.coordinatesScaleConverterFromServer(output[0], output[1],size)[1];
		
		output[0] =convert.coordinatesFromBoardToPawnCovnerter(output[0], output[1])[0];
		output[1] =convert.coordinatesFromBoardToPawnCovnerter(output[0], output[1])[1];
		
		gui.oponentMoved(output[0], output[1]);
		
	}

	public void receiveGameStarted() {
		gui.gameStarted();
		
		
	}

	public void receiveYourTurn() {
		gui.yourTurn();
		
	}

	public void receiveNotYourTurn() {
		gui.oponentTurn();
		
	}
     //metoda wzraca tablice współrzeych 
	public void receiveDelete(String string) {
		int size=gui.size;
		String[] splitString = splitStringIntoTable(string);
		int length = splitString.length;
		int[][] output=new int[length/2][2];
		int index=1;
		//od 1 bo unikam wiadomosci infromacyjnej na pierwszym slocie
		for(int i=0;i<length/2;i++) {
			output[i][0]=Integer.parseInt(splitString[index]);
			output[i][1]=Integer.parseInt(splitString[index+1]);
			
			output[i][0]=convert.coordinatesScaleConverterFromServer(output[i][0], output[i][1],size)[0];
			output[i][1]=convert.coordinatesScaleConverterFromServer(output[i][0], output[i][1],size)[1];
			

			output[i][0] =convert.coordinatesFromBoardToPawnCovnerter(output[i][0], output[i][1])[0];
			output[i][1] =convert.coordinatesFromBoardToPawnCovnerter(output[i][0], output[i][1])[1];
			
			
			index+=2;
		}
		
		 // return output;  
		gui.delete(output);
		
	}

	public void receiveOtherPlayerLeft() {
		gui.quit();
		
	}

	public void receiveVictory() {
		gui.quit();
		
	}

	public void receivePrisoners(String string) {
		int[] output=new int[3];
		String[] splitString = string.split("\\s+");
		output[0]=Integer.parseInt(splitString[1]);
		output[1]=Integer.parseInt(splitString[2]);
		
		gui.oponentMoved(output[0], output[1]);
		
		
	}

	public void receiveDefeat() {
		gui.quit();
		
	}

	public void makeMove(int x,int y) {
		System.out.println(x+" "+y+" przyszło");
		int oldX=x;
		 int temp1=x; int temp2=y;
		x=convert.coordinatesFromPawnToBoardCovnerter(temp1, temp2)[0];
		y=convert.coordinatesFromPawnToBoardCovnerter(temp1, temp2)[1];
	
		
		  temp1=x; temp2=y;
		x=convert.coordinatesScaleConverterFromBoard(temp1, temp2, gui.size)[0];
		y=convert.coordinatesScaleConverterFromBoard(temp1, temp2, gui.size)[1];
		String s;
		
		if(oldX==95) s="MOVE ".concat(String.valueOf(x).concat(" ").concat(String.valueOf(y)));
		else  s="MOVE ".concat(String.valueOf(x).concat(" ").concat(String.valueOf(y+1)));
		
		System.out.println(s);
		receiveYouMoved(s);
		
	}

	public void makePass() {
		
		client.sendMessage("PASS");
	}

	public void makeExit() {
		client.sendMessage("QUIT");
		
	}

	public void sendSize(int size) {
		//client.sendMessage("SIZE "+String.valueOf(size));
		client.sendMessage("SIZE 9");
		System.out.println("size dochodzi do parsera");
	}

	
	public void sendType(String type) {
	//client.sendMessage("TYPE ".concat(type));
		client.sendMessage("TYPE MULTI");
	System.out.println("typ dochodzi do parsera");
		
	}

	public void findGame() {
	client.sendMessage("FIND_GAME");
		
	}

	public void receiveConnected() {
		gui.connected();
		System.out.println("info przechodzi przez parser");
		
	}

	
	
	
}
