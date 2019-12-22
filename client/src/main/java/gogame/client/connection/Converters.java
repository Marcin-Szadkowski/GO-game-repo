package gogame.client.connection;
import gogame.client.board.BoardGui;
import gogame.client.board.gui.CustomConstants;

/**
 * Klasa zawieraj¹ca metody do danych pomiêdzy klientem a serverem
 * @author User
 *
 */
public class Converters {

    CustomConstants constant = new CustomConstants();
    BoardGui board9 = new BoardGui(9);
    BoardGui board13 = new BoardGui(13);
    BoardGui board19 = new BoardGui(19);
   
    /**
     * Metoda bazuje na rozmiarze planszy i zamienia wspó³rzêdne zadane w skali planszy (piksele)
     * na wspó³rzêdne wysy³ane serverowi. Konwersja polega na zrzutowaniu pikseli na liczby z zakresu <0,size> 
     * @param x
     * @param y
     * @param size rozmiar w planszy zadany w grze
     * @return wynik konwersji w postaci teblicy 2-elementowej zawieraj¹cy odpowiednio o x i y
     */
	public int[] coordinatesScaleConverterFromBoard(int x, int y, int size) {
		int[] output = new int [2];
		int orientX=0,orientY = 0;
		if(size == 9) {orientX=constant.marginLeft ; orientY=board9.stepHeight();}
		else if(size == 13) {orientX= constant.marginLeft; orientY=board13.stepHeight();}
		else if(size == 19) {orientX= constant.marginLeft; orientY=board19.stepHeight();}
		
		output[0]=y/orientY-1;
		output[1]=(x-orientX)/orientY;
		
		return output;	
	}
	
	
	/**
	 * Metoda bazuje na rozmiarze planszy i z otrzymanych wspó³rednych z zakresu <0,size>
	 * zamienia je na wspó³rzedne pikselowe planszy 
	 * @param x
	 * @param y
	 * @param  size rozmiar w planszy zadany w grze
	 * @return wynik konwersji w postaci teblicy 2-elementowej zawieraj¹cy odpowiednio o x i y
	 */
	public int[] coordinatesScaleConverterFromServer(int x, int y, int size) {
		
		int[] output = new int [2];
		int orientX=0,orientY = 0;
		if(size == 9) {orientX=constant.marginLeft ; orientY=board9.stepHeight();}
		else if(size == 13) {orientX= constant.marginLeft; orientY=board13.stepHeight();}
		else if(size == 19) {orientX= constant.marginLeft; orientY=board19.stepHeight();}
		
		output[0]=orientX+(y)*orientY;
		output[1]=(x+1)*orientY;
		
		return output;	
	}
	/**
	 * Metoda konwertuj¹ca wspó³rzêdne ze wspó³rzêdnych  (x,y) przeciêc planszy
	 * na wspó³rzêdne (x1,y1),które s¹ wspó³rzednymi lewego górnego rogu kwadratu o œrodku ciê¿koœci w (x,y)
	 * @param x
	 * @param y
	 * @return wynik konwersji w postaci teblicy 2-elementowej zawieraj¹cy odpowiednio o x1 i y1
	 */
	public int[] coordinatesFromBoardToPawnCovnerter(int x, int y) {
		int[] output = new int [2];
		output[0] = x+constant.shiftWidth-constant.squareSideLength/2;
		output[1]=y+constant.shiftHeight-constant.squareSideLength/2;
		return output;
	}
	
	/**
	 * Metoda konwertuj¹ca wspó³rzêdne ze wspó³rzêdnych  (x,y) - lewy górny róg kwadratu 
	 * na wspó³rzêdne (x1,y1),które s¹ wspó³rzednymi œrodka kwadratu
	 * @param x
	 * @param y
	 * @return wynik konwersji w postaci teblicy 2-elementowej zawieraj¹cy odpowiednio o x1 i y1
	 */
	public int[] coordinatesFromPawnToBoardCovnerter(int x, int y) {
		int[] output = new int [2];
		output[0] = x-constant.shiftHeight+-constant.squareSideLength/2;
		output[1]=y-constant.shiftHeight+constant.squareSideLength/2;
		return output;
	}
	
	/**
	 * Metoda zamieniaj¹ca stringa na tablice stringów oddzielonych spacj¹ .
	 * @param string
	 * @return
	 */
	public String[] splitStringIntoTable(String string) {

		String[] tab = string.split(" ");
		return tab;
		
		}
		
	
	
	}
	
	
	

