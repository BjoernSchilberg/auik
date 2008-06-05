/*
 * Datei:
 * $Id: SuevModel.java,v 1.1 2008-06-05 11:38:40 u633d Exp $
 * 
 * Erstellt am 24.08.2005 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/08/25 14:47:00  u633d
 * - zu viel ;)
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhSuevFachdaten;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel f�r SUEV-Fachdaten.
 * @author David Klotz
 */
public class SuevModel extends ListTableModel {
	/**
	 * Erzeugt ein einfaches TableModel f�r 
	 * SUEV-Fachdaten.
	 */
	public SuevModel() {
		super(new String[]{
				"Betreiber", 
				"Standort",
				"vers. Fl�che"
		}, 
		false);
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		AnhSuevFachdaten fd = (AnhSuevFachdaten) objectAtRow;
		Object tmp;
		
		switch (columnIndex) {
		case 0:
			if (fd.getBasisObjekt() != null) {
				tmp = fd.getBasisObjekt().getBasisBetreiber();
			} else {
				tmp = "<html><font color=red>KEIN BASISOBJEKT!</font></html>";
			}
			break;
		case 1:
			if (fd.getBasisObjekt() != null) {
				tmp = fd.getBasisObjekt().getBasisStandort();
			} else {
				tmp = "<html><font color=red>KEIN BASISOBJEKT!</font></html>";
			}
			break;
		case 2:
			tmp = fd.getVersFlaeche();
			break;

		default:
			tmp = "ERROR";
			break;
		}
		
		return tmp;
	}
	
	/* 
	 * Leer, da kein Updaten der Liste n�tig/m�glich.
	 */
	public void updateList() {
	}
}
