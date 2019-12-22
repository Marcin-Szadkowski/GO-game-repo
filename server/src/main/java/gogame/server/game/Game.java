package gogame.server.game;

import java.util.LinkedList;
import java.util.List;

public class Game {
	public int size;
	public Stone[][] table;
	public int whitePrisoners =0; //zbite biale kamienie
	public int blackPrisoners =0; //zbite czarne kamienie
	public Stone lastBeat;
	private Playable playerBlack, playerWhite;
	private Playable currentPlayer;
	private int pass = 0; //Kiedy zmienna osiagnie wartosc 2 to gra sie konczy
	
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
	public void setup(Playable player1, Playable player2) {
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
	public synchronized void move(int x, int y, Playable player) {
		//Sprawdzam czy to kolej tego gracza
		if(player == currentPlayer) {
			//Sprawdzam czy wgl podane pole jest wolne
			if(table[x][y] == null) {
				LinkedList<Stone> stonesToBeat = new LinkedList<Stone>();
				//Dodaje tymczasowo ten kamien na plansze
				Stone stone = new Stone(x, y, player.getColor());
				table[x][y]= stone;

				if(GameMethods.isAlive(GameMethods.findGroup(stone, size, table), size, table) == true) {
					//Jezeli grupa jest zywa, to ok, sprawdz czy mamy bicie	
					if(GameMethods.areDead(GameMethods.findGroups(stone, size, table), size, table) == true) {
						//To wykonaj bicie
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
				//Wykonano porpawnie ruch, wiec mozna zmiejszyc wartosc pass
				if(pass == 1)
					pass =0;
				//Wyslij teraz wiadomosci o zmianie stanu gry
				this.youMoved(player, x, y);
				if(player == playerBlack) {			
					currentPlayer = playerWhite;
					this.opponentMoved(playerWhite, x, y);
				}else {
					currentPlayer = playerBlack;
					this.opponentMoved(playerBlack, x, y);
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
	 * Metoda podsumowujaca gre
	 * Wywolywana gdy gracze spasuja po sobie
	 */
	public void summary() {
		GameMethods.countTerritory(this);
		//
		if(blackPrisoners > whitePrisoners) {
			//To wygral bialy bo ma wiecej wiezni+punktow
			playerWhite.victory(blackPrisoners, whitePrisoners);
			playerBlack.defeat(whitePrisoners, blackPrisoners);
		}else if( whitePrisoners > blackPrisoners) {
			//To wygral czarny
			playerBlack.victory(whitePrisoners, blackPrisoners);
			playerWhite.defeat(blackPrisoners, whitePrisoners);
		}else if(whitePrisoners == blackPrisoners) {
			playerBlack.tie(blackPrisoners);
			playerWhite.tie(blackPrisoners);
		}
	}
	/**
	 * Metoda wysylajaca do odpowiedniego gracza wiadomsoc o ruchu przeciwnika
	 * @param player ten gracz ma dostac informacje o ruchu przeciwnika
	 * @param x
	 * @param y 
	 */
	public void opponentMoved(Playable player, int x, int y) {
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
	public void youMoved(Playable player, int x, int y) {
		player.youMoved(x, y);
	}	
	/**
	 * Gracz pomija swoj ruch
	 * @param player gracz pomijajacy ruch
	 */
	public synchronized void pass(Playable player) {
		//Sprawdzic czy to wgl kolej tego gracza na spasowanie
		if(player == currentPlayer) {
			pass++;
			if(pass == 2) {
				this.summary();
			}
			//Zamien aktualnego gracza
			if(player == playerBlack) {
				currentPlayer = playerWhite;
				currentPlayer.yourTurn();
			}				
			else {
				currentPlayer = playerBlack;
				currentPlayer.yourTurn();
			}
							
		}
	}
	/**
	 * Metoda wysylajaca do przeciwnika wiadomosc o odejsciu gracza
	 * @param gracz, ktory opuszcza gre
	 */
	public void quit(Playable player) {
		//Sprobuj wyslac do graczy QUIT
		if(player == playerBlack)
			playerWhite.otherPlayerLeft();
		else
			playerBlack.otherPlayerLeft();
	}	
}
