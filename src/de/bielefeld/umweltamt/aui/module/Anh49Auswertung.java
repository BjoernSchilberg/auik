/*
 * Datei:
 * $Id: Anh49Auswertung.java,v 1.1 2008-06-05 11:38:33 u633d Exp $
 * 
 * Erstellt am 15.08.2005 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2005/09/14 11:25:38  u633d
 * - Version vom 14.9.
 *
 * Revision 1.3  2005/09/07 05:56:14  u633d
 * - Anh 49 erg�nzt und neue Mappings
 *
 * Revision 1.2  2005/08/31 06:25:12  u633d
 * - kleine Erg�nzungen
 *
 * Revision 1.1  2005/08/24 08:42:51  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.Anh49Model;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul f�r Anhang 49-Datens�tze.
 * @author David Klotz
 */
public class Anh49Auswertung extends AbstractQueryModul {
	/** Das obere Panel mit den Abfrage-Optionen */
	private JPanel queryPanel;
	
	// Widgets f�r die Abfrage
	private JTextField sachbFeld;
	private IntegerField dekraTuevFeld;
	private JCheckBox abgemeldetCheck;
	private JCheckBox abgerissenCheck;
	private JCheckBox abwasserfreiCheck;
	private JCheckBox wiedervorlageCheck;
	private JButton submitButton;
	
	/** Das TableModel f�r die Ergebnis-Tabelle */
	private Anh49Model tmodel;

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Auswertung Anhang 49";
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
	 */
	public JPanel getQueryOptionsPanel() {
		if (queryPanel == null) {
			// Die Widgets initialisieren:
			sachbFeld = new JTextField("", 12);
			dekraTuevFeld = new IntegerField();
			
			abgemeldetCheck = new JCheckBox("Abgemeldet");
			
			abgerissenCheck = new JCheckBox("Abgerissen");
			
			abwasserfreiCheck = new JCheckBox("Abwasserfrei");
			
			wiedervorlageCheck = new JCheckBox("Nur abgelaufene Wiedervorlage", true);
			
			submitButton = new JButton("Suchen");
			
			// Ein ActionListener f�r den Button, 
			// der die eigentliche Suche ausl�st: 
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean abgem;
					if (abgemeldetCheck.isSelected()) {
						abgem = true;
					} else {
						abgem = false;
					}
					
					String abgerissen;
					if (abgerissenCheck.isSelected()) {
						abgerissen = "%abgerissen";
					} else {
						abgerissen = "";
					}
					
					boolean abwfrei;
					if (abwasserfreiCheck.isSelected()) {
						abwfrei = true;
					} else {
						abwfrei = false;
					}
					
					((Anh49Model)getTableModel()).setList(
							Anh49Fachdaten.findAuswertung(
									sachbFeld.getText(), 
									abgem, 
									abgerissen, 
									abwfrei, 
									dekraTuevFeld.getIntValue(), 
									wiedervorlageCheck.isSelected()));
					((Anh49Model)getTableModel()).fireTableDataChanged();
					frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
				}
			});
			
			// Noch etwas Layout...
			FormLayout layout = new FormLayout(
					"pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref"
					);
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			
			builder.append(abgemeldetCheck, abwasserfreiCheck);
			builder.append(wiedervorlageCheck, abgerissenCheck);
			builder.nextLine();
			builder.append("SachbearbeiterIn:", sachbFeld);
			builder.append("Dekra-T�V-T.:", dekraTuevFeld, submitButton);
			
			queryPanel = builder.getPanel();
		}
		
		return queryPanel;
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel()
	 */
	public ListTableModel getTableModel() {
		if (tmodel == null) {
			tmodel = new Anh49Model();
		}
		return tmodel;
	}
}
