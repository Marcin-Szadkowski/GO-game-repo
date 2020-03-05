package gogame.server.game;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;
import org.mockito.Mockito;

public class GameTest {

	@Test
	/**
	 * Test sprawdzajacy czy po wywolaniu gameStarted w klasie Game sa wywolywane odpowiednie 
	 * metody w klasie Player
	 * @see Game#setup()
	 * @see Game#gameStarted()
	 * @see Player#gameStarted()
	 */
	public void gameStartedTest() {
		Game game = new Game(new Integer(19));
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		
		Mockito.doNothing().when(player1).gameStarted();
		Mockito.doNothing().when(player2).gameStarted();
		
		game.setup(player1, player2);
		
		Mockito.verify(player1).gameStarted();
		Mockito.verify(player2).gameStarted();
	}
	/**
	 * Test metody summary
	 * @see Game#summary()
	 */
	@Test
	public void summaryTest() {
		Player playerBlack = Mockito.mock(Player.class);
		Player playerWhite = Mockito.mock(Player.class);
		Game game = new Game(9);
		game.setup(playerBlack, playerWhite);
		
		game.blackPrisoners = 1;		
		game.summary();
		Mockito.verify(playerWhite).victory(1, 0);
		Mockito.verify(playerBlack).defeat(0, 1);
		
		//Teraz dajemy wiecej punktow graczowi czarnemu
		game.whitePrisoners = 2;
		game.summary(); //Wywolujemy funkcje
		Mockito.verify(playerBlack).victory(2, 1);
		Mockito.verify(playerWhite).defeat(1, 2);
		
		//Teraz doprowadzamy do remisu
		game.blackPrisoners =2;
		game.summary();
		Mockito.verify(playerBlack).tie(2);
		Mockito.verify(playerWhite).tie(2);		
	}
	/**
	 * Test metody pass()
	 * @see Game#pass(Playable)
	 */
	@Test
	public void passTest() {
		Player playerBlack = Mockito.mock(Player.class);
		Player playerWhite = Mockito.mock(Player.class);
		Game game = new Game(9);
		game.setup(playerBlack, playerWhite);
		
		game.pass(playerBlack);
		game.pass(playerWhite);
		Mockito.verify(playerWhite).opponentPassed();
		Mockito.verify(playerBlack).opponentPassed();
	}
	/**
	 * Test metody quit()
	 * @see Game#quit(Playable)
	 */
	@Test
	public void quitTest() {
		Player playerBlack = Mockito.mock(Player.class);
		Player playerWhite = Mockito.mock(Player.class);
		Game game = new Game(9);
		game.setup(playerBlack, playerWhite);
		//Czarny gracz wysyla quit
		game.quit(playerBlack);
		Mockito.verify(playerWhite).otherPlayerLeft();
		//Bialy gracz wysyla quit
		game.quit(playerWhite);
		Mockito.verify(playerBlack).otherPlayerLeft();
	}
	/**
	 * Test metody youMoved(
	 * @see Game#youMoved(Playable, int, int)
	 */
	@Test 
	public void youMovedTest() {
		Player playerBlack = Mockito.mock(Player.class);
		Player playerWhite = Mockito.mock(Player.class);
		Game game = new Game(9);
		game.setup(playerBlack, playerWhite);
		
		game.youMoved(playerWhite, 3, 2);
		Mockito.verify(playerWhite).youMoved(3, 2);
	}
	/**
	 * Test metody prisoners()
	 * @see Game#prisoners()
	 */
	@Test
	public void prisonersTest() {
		Player playerBlack = Mockito.mock(Player.class);
		Player playerWhite = Mockito.mock(Player.class);
		Game game = new Game(9);
		game.setup(playerBlack, playerWhite);
		
		game.prisoners();
		Mockito.verify(playerBlack).prisoners(0, 0);
		Mockito.verify(playerWhite).prisoners(0, 0);		
	}
	/**
	 * Test metody delete()
	 * @see Game#delete(LinkedList)
	 */
	@Test
	public void deleteTest() {
		Player playerBlack = Mockito.mock(Player.class);
		Player playerWhite = Mockito.mock(Player.class);
		Game game = new Game(9);
		game.setup(playerBlack, playerWhite);
		LinkedList<Stone> stones = new LinkedList<Stone>();
		
		game.delete(stones);
		Mockito.verify(playerBlack).delete(stones);
		Mockito.verify(playerWhite).delete(stones);
	}
	/**
	 * Test metody
	 * @see Game#opponentMoved(Playable, int, int)
	 */
	@Test
	public void opponentMovedTest() {
		Player playerBlack = Mockito.mock(Player.class);
		Player playerWhite = Mockito.mock(Player.class);
		Game game = new Game(9);
		game.setup(playerBlack, playerWhite);
		
		game.opponentMoved(playerWhite, 6, 4);
		
		Mockito.verify(playerWhite).opponentMoved(6, 4);
	}
	/**
	 * Test metody
	 * @see Game#move(int, int, Playable)
	 */
	@Test
	public void moveTest() {
		Player playerBlack = Mockito.mock(Player.class);
		Player playerWhite = Mockito.mock(Player.class);
		Game game = new Game(9);
		game.setup(playerBlack, playerWhite);
		
		Mockito.when(playerBlack.getColor()).thenReturn("black");
		Mockito.when(playerWhite.getColor()).thenReturn("white");
		
		//Najpierw probuje wykonac ruch gracz bialy poza swoja kolejka
		game.move(0, 0, playerWhite);
		Mockito.verify(playerWhite).notYourTurn();
		
		
		//Teraz ruch wykonuje gracz czarny
		game.move(1, 1, playerBlack);
		Mockito.verify(playerBlack).youMoved(1, 1);
		Mockito.verify(playerWhite).opponentMoved(1, 1);
		
		//Teraz ustawiam na planszy kilka kamieni zeby doprowadzic do bicia
		game.move(1, 0, playerWhite);
		game.pass(playerBlack);
		game.move(0, 1, playerWhite);
		game.pass(playerBlack);
		game.move(2, 1, playerWhite);
		game.move(2, 2, playerBlack);
		game.move(1, 2, playerWhite);
		
		Mockito.verify(playerWhite).youMoved(1, 2);
		Mockito.verify(playerBlack).opponentMoved(1, 2);
		
		//Teraz sprawdzam inne bicie
		game.move(2, 0, playerBlack);
		game.pass(playerWhite);
		game.move(2, 2, playerBlack);
		game.pass(playerWhite);
		game.move(3, 1, playerBlack);
		game.pass(playerWhite);
		game.move(1, 1, playerBlack);
		Mockito.verify(playerBlack, Mockito.times(2)).youMoved(1, 1);
		Mockito.verify(playerWhite, Mockito.times(2)).opponentMoved(1, 1);
		
		game.move(2, 1, playerWhite);
		Mockito.verify(playerWhite, Mockito.times(1)).youMoved(2, 1);
		
		game.pass(playerWhite);
		game.move(4, 4, playerBlack);
		Mockito.verify(playerBlack).youMoved(4, 4);
		game.move(2, 1, playerWhite);
		Mockito.verify(playerWhite, Mockito.times(2)).youMoved(2, 1);
		
		//Teraza sprobuje wykonac ruch samobojczy
		game.table[2][0] = null;
		game.move(1, 1, playerBlack);
		//Dwa wywolania to porzednie wywolanai kiedy ruch byl dozwolony
		Mockito.verify(playerBlack, Mockito.times(2)).youMoved(1, 1);
	}
	
}
