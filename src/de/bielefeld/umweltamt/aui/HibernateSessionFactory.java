package de.bielefeld.umweltamt.aui;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;

/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern.
 * @see <a href="http://hibernate.org/42.html">hibernate.org/42.html</a>
 */
public class HibernateSessionFactory {

    /** 
     * Location of hibernate.cfg.xml file.
     * NOTICE: Location should be on the classpath as Hibernate uses
     * #resourceAsStream style lookup for its configuration file. That
     * is place the config file in a Java package - the default location
     * is the default Java package.<br><br>
     * Examples: <br>
     * <code>CONFIG_FILE_LOCATION = "/hibernate.conf.xml". 
     * CONFIG_FILE_LOCATION = "/com/foo/bar/myhiberstuff.conf.xml".</code> 
     */
    private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";

    /** Holds a single instance of Session */
    private static final ThreadLocal threadLocal = new ThreadLocal();

    /** The single instance of hibernate configuration */
    private static /*final*/ Configuration cfg = new Configuration();

    /** The single instance of hibernate SessionFactory */
    private static org.hibernate.SessionFactory sessionFactory;
    
    /** The Database-User */
    private static String DB_USER = "";
    /** The Database-Password */
    private static String DB_PASS = "";
    /** Der Datenbank-Name */
    private static String DB_URL = "";

    /**
     * Returns the ThreadLocal Session instance.  Lazy initialize
     * the <code>SessionFactory</code> if needed.
     * NIEMALS die Session �ber sess.close() selbst wieder schlie�en,
     * immer HibernateSessionFactory.closeSession() benutzen.
     * Diese Session-Factory verwaltet nur EINE gleichzeitig offene
     * Session, also aufpassen was man wann wo �ffnet und schlie�t.
     *
     *  @return Session
     *  @throws HibernateException
     */
    public static Session currentSession() throws HibernateException {
        Session session = (Session) threadLocal.get();

        if (session == null) {
            if (sessionFactory == null) {
                try {
                	cfg.setProperty("hibernate.connection.username", DB_USER);
                	cfg.setProperty("hibernate.connection.password", DB_PASS);
//                	cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://umweltamt.bielefeld.int/auik");
//                	cfg.setProperty("hibernate.connection.url", DB_URL);
                    cfg.configure(CONFIG_FILE_LOCATION);
                    sessionFactory = cfg.buildSessionFactory();
                }
                catch (Exception e) {
                    System.err.println("%%%% Error Creating SessionFactory %%%%");
                    e.printStackTrace();
                }
            }
            session = sessionFactory.openSession();
            threadLocal.set(session);
            AUIKataster.debugOutput("Neue Session begonnen!", "HibernateSessionFactory");
        }

        return session;
    }

    /**
     *  Close the single hibernate session instance.
     *
     */
    public static void closeSession() {//throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            try {
				session.close();
				AUIKataster.debugOutput("Session geschlossen!", "HibernateSessionFactory");
			} catch (HibernateException e) {
				AUIKataster.handleDBException(e, "HibernateSessionFactory.closeSession", false);
			}
        }
    }
    
    /**
     * Legt fest, welche Datenbank benutzt wird.
     * @param name Der Name der Datenbank
     */
    public static void setDBUrl(String url) {
    	DB_URL = url;
    }
    
    /**
     * Stellt fest, welche Datenbank benutzt wird.
     * @return Der Name der Datenbank
     */
    public static String getDBUrl() {
    	return DB_URL;
    }

    /**
     * Setzt die Benutzerdaten f�r die Datenbank.
     * @param user Der Datenbank-Benutzer
     * @param pass Das Passwort des Datenbank-Benutzers
     */
    public static void setDBData(String user, String pass) {
    	DB_USER = user;
    	DB_PASS = pass;
    	closeSession();
    	sessionFactory = null;
    	cfg = new Configuration();
    	//AUIKataster.debugOutput("User: " + DB_USER + ", Pass: " + DB_PASS, "HSF.setDBData");
    }
    
    /**
     * �berpr�ft die Benutzerdaten f�r die Datenbank. 
     * Wenn sie richtig sind, werden die Daten auch automatisch 
     * f�r weitere Sessions gespeichert.
     * @param user Der Datenbank-Benutzer
     * @param pass Das Passwort des Datenbank-Benutzers
     * @return <code>true</code>, wenn die Benutzerdaten korrekt sind, sonst <code>false</code>
     */
    public static boolean checkCredentials(String user, String pass) throws HibernateException {
    	setDBData(user, pass);
    	//AUIKataster.debugOutput("User: " + DB_USER + ", Pass: " + DB_PASS, "HSF.checkCredentials");
    	
    	boolean tmp = false;
    	
    	try {
			Session session = currentSession();
			// TODO: Vielleicht etwas allgemeineren Test finden?
			List test = session.createSQLQuery(
					"select strasse from basis_strassen where id=0"
			).list();
			
			tmp = true;
			AUIKataster.debugOutput(test.toString());
		} catch (HibernateException e) {
			if (e.getClass().equals(org.hibernate.exception.JDBCConnectionException.class)) {
				tmp = false;
				setDBData("", "");
			} else {
				throw e;
			}
		} finally {
			closeSession();
		}
    	
    	return tmp;
    }
    
    /**
     * Default constructor.
     */
    private HibernateSessionFactory() {
    }

}
