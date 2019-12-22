package gogame.client.connection;


import gogame.client.console.GameConsole;
// tcpClient  (listen )    -> parser(comand interpreter)  -> game(data ) - > (gui)
import gogame.client.gui.GameGui;
import gogame.client.transProtocol.TcpClient;
import gogame.client.transProtocol.TransferProtocol;


/**
 * Klasa w której odbywa się przekazywanie oraz konwersja danych pomiędzy klientem a serwerem
 * Klasa implementuje interfejsy odpowiedzialne za wysyłanie jak i odbieranie danych z serwera.
 * @author wojciech
 *
 */
public  class DataParser extends Converters implements SendDataParser,ReceiveDataParser {
 
	GameGui game=new GameGui();
	TransferProtocol client = (TcpClient)TcpClient.getInstance();
	Converters convert = new Converters();
	
	
	/**
	 * Metoda wywołująca w gui rysowanie stone'a bazująca na informacji od servera
	 */
	public void receiveYouMoved(String string) {
		//wyswietlam w gui,że po tym ruchu nastąpi ruch przeciwnika
		game.yourTurn("OPPONENT TURN");
		
		//pobieram rozmiar planszy gry z gui
		int size=game.size;
		int[] output=new int[2];
		//konwertuję informację od servera na tablicę strongów i odpowiednio przydzielam do tabeli
		String[] splitString = splitStringIntoTable(string);
		output[0]=Integer.parseInt(splitString[2]);
		output[1]=Integer.parseInt(splitString[1]);
		
		//konwercja ze skali serwerowej na skalę gui
		
		int temp1=output[0]; int temp2=output[1]; // zmienne zachowujące dane przed ich nadpisaniem 
		output[0]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[0];
		output[1]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[1];
	
		//konwersja ze współrzednych przecięc na współrzeędne potrzebne do narysowania stone'a 
		//na odpowienim przecięciu
		 temp1=output[0]; temp2=output[1];
		output[0] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[0];
		output[1] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[1];
	    
		//wywołanie odpowiednich metod w gui
		game.youMoved(output[0], output[1]);reDraw();

			
		
	}
	public void reDraw() {
		game.refresh();
	}


	/**
	 * Metoda wywołująca w gui rysowanie stone'a przeciwnika bazująca na informacji od servera
	 * budowa metody analogiczna do @{@link #receiveYouMoved(String)}
	 */
	public void receiveOponentMoved(String string) {
		//wyświetlam, że po tym ruchu następi moja kolej na wykonanie ruchu
		game.yourTurn("YOUR TURN");
		int size=game.size;
		int[] output=new int[2];
		String[] splitString = splitStringIntoTable(string);
		output[0]=Integer.parseInt(splitString[2]);
		output[1]=Integer.parseInt(splitString[1]);
		
		int temp1=output[0]; int temp2=output[1];
		output[0]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[0];
		output[1]=convert.coordinatesScaleConverterFromServer(temp1, temp2,size)[1];
	
	    temp1=output[0]; temp2=output[1];
		output[0] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[0];
		output[1] =convert.coordinatesFromBoardToPawnCovnerter(temp1, temp2)[1];
	
		
		game.oponentMoved(output[0], output[1]);reDraw();
		
	}
    /**
     * Metoda konwertująca infomrację od serwera o rozpoczętej grze.
     * Wyodrębnia infomrację o kolorze gracza i wysyła ją do gui.
     */
	public void receiveGameStarted(String s) {
		String[] splitString = splitStringIntoTable(s);
		game.gameStarted(splitString[1]);
	
	
		
	}
    /**
     * Metoda interpretująca dane wysłane od serwera,
     * która informuje gracza, że teraz jego kolej rozgrywki
     */
	public void receiveYourTurn() {
		game.yourTurn("YOUR TURN");
			
	}
	 /**
     * Metoda interpretująca dane wysłane od serwera,
     * która informuje gracza, że teraz kolej przeciwnika
     */
	public void receiveNotYourTurn() {
		game.yourTurn("OPPONENT TURN");
	
	}
    
	
	/**
	 * Metoda interpretuje infrormację od serwera i zwraca tablicę współrzenych,
	 * tych pionków, które  muszą zostac usunięte
	 */
	public void receiveDelete(String string) {
		System.out.println("usunięcie doszło");
		int size=game.size;
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
	
	
		game.delete(output);
		
	}
    
