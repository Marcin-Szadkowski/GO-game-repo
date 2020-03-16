package gogame.server.dataBase;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class DBManager {
	protected final static SessionFactory factory;
	
	static {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
