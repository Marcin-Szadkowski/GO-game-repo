package gogame.client.connection;

import gogame.client.game.Game;
// tcpClient  (listen )    -> parser(comand interpreter)  -> game(data ) - > (gui)
import gogame.client.gui.GameGui;
import gogame.client.transProtocol.TcpClient;
import gogame.client.transProtocol.TransferProtocol;


//interpretacja danych z tcp client
public  class DataParser extends Converters implements SendDataParser,ReceiveDataParser {
 
	
	GameGui gui=new GameGui();
	TransferProtocol client = (TcpClient)TcpClient.getInstance();
	Converters convert = new Converters();
	
	
	 
	public void receiveYouMoved(String string) {
		gui.yourTurn("OPPONENT TURN");
		int size=gui.size;
		int[] output=new int[2];
		String[] splitString = splitStringIntoTable(string);
		output[0]=Integer.parseInt(splitString[2]);
		output[1]=Integer.parseInt(splitString[1]);
		
		//tamte byly w skali serverowej, teraz konwersja na skale gui
		 int temp1=output[0]; int temp2=output[1];
		output[0]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[0];
		output[1]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[1];
	
		 temp1=output[0]; temp2=output[1];
		output[0] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[0];
		output[1] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[1];
	
			gui.youMoved(output[0], output[1]);reDraw();
		//	gui.setVar(output);
		//	gui.moveAccepted();
			
		
	}
	public void reDraw() {
		gui.refresh();
	}


	public void receiveOponentMoved(String string) {
		System.out.println("jednakmam");
		gui.yourTurn("YOUR TURN");
		int size=gui.size;
		int[] output=new int[2];
		String[] splitString = splitStringIntoTable(string);
		output[0]=Integer.parseInt(splitString[2]);
		output[1]=Integer.parseInt(splitString[1]);
		
		//tamte byly w skali serverowej, teraz konwersja na skale gui
		 int temp1=output[0]; int temp2=output[1];
		output[0]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[0];
		output[1]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[1];
	
		 temp1=output[0]; temp2=output[1];
		output[0] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[0];
		output[1] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[1];
	
			//gui.youMoved(output[0], output[1]);
		
		gui.oponentMoved(output[0], output[1]);reDraw();
		
	}

	public void receiveGameStarted(String s) {
		String[] splitString = splitStringIntoTable(s);
		gui.gameStarted(splitString[1]);
	
	
		
	}

	public void receiveYourTurn() {
		gui.yourTurn("YOUR TURN");
		
		
	}

	public void receiveNotYourTurn() {
		gui.yourTurn("OPPONENT TURN");
	
	}
     //metoda wzraca tablice współrzeych 
	public void receiveDelete(String string) {
		System.out.println("usunięcie doszło");
		int size=gui.size;
		String[] splitString = splitStringIntoTable(string);
		int length = splitString.length;
		int[][] output=new int[length/2][2];
		int index=1;
		//od 1 bo unikam wiadomosci infromacyjnej na pierwszym slocie
		for(int i=0;i<length/2;i++) {
			output[i][0]=Integer.parseInt(splitString[index+1]);
			output[i][1]=Integer.parseInt(splitString[index]);
			
			 int temp1=output[i][0]; int temp2=output[i][1];
			output[i][0]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[0];
			output[i][1]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[1];
			
			 temp1=output[i][0]; temp2=output[i][1];
			output[i][0] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[0];
			output[i][1] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[1];
			
			
			index+=2;
		}
	
	
		gui.delete(output);
		
	}

	public void receiveOtherPlayerLeft() {
		gui.quit();
		
	}

	public void receiveVictory(String string ) {
		String[] output=new String[3];
		String[] splitString = string.split("\\s+");
		output[0]=(splitString[1]);
		output[1]=(splitString[2]);
		
		gui.gameEnded(output[0], output[1]);
		
	}

	public void receivePrisoners(String string) {
		String[] output=new String[3];
		String[] splitString = string.split("\\s+");
		output[0]=(splitString[1]);
		output[1]=(splitString[2]);
		
		gui.getPrisoners(output[0], output[1]);
		
		
	}

	public void receiveDefeat(String string) {
		String[] output=new String[3];
		String[] splitString = string.split("\\s+");
		output[0]=(splitString[1]);
		output[1]=(splitString[2]);
		
		gui.gameEnded(output[0], output[1]);
		
	}