	public void receiveOtherPlayerLeft() {
		game.otherPlayerLeft();
		System.out.println("DOSTAJE W PARSER OTHER LEFT");
		
	}
    /**
     * Metoda interpretująca dane od serwera,
     * informująca gui ,że gra się zakończyła 
     * oraz przekazująca ostateczne punkty zdobyte
     *  przez graczy
     */
	public void receiveVictory(String string ) {
		System.out.println(string);
		String[] output=new String[3];
		String[] splitString = string.split("\\s+");
		output[0]=(splitString[1]);
		output[1]=(splitString[2]);
		
		game.gameEnded(splitString[0],splitString[1],splitString[2]);
		client.stop();
		
	}
    /**
     * Metoda interpretująca dane od serwera, która zwraca
     * aktualny stan liczbowy zmiennych Prisoners do rozgrywki
     */
	public void receivePrisoners(String string) {
		String[] output=new String[3];
		String[] splitString = string.split("\\s+");
		output[0]=(splitString[1]);
		output[1]=(splitString[2]);
		
		game.getPrisoners(output[0], output[1]);
		
		
		
	}
	  /**
     * Metoda interpretująca dane od serwera,
     * informująca gui ,że gra się zakończyła 
     * oraz przekazująca ostateczne punkty zdobyte
     *  przez graczy
     */
	public void receiveDefeat(String string) {
		System.out.println(string);
		//String[] output=new String[3];
		String[] splitString = string.split("\\s+");
		//output[0]=(splitString[1]);
		//output[1]=(splitString[2]);
		
		game.gameEnded(splitString[0],splitString[1],splitString[2]);
		client.stop();
		
	}

	/**
	 * Metoda, która wysyła do serwera informację o ruchu wykonanym przez gracza
	 */
	public void makeMove(int x,int y) {
		
		int oldX=x;
		 int temp1=x; int temp2=y;
		x=convert.coordinatesFromPawnToBoardCovnerter(temp1, temp2)[0];
		y=convert.coordinatesFromPawnToBoardCovnerter(temp1, temp2)[1];
	    
		
		  temp1=x; temp2=y;
		x=convert.coordinatesScaleConverterFromBoard(temp1, temp2, game.size)[0];
		y=convert.coordinatesScaleConverterFromBoard(temp1, temp2, game.size)[1];
		String s = null;
		
		//korekty
		if((oldX==95) && (game.size== 19)) s="MOVE ".concat(String.valueOf(y+1).concat(" ").concat(String.valueOf(x)));
		else if((oldX==128) && (game.size== 19)) s="MOVE ".concat(String.valueOf(y+1).concat(" ").concat(String.valueOf(x)));
		else if(game.size==19) s="MOVE ".concat(String.valueOf(y+2).concat(" ").concat(String.valueOf(x)));
		else if((oldX==95) && (game.size== 9)) s="MOVE ".concat(String.valueOf(y).concat(" ").concat(String.valueOf(x)));
		else if (game.size == 9)s="MOVE ".concat(String.valueOf(y+1).concat(" ").concat(String.valueOf(x)));
		else s="MOVE ".concat(String.valueOf(y+1).concat(" ").concat(String.valueOf(x)));	
			
		
		client.sendMessage(s);
		
	}

	/**
	 * Metoda, która zwraca informację do serwera, że gracz spasował.
	 */
	public void makePass() {
		
		client.sendMessage("PASS");
	}
	/**
	 * Metoda, która zwraca informację do serwera, że gracz zakończył grę.
	 */
	public void makeQuit() {
		client.sendMessage("QUIT");
		System.out.println("WYSYLAM QUIT DO SERVERA Z PARSERA");
		
	}
	/**
	 * Metoda, która zwraca informację do serwera o wybranym przez gracza rozmiarze planszy do gry.
	 */
	public void sendSize(int size) {
		
		String s="SIZE ".concat(String.valueOf(size));
		client.sendMessage(s);
		
	}

	/**
	 * Metoda, która zwraca informację do serwera o wybranym przez gracza typie gry.
	 */
	public void sendType(String type) {
	
		String s="TYPE ".concat(type);
		client.sendMessage(s);
		
		
	}

	/**
	 * Metoda, która zwraca informację do serwera, żeby ten spróbował stworzyć grę
	 *  dla odpowiednych parametrów wyboru.
	 */
	public void findGame() {
		
	client.sendMessage("FIND_GAME");
		
	}
     
	public void receiveConnected() {
		game.connected();
		
		
	}
	
    public void receiveTie(String s) {
		
		
	}
	
	/**
	 * Metoda interpretująca dane wysłane od serwera oraz zarządzająca
	 * wykonywaniem metod w klasie DataParser
	 * @param response
	 */
	public void commandInterpreter(String response) {
	
		
		 if(response.startsWith("GAME_STARTED")) {
	    	   receiveGameStarted(response);
	    	  // receiveGameGuiStarted();
	  
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
	      else if(response.startsWith("TIE")) {
		         receiveDefeat(response);
		    	
		    } 
		 
	}
	

	
	
	
}
