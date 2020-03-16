package gogame.server.dataBase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import gogame.server.game.Game;
/**
 * Klasa obslugujaca polaczenie klasy Game z 
 * odpowiednia tabela w bazie danych
 * @author marcin
 *
 */
public class ManageGame extends DBManager {
	
	/**
	 * Metoda dodajaca grę do tabeli games
	 * @param game
	 * @return ID zapisanej gry
	 */
	public static final synchronized Integer addGame(Game game) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer gameID= null;
		
		try {
			tx = session.beginTransaction();
			gameID = (Integer) session.save(game);
			tx.commit();
		}catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return gameID;
	}
	/**
	 * Metoda uaktualniajaca informacje o grze
	 * @param game
	 * @param gameID
	 */
	public static final synchronized void updateGame(Game game, Integer gameID) {
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Game savedGame = (Game)session.get(Game.class, gameID);
			savedGame.blackPrisoners = game.blackPrisoners;
			savedGame.whitePrisoners = game.whitePrisoners;
			session.update(savedGame);
			tx.commit();
		}catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}	
}
