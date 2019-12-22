package gogame.server.game;

import java.util.LinkedList;
import java.util.List;

public class Game {
	public int size;
	public Stone[][] table;
	public int whitePrisoners =0; //zbite biale kamienie
	public int blackPrisoners =0; //zbite czarne kamienie
	private Stone lastBeat;
	private Player playerBlack, playerWhite;
	private Player currentPlayer;
	
	public Game(int size) {
		this.size = size;
		table = new Stone[size][size];
	}
	/**
	 * Metoda przypisujaca graczy do powstalej gry
	 * Ponadto metoda ustawia pierwszego gracza jako gracza, ktory zaczyna gre
	 * @param player1
	 * @param player2
	 */
	public void setup(Player player1, Player player2) {
		currentPlayer = player1;
		this.playerBlack = player1;
		this.playerWhite = player2;
		gameStarted();
		
	}
	/**
	 * Metoda wykonujaca ruch na planszy
	 * @param x wspolrzedna x ruchu
	 * @param y wspolrzedna y ruchu
	 * @param player gracz wykonujacy ruch
	 */
	public synchronized void move(int x, int y, Player player) {
		//Sprawdzam czy to kolej tego gracza
		if(player == currentPlayer) {
			//Sprawdzam czy wgl podane pole jest wolne
			if(table[x][y] == null) {
				LinkedList<Stone> stonesToBeat = new LinkedList<Stone>();
				//Dodaje tymczasowo ten kamien na plansze
				Stone stone = new Stone(x, y, player.color);
				table[x][y]= stone;

				if(GameMethods.isAlive(GameMethods.findGroup(stone, size, table), size, table) == true) {
					//Jezeli grupa jest zywa, to ok, sprawdz czy mamy bicie	
					if(GameMethods.areDead(GameMethods.findGroups(stone, size, table), size, table) == true) {
						//To wykonaj bicie
						System.out.println("Wykonuje bicie");
						 stonesToBeat = GameMethods.beatStones(GameMethods.findGroups(stone, size, table), this);
						 if(stonesToBeat.size() == 1)
							 lastBeat = stonesToBeat.get(0);
						 else
							 lastBeat = null;
					}else 
						lastBeat =null;
				} else {
					//Jezeli grupa nie jest zywa to sprawdz czy mamy bicie(wtedy to nie ruch samobojczy)
					if(GameMethods.areDead(GameMethods.findGroups(stone, size, table), size, table) == true) {
						//To ruch jest ok. Wykonaj bicie martwej grupy jesli to nie jest KO
						if(lastBeat == null || lastBeat.x != x || lastBeat.y != y) {
							System.out.println("Ruch nie jest samobojczy bo wykonuje bicie i to nie KO");
							stonesToBeat = GameMethods.beatStones(GameMethods.findGroups(stone, size, table), this);
							if(stonesToBeat.size() == 1)
								lastBeat = stonesToBeat.get(0);
							else
								lastBeat = null;
						}else {
							//Ruch nie jest ok. Nie wykonuj ruchu. Usun ten kamien z planszy
							table[x][y] = null;
							return;
						}
						
					}else {
						//Ruch nie jest ok. Nie wykonuj ruchu. Usun ten kamien z planszy
						table[x][y] = null;
						return;
					}
				}
				//Wyslij teraz wiadomosci o zmianie stanu gry
				this.youMoved(player, x, y);
				if(player == playerBlack) {
					this.opponentMoved(playerWhite, x, y);
					currentPlayer = playerWhite;
				}else {
					this.opponentMoved(playerBlack, x, y);
					currentPlayer = playerBlack;
				}
				//Wyslij info o kamieniach ktore trzeba usunac z planszy
				if(!stonesToBeat.isEmpty()) {
					this.delete(stonesToBeat);
					//Wyslij infromacje o wiezniach
					this.prisoners();
				}
					
			}
			
		}else {
			player.notYourTurn();
		}
	}
	/**
	 * Metoda wysylajaca do odpowiedniego gracza wiadomsoc o ruchu przeciwnika
	 * @param player ten gracz ma dostac informacje o ruchu przeciwnika
	 * @param x
	 * @param y 
	 */
	public void opponentMoved(Player player, int x, int y) {
		player.opponentMoved(x, y);
	}

	/**
	 * Metoda wysylajaca do graczy informacje o rozpoczeciu gry
	 * Wywolywana w Lobby po dolaczeniu graczy do gry
	 */
	private void gameStarted() {
		playerBlack.gameStarted();
		playerWhite.gameStarted();
	}
	/**
	 * Metoda wysylajaca informacje o kamieniach do usuniecia z planszy
	 * @param groups
	 */
	public void delete(LinkedList<Stone> stones) {
		playerBlack.delete(stones);
		playerWhite.delete(stones);
	}
	/**
	 * Metoda wysylajaca do gracza informacje o stanie wiezniow
	 * @param x
	 * @param y
	 */
	public void prisoners() {
		//Wyslij do gracza info o stanie wiezniow
		//Jako pierwszy argument gracz dostaje te ktore zbil
		playerBlack.prisoners(whitePrisoners, blackPrisoners);
		playerWhite.prisoners(blackPrisoners, whitePrisoners);
	}
	/**
	 * Metoda informujaca gracza o poprawnie wykonanym ruchu
	 * @param x
	 * @param y
	 * @see Player#youMoved(int, int)
	 */
	public void youMoved(Player player, int x, int y) {
		player.youMoved(x, y);
	}	
	/**
	 * Gracz pomija swoj ruch
	 * @param player gracz pomijajacy ruch
	 */
	public void pass(Player player) {
		//Sprawdzic czy to wgl kolej tego gracza na spasowanie
		if(player == currentPlayer) {
			
		}
	}
	/**
	 * Metoda wysylajaca do przeciwnika wiadomosc o odejsciu gracza
	 * @param gracz, ktory opuszcza gre
	 */
	public void quit(Player player) {
		//Sprobuj wyslac do graczy QUIT
	}
	
}
