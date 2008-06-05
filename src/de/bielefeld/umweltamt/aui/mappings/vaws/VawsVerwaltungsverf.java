/*
 * Created Tue Sep 06 14:48:29 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_VERWALTUNGSVERF' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsVerwaltungsverf
    extends AbstractVawsVerwaltungsverf
    implements Serializable
{

	
    /**
     * Simple constructor of VawsVerwaltungsverf instances.
     */
    public VawsVerwaltungsverf()
    {
    }

    /* Add customized code below */

	/**
	 * Liefert alle Verfahrens-Eintr�ge deren Wiedervorlage in der 
	 * Vergangenheit liegt und die nicht abgeschlossen sind.
	 * @return Eine Liste mit VawsVerwaltungsverf-Objekten.
	 */
	public static List getAuswertung() {
		List kontrollen;
		String query = "from VawsVerwaltungsverf vf where " +
				"vf.wiedervorlage < ? " +
				"and " +
				"(vf.wvverwverf = ? or vf.wvverwverf is NULL) " +
				"order by vf.wiedervorlage";
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			kontrollen = session.createQuery(
					query)
					.setDate(0, new Date())
					.setString(1, "f")
					.list();

		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		} finally {
			//HibernateSessionFactory.closeSession();
		}
		
		return kontrollen;
	}
	
    /**
     * Liefert alle Verwaltungsverfahren-Eintr�ge zu einem bestimmten 
     * VawsFachdatensatz.
     * @param fachdaten Der Fachdatensatz.
     * @return Eine Liste mit VawsVerwaltungsverf-Objekten.
     */
	public static List getVerwaltungsverf(VawsFachdaten fachdaten) {
		List verfahren;
		
		if (fachdaten.getBehaelterId() == null)
		{
			verfahren = new ArrayList();
		} else {
			try {
				Session session = HibernateSessionFactory.currentSession();
				
				verfahren = session.createQuery(
						"from VawsVerwaltungsverf vvf where " +
						"vvf.vawsFachdaten = ? " +
						"order by vvf.wvverwverf desc, vvf.datum, vvf.wiedervorlage")
						.setEntity(0, fachdaten)
						.list();

				AUIKataster.debugOutput(verfahren.size() + " Verfahrens-Eintr�ge f�r FD " + fachdaten + " gefunden!", "VawsVerwaltungsverf");
			} catch (HibernateException e) {
				throw new RuntimeException("Datenbank-Fehler", e);
			} finally {
				//HibernateSessionFactory.closeSession();
			}
		}
		
    	return verfahren;
    }
	
	/**
	 * Speichert einen VAWS-Verwaltungsverfahren-Eintrag in der Datenbank.
	 * @param verfahren Der zu speichernde Datensatz.
	 * @return <code>true</code>, falls beim Speichern kein Fehler auftritt, sonst <code>false</code>.
	 */
    public static boolean saveVerfahren(VawsVerwaltungsverf verfahren) {
    	boolean saved;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(verfahren);
			tx.commit();
			saved = true;
		} catch (HibernateException e) {
			saved = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "VawsVerwaltungsverf.save", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return saved;
    }
    
    /**
     * L�scht einen vorhandenen Datensatz aus der Datenbank.
     * @param verfahren Der Datensatz, der gel�scht werden soll.
     * @return <code>true</code>, wenn der Datensatz gel�scht wurde oder 
     * <code>false</code> falls dabei ein Fehler auftrat (z.B. der Datensatz 
     * nicht in der Datenbank existiert).
     */
    public static boolean removeVerfahren(VawsVerwaltungsverf verfahren) {
    	boolean removed;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.delete(verfahren);
			tx.commit();
			removed = true;
		} catch (HibernateException e) {
			removed = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "VawsVerwaltungsverf.remove", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return removed;
    }
}