	public void makeMove(int x,int y) {
		
		int oldX=x;
		 int temp1=x; int temp2=y;
		x=convert.coordinatesFromPawnToBoardCovnerter(temp1, temp2)[0];
		y=convert.coordinatesFromPawnToBoardCovnerter(temp1, temp2)[1];
	    
		
		  temp1=x; temp2=y;
		x=convert.coordinatesScaleConverterFromBoard(temp1, temp2, gui.size)[0];
		y=convert.coordinatesScaleConverterFromBoard(temp1, temp2, gui.size)[1];
		String s = null;
		//korekty
		//if((oldX==95) && (gui.size== 19)) s="MOVE ".concat(String.valueOf(x).concat(" ").concat(String.valueOf(y+1)));
		//else if((oldX==128) && (gui.size== 19)) s="MOVE ".concat(String.valueOf(x).concat(" ").concat(String.valueOf(y+1)));
		//else if(gui.size==19) s="MOVE ".concat(String.valueOf(x).concat(" ").concat(String.valueOf(y+2)));
		//else if((oldX==95) && (gui.size== 9)) s="MOVE ".concat(String.valueOf(x).concat(" ").concat(String.valueOf(y)));
		//else if (gui.size == 9)s="MOVE ".concat(String.valueOf(x).concat(" ").concat(String.valueOf(y+1)));
		//else s="MOVE ".concat(String.valueOf(x).concat(" ").concat(String.valueOf(y+1)));
		if((oldX==95) && (gui.size== 19)) s="MOVE ".concat(String.valueOf(y+1).concat(" ").concat(String.valueOf(x)));
		else if((oldX==128) && (gui.size== 19)) s="MOVE ".concat(String.valueOf(y+1).concat(" ").concat(String.valueOf(x)));
		else if(gui.size==19) s="MOVE ".concat(String.valueOf(y+2).concat(" ").concat(String.valueOf(x)));
		else if((oldX==95) && (gui.size== 9)) s="MOVE ".concat(String.valueOf(y).concat(" ").concat(String.valueOf(x)));
		else if (gui.size == 9)s="MOVE ".concat(String.valueOf(y+1).concat(" ").concat(String.valueOf(x)));
		else s="MOVE ".concat(String.valueOf(y+1).concat(" ").concat(String.valueOf(x)));	
			
		
	  System.out.println(s);
		client.sendMessage(s);
		
	}

	public void makePass() {
		
		client.sendMessage("PASS");
	}

	public void makeQuit() {
		client.sendMessage("QUIT");
		
	}

	public void sendSize(int size) {
		
		String s="SIZE ".concat(String.valueOf(size));
		client.sendMessage(s);
		
	}

	
	public void sendType(String type) {
	
		String s="TYPE ".concat(type);
		client.sendMessage(s);
		
		
	}

	public void findGame() {
		
	client.sendMessage("FIND_GAME");
		
	}

	public void receiveConnected() {
		gui.connected();
		
		
	}
	public void commandInterpreter(String response) {
		
		 if(response.startsWith("GAME_STARTED")) {
	    	   receiveGameStarted(response);
	  
	      }
	      else if(response.startsWith("YOU_MOVED")) {
	    	  receiveYouMoved(response);
	    	  
	      }
	      else if(response.startsWith("OPPONENT_MOVED")) {
	    	   receiveOponentMoved(response);
	    	   System.out.println("DOSTAJE INFO OPPONENT MOVED");
	    	  
	    	  
	      }
	    
	      else if(response.startsWith("NOT_YOUR_TURN")) {
	    	  
	    	  receiveNotYourTurn();
	      }
	      else if(response.startsWith("DELETE")) {
	    	  receiveDelete(response);
	      }
	      else if (response.startsWith("OTHER_PLAYER_LEFT")) {
	    	  receiveOtherPlayerLeft();
	    	
	      }
	      else if(response.startsWith("VICTORY")) {
	    	  receiveVictory(response);
	    	 
	    	  
	      }
	      else if(response.startsWith("PRISONERS")) {
	    	  receivePrisoners(response);
	    	 
	      }
	      else if(response.startsWith("DEFEAT")) {
	         receiveDefeat(response);
	    	
	      }
	    		
	}

	
	
	
}
