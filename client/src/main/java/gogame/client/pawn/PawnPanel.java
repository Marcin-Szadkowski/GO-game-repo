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
 * Klasa tworz�ca JPanel, kt�ry b�dzie g��wnym obiektem graficznym rozgrywki.
 * Klasa obs�uguje rysowanie planszy zar�wno jak i  posiada metody dodaj�ce oraz usuwaj�ce 
 * poszczeg�lne pionki z  planszy.
 * @author wojciech
 *
 */
public class PawnPanel extends JPanel{
	
/**
 * lista zawieraj�ca wszystkie pionki rozgrywki
 */
public List<Pawn> pawns = new LinkedList<Pawn>();
/**
 * lista zawieraj�ca wszystkie POMOCNICZE kwadraty rozgrywki, kt�re determinuj� czy w ich obr�bie nast�pi�o klikni�cie myszk�
 */
public List<ClickAreaSquare> squares = new LinkedList<ClickAreaSquare>();
public static int type;
public int boardSize;
DataParser parser = new DataParser();

	
	/**
	 * Metoda dodaj�ca pionek(stone) do @{@link #pawns}
	 * @param pawn  obiekt pawn kt�ry chcemy dodac do listy 
	 */
	public void addPawn(Pawn pawn) {
		pawns.add(pawn);
		this.repaint();
	}
	

	
	/**
	 * Metoda przyjmuj�ca tablic� wsp�rzednych i na jej podstawie usuwa pionki(stones) z listy @{@link #pawns}
	 * @param tab  tablica wsp�rzednych pionk�w do usuni�cia
	 */
	public void removePawn(int tab[][]) {
		int temp=0;
		int k=0;
		while(k!=tab.length) {
		
			//stosuj� wzorzec iterator aby zapobiec ConcurrentModificationException
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
	 * Metoda dodaj�ca kwadrat do @{@link #squares}
	 * @param square  obiekt square kt�ry chcemy dodac do listy
	 */
	public void addSquare(ClickAreaSquare square) {
		squares.add(square);
		this.repaint();
	}
	

	/**
	 * Metoda kt�ra rysuje plansz� wraz z wszystkimi komponentami ( pionki, kwadraty pomocnicze, linie planszy)
	 */
	public void paint(Graphics g) {

		//tworzenie obiektu typu BoardGui w celu zadeklarowania parametr�w do rysowania planszy
		BoardGui board = new BoardGui(boardSize);
		
		//deklaracja parametr�w na podstawie planszy wybranej prze gracza
		int size=board.size();
		int x=board.stepWidth();
		int y=board.stepHeight();

		//rysowanie planszy (siatki na kt�rej przeci�ciach mo�na stawiac pionki)
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
		
		// rysowanie wszystkich pionk�w jakie aktualnie s� na li�cie pawns
		for(Pawn p  : pawns) {
			
			p.draw(g);
			
				
		}
		//metoda rysuj�ca pomocnicze kwadraty wizualizuj�ce obszar klikni�cia na planszy
		/*for(ClickAreaSquare s  : squares) {
			
			s.draw(g);
			
				
		}	*/
		
	}
	/**
	 * Metoda kt�ra na podstawie przesuni�c charakterystycznych dla wybranej przez gracza planszy oraz jej rozmiarze 
	 * generuje  tablic� dwuwymiarow� zawieraj�c� wszystkie wsp�rz�dne przeci�c siatki gry (generuje wszystkie mozliwe srodki dla pionk�w w grze)
	 * @param size  rozmiar planszy wybrany w grze
	 * @param widthStep  charakterystyczne przesuni�cie dla planszy o rozmiarze size
	 * @param heightStep charakterystyczne przesuni�cie dla planszy o rozmiarze size
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