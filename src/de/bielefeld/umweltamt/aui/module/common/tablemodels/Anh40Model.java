/*
 * Datei:
 * $Id: Anh40Model.java,v 1.1 2008-06-05 11:38:40 u633d Exp $
 * 
 * Erstellt am 03.05.2006 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/05/23 05:29:42  u633d
 * Objektchronologie f�r alle Objekte verf�gbar gemacht
 *
 * Revision 1.1  2006/05/03 09:01:54  u633d
 * Anhang 40 und 56 erg�nzt
 *
 * Revision 1.1  2006/05/03 08:42:52  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel f�r Anhang 40-Fachdaten.
 * @author Gerd Genuit
 */
public class Anh40Model extends ListTableModel {
	public Anh40Model() {
		super(new String[]{
				"Betreiber", 
				"Standort", 
				"Bemerkungen"
		}, 
		false);
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		Anh40Fachdaten fd = (Anh40Fachdaten) objectAtRow;
		Object tmp;
		
		switch (columnIndex) {
		case 0:
			tmp = fd.getBasisObjekt().getBasisBetreiber();
			break;
		case 1:
			tmp = fd.getBasisObjekt().getBasisStandort();
			break;
		case 2:
			tmp = fd.getBemerkungen();
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
