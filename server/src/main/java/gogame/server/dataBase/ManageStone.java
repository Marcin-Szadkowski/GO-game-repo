package gogame.server.dataBase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import gogame.server.game.Stone;
/**
 * Klasa obslugujaca polaczenie klasy Stone
 * z odpowiednia tabela w bazie danych.
 * Zapisane kamienie w bazie danych odwzorowuja kolejne ruchy.
 * @author marcin
 *
 */
public class ManageStone extends DBManager {
	/**
	 * Metoda zapisujaca ruch w odpowiedniej tabeli w bazie danych
	 * @param x
	 * @param y
	 * @param color
	 * @param gameID
	 */
	public static final synchronized Integer addStone(int x, int y, String color, Integer gameID) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer stoneID = null;
		
		try {
			tx = session.beginTransaction();
			Stone stone = new Stone(x, y, color);
			stone.setGameId(gameID);
			stoneID = (Integer) session.save(stone);
			tx.commit();
		}catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return stoneID;
	}
}
