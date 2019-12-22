package gogame.client.pawn;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javax.swing.JPanel;

import gogame.client.board.BoardGui;
import gogame.client.connection.DataParser;
import gogame.client.gui.ClickAreaSquare;

/**
 * Klasa tworz¹ca JPanel, który bêdzie g³ównym obiektem graficznym rozgrywki.
 * Klasa obs³uguje rysowanie planszy zarówno jak i  posiada metody dodaj¹ce oraz usuwaj¹ce 
 * poszczególne pionki z  planszy.
 * @author wojciech
 *
 */
public class PawnPanel extends JPanel{
	
/**
 * lista zawieraj¹ca wszystkie pionki rozgrywki
 */
public List<Pawn> pawns = new LinkedList<Pawn>();
/**
 * lista zawieraj¹ca wszystkie POMOCNICZE kwadraty rozgrywki, które determinuj¹ czy w ich obrêbie nastêpi³o klikniêcie myszk¹
 */
public List<ClickAreaSquare> squares = new LinkedList<ClickAreaSquare>();
public static int type;
public int boardSize;
DataParser parser = new DataParser();

	
	/**
	 * Metoda dodaj¹ca pionek(stone) do @{@link #pawns}
	 * @param pawn  obiekt pawn który chcemy dodac do listy 
	 */
	public void addPawn(Pawn pawn) {
		pawns.add(pawn);
		this.repaint();
	}
	

	
	/**
	 * Metoda przyjmuj¹ca tablicê wspó³rzednych i na jej podstawie usuwa pionki(stones) z listy @{@link #pawns}
	 * @param tab  tablica wspó³rzednych pionków do usuniêcia
	 */
	public void removePawn(int tab[][]) {
		int temp=0;
		int k=0;
		while(k!=tab.length) {
		
			//stosujê wzorzec iterator aby zapobiec ConcurrentModificationException
			for (Iterator<Pawn> it = pawns.iterator(); it.hasNext();) {
	            Pawn next = it.next();
	            if (next.getX()==tab[k][0] && next.getY()==tab[k][1]  )  {
	                it.remove();
	              
	            }  
	        }this.repaint(); 
			
		k++;
		}
		this.repaint();
	
	}
	/**
	 * Metoda dodaj¹ca kwadrat do @{@link #squares}
	 * @param square  obiekt square który chcemy dodac do listy
	 */
	public void addSquare(ClickAreaSquare square) {
		squares.add(square);
		this.repaint();
	}
	

	/**
	 * Metoda która rysuje planszê wraz z wszystkimi komponentami ( pionki, kwadraty pomocnicze, linie planszy)
	 */
	public void paint(Graphics g) {

		//tworzenie obiektu typu BoardGui w celu zadeklarowania parametrów do rysowania planszy
		BoardGui board = new BoardGui(boardSize);
		
		//deklaracja parametrów na podstawie planszy wybranej prze gracza
		int size=board.size();
		int x=board.stepWidth();
		int y=board.stepHeight();

		//rysowanie planszy (siatki na której przeciêciach mo¿na stawiac pionki)
		for(int i=0; i<= size ; i++) {
			g.drawLine(boardCor(size,x,y)[i][0]+7, boardCor(size,x,y)[i][1]+30, boardCor(size,x,y)[(size+1)*(size+1)-size-1 +i][0]+7,boardCor(size,x,y)[(size+1)*(size+1)-size-1 +i][1]+30);
		}
		
		for(int i=0; i<= size ; i++) {
			g.drawLine(boardCor(size,x,y)[(size+1)*i][0]+7,boardCor(size,x,y)[(size+1)*i][1]+30,boardCor(size,x,y)[(size+1)*i+size][0]+7,boardCor(size,x,y)[(size+1)*i][1]+30);
		}
		
		//square width i height na 24 na sztywniaka  
		for(int i=0 ; i<(size+1)*(size+1); i++) {	
			 squares.add(new ClickAreaSquare(boardCor(size,x,y)[i][0]-12+7,boardCor(size,x,y)[i][1]-12+30,24,24));
			}
		
		// rysowanie wszystkich pionków jakie aktualnie s¹ na liœcie pawns
		for(Pawn p  : pawns) {
			
			p.draw(g);
			
				
		}
		//metoda rysuj¹ca pomocnicze kwadraty wizualizuj¹ce obszar klikniêcia na planszy
		/*for(ClickAreaSquare s  : squares) {
			
			s.draw(g);
			
				
		}	*/
		
	}
	/**
	 * Metoda która na podstawie przesuniêc charakterystycznych dla wybranej przez gracza planszy oraz jej rozmiarze 
	 * generuje  tablicê dwuwymiarow¹ zawieraj¹c¹ wszystkie wspó³rzêdne przeciêc siatki gry (generuje wszystkie mozliwe srodki dla pionków w grze)
	 * @param size  rozmiar planszy wybrany w grze
	 * @param widthStep  charakterystyczne przesuniêcie dla planszy o rozmiarze size
	 * @param heightStep charakterystyczne przesuniêcie dla planszy o rozmiarze size
	 * @return
	 */
	public int[][] boardCor(int size,int widthStep,int heightStep) {
		int[][] boardCoordinates= new int[(size+1)*(size+1)][2];
		//coordinates of upper left corner  (0,heighStep)
		int iterator =0;
		for(int i=1 ; i<=size+1;i++)
		{
			
			for(int j=0 ; j<=size; j++)
			{
				boardCoordinates[iterator][0]=j*widthStep+100;
				boardCoordinates[iterator][1]=heightStep*i;
			//	System.out.print(" ["+boardCoordinates[iterator][0]+" , "+boardCoordinates[iterator][1]+" ]" );
				iterator ++;
			}
		//	System.out.println("\n");
				
		}
		//System.out.println(boardCoordinates);
		return boardCoordinates;
	}
	
	
	
}