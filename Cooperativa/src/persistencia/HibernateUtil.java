package persistencia;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	// private static final SessionFactory sessionFactory;
	// static {
	// try {
	// Configuration config = new Configuration()
	// .configure("/persistencia/hibernate.cfg.xml");
	// ServiceRegistry registry = new ServiceRegistryBuilder()
	// .applySettings(config.getProperties())
	// .buildServiceRegistry();
	// sessionFactory = config.buildSessionFactory(registry);
	// } catch (Throwable e) {
	// System.out.println(e.getMessage());
	// throw new ExceptionInInitializerError(e);
	// }
	// }
	//
	// public static SessionFactory getSessionFactory() {
	// return sessionFactory;
	// }

	private static SessionFactory sessionFactory;

	public static synchronized void buildSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			configuration.configure("/persistencia/hibernate.cfg.xml");
			configuration.setProperty(
					"hibernate.current_session_context_class", "thread");
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.buildServiceRegistry();			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}

	public static void openSessionAndBindToThread() {
		Session session = sessionFactory.openSession();
		ThreadLocalSessionContext.bind(session);
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void closeSessionAndUnbindFromThread() {
		Session session = ThreadLocalSessionContext.unbind(sessionFactory);
		if (session != null) {
			session.close();
		}
	}

	public static void closeSessionFactory() {
		if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
			sessionFactory.close();
		}
	}

}
