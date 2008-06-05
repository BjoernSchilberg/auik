/*
 * Datei:
 * $Id: VawsPanel.java,v 1.1 2008-06-05 11:38:39 u633d Exp $
 * 
 * Erstellt am 25.08.2005 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2006/09/19 12:23:07  u633d
 * VawsAbscheider nun moeglich
 *
 * Revision 1.9  2006/09/19 10:31:22  u633d
 * Abscheider bearbeitet
 *
 * Revision 1.8  2006/09/19 08:09:02  u633d
 * VawsAbscheider
 *
 * Revision 1.5.4.2  2006/09/06 08:11:44  u633d
 * VAwS-Anlage hinzugekommen, allerdings noch nicht ganz fertig
 *
 * Revision 1.5.4.1  2006/09/06 06:45:08  u633d
 * Sielhautpunkte funktionieren als PDF
 * VAwS-Listen auch
 * f�r VAwS-Anlagen muss ich nur noch das Java schreiben
 *
 * Revision 1.5  2005/11/02 13:56:03  u633d
 * - Version vom 2.11.
 *
 * Revision 1.4  2005/10/13 13:00:27  u633d
 * Version vom 13.10.
 *
 * Revision 1.3  2005/09/28 11:11:14  u633d
 * - Version vom 28.9.
 *
 * Revision 1.2  2005/09/07 10:48:23  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.1  2005/08/25 14:47:00  u633d
 * - zu viel ;)
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.ReportManager;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsAnlagenarten;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsFachdaten;
import de.bielefeld.umweltamt.aui.module.ObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.editors.VawsEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.VawsModel;

/**
 * Das "Vaws"-Panel des Objekt-Bearbeiten-Moduls.
 * @author David Klotz
 */
public class VawsPanel extends JPanel {
	private String name;
	private ObjektBearbeiten hauptModul;
	
	private VawsModel vawsModel;
	
	private Action vawsEditAction;
	private Action vawsLoeschAction;
	private Action reportAnlageAction;
	private JPopupMenu vawsPopup;
	private JTable vawsTable;
	
	protected Integer objektid;
	protected String betreiber;
	protected String standort;
	protected String art;
	protected Integer behaelterid;
	
	private JComboBox anlagenartBox;
	private JButton anlegenButton;
	private JButton reportListeButton;
	private JButton reportAnlageButton;
	
	/**
	 * Erzeugt das Vaws-Panel f�r das ObjektBearbeiten-Modul.
	 * @param hauptModul Das ObjektBearbeiten-Hauptmodul.
	 */
	public VawsPanel(ObjektBearbeiten hauptModul) {
		name = "VAwS";
		this.hauptModul = hauptModul;
		vawsModel = new VawsModel();
		
		JScrollPane vawsScroller = new JScrollPane(getVawsTable());
		
		anlagenartBox = new JComboBox(VawsAnlagenarten.getAnlagenarten());
		anlegenButton = new JButton("Anlegen");
		anlegenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				neuerDatensatz();
			}
		});
		
		reportListeButton = new JButton("PDF-Liste generieren");
		reportListeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showReportListe();
			}
		});
		
		reportAnlageButton = new JButton("PDF-Datenblatt generieren");
		reportAnlageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showReportAnlage();
			}
		});
		
		FormLayout layout = new FormLayout(
				"pref, 3dlu, pref, 3dlu, pref, 10dlu:g, pref, 3dlu, pref", 
				"f:100dlu:g, 3dlu, pref"
		);
		DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
		builder.setDefaultDialogBorder();
		
		builder.append(vawsScroller,9);
		builder.nextLine(2);
		builder.append("Neue(n)", anlagenartBox, anlegenButton);
		builder.append(reportListeButton, reportAnlageButton);
	}
	
	/**
	 * Liefert den Namen dieses Panels.
	 * @return "VAWS"
	 */
	public String getName() {
		return name;
	}
	
	// Funktionalit�t:
	
	/**
	 * Holt die Liste mit Fachdatens�tzen aus der Datenbank.
	 */
	public void fetchFormData() {
		vawsModel.setList(
				VawsFachdaten.getVawsByObjekt(hauptModul.getObjekt())
		);
	}
	
	/**
	 * Erneuert die Anzeige der Tabelle.
	 */
	public void updateForm() {
		// TODO: Evtl. bei allen Tabellen nur fireTableDataChanged nach Editor?
		// Zumindest so lange kein neues Objekt hinzugekommen ist?
		vawsModel.fireTableDataChanged();
	}
	
	/**
	 * Legt einen neuen Vaws-Datensatz zu diesem Objekt an.
	 */
	public void neuerDatensatz() {
		VawsFachdaten neu = new VawsFachdaten();
		neu.setBasisObjekt(hauptModul.getObjekt());
		neu.setAnlagenart((String)anlagenartBox.getSelectedItem());
		
		editDatensatz(neu);
	}
	
	/**
	 * �ffnet einen Dialog um einen Vaws-Datensatz zu bearbeiten.
	 * @param probe Der Datensatz.
	 */
	public void editDatensatz(VawsFachdaten fd) {
		//AUIKataster.debugOutput("Bearbeite '" + fd + "' !", "VawsPanel.editDatensatz");
		VawsEditor editor = new VawsEditor(fd, hauptModul.getFrame());
		
		editor.setVisible(true);
		
		if (editor.wasSaved()) {
			// Nach dem Bearbeiten die Liste updaten, 
			// damit unsere �nderungen auch angezeigt werden.
			fetchFormData();
			updateForm();
		}
	}
	
	/**
	 * L�scht einen Vaws-Datensatz (mit Nachfrage).
	 * @param fd Der Datensatz.
	 */
	public void loescheDatensatz(VawsFachdaten fd) {
		String art = fd.getAnlagenart();
		if (fd.isLageranlage()) {
			art = "Lageranlage";
		}
		
		if (hauptModul.getFrame().showQuestion(
				"Soll die ausgew�hlte "+art+" wirklich gel�scht werden?", 
				"L�schen best�tigen")) {
			if (VawsFachdaten.removeFachdaten(fd)) {
				hauptModul.getFrame().changeStatus("Fachdatensatz '" + fd + "' erfolgreich gel�scht!", HauptFrame.SUCCESS_COLOR);
				
				// Tabelle updaten:
				fetchFormData();
				updateForm();
			} else {
				hauptModul.getFrame().showErrorMessage("Konnte Fachdatensatz '" + fd + "' nicht l�schen!");
			}
		}
	}
	
	public void showReportListe() {
		betreiber = hauptModul.getObjekt().getBasisBetreiber().toString();
		standort = hauptModul.getObjekt().getBasisStandort().toString();
		art = hauptModul.getObjekt().getBasisObjektarten().getObjektart();
		objektid = hauptModul.getObjekt().getObjektid();
		
		//scriptables.put("liste", vawsModel.getList());
		if (betreiber != null || standort != null || objektid != null || art != null)
		{
			ReportManager.getInstance().startReportWorker("VAwS-Liste", objektid, betreiber, standort, art, reportListeButton);	
		}
//		ReportManager.getInstance().startReportWorker("vawsliste", params, scriptables, reportListeButton);
	}
	
	public void showReportAnlage() {
		int row = getVawsTable().getSelectedRow();
		if (row != -1) {
			VawsFachdaten anlage = vawsModel.getDatenSatz(row);
			betreiber = hauptModul.getObjekt().getBasisBetreiber().toString();
			standort = hauptModul.getObjekt().getBasisStandort().toString();
			behaelterid = anlage.getBehaelterId();
			
			//scriptables.put("liste", vawsModel.getList());
			if (betreiber != null || standort != null || behaelterid != null)
			{
				ReportManager.getInstance().startReportWorker("VAwS-Anlage", behaelterid, betreiber, standort, reportListeButton);	
			}
//		int row = getVawsTable().getSelectedRow();
//		if (row != -1) {
//			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
//			
//			VawsFachdaten anlage = vawsModel.getDatenSatz(row);
//			HashMap params = new HashMap();
//			params.put("anlagenart", anlage.getAnlagenart());
//			params.put("hnr", anlage.getHerstellnr());
//			params.put("vbf", anlage.getVbfeinstufung());
//			
//			params.put("fluessigkeit", anlage.getFluessigkeit());
//			params.put("gefstufe", anlage.getGefaehrdungsstufe());
//			params.put("wgk", anlage.getWgk());
//			
//			params.put("baujahr", anlage.getBaujahr());
//			
//			if (anlage.getDatumInbetriebnahme() != null) {
//				params.put("inbetriebnahme", df.format(anlage.getDatumInbetriebnahme()));
//			} else {
//				params.put("inbetriebnahme", " ");
//			}
//			if (anlage.getDatumGenehmigung() != null) {
//				params.put("genehmigung", df.format(anlage.getDatumGenehmigung()));
//			} else {
//				params.put("genehmigung", " ");
//			}
//			if (anlage.getDatumErfassung() != null) {
//				params.put("erfassung", df.format(anlage.getDatumErfassung()));
//			} else {
//				params.put("erfassung", " ");
//			}
//			if (anlage.getStillegungsdatum() != null) {
//				params.put("stillegung", df.format(anlage.getStillegungsdatum()));
//			} else {
//				params.put("stillegung", " ");
//			}
//			
//			params.put("bemerkungen", ((anlage.getBemerkungen() != null) ? anlage.getBemerkungen() : " "));
//			
//			HashMap scriptables = new HashMap();
//			List liste = VawsKontrollen.getKontrollen(anlage);
////			System.out.println("%!%!% liste.size: " + liste.size());
//			scriptables.put("liste", liste);
//			
////			ReportManager.getInstance().startReportWorker("vawsanlage", params, scriptables, reportAnlageButton);
			else
			{
				hauptModul.getFrame().changeStatus("Keine Anlage ausgew�hlt!", HauptFrame.ERROR_COLOR);
			}
		}
	}
	
	/**
	 * Liefert die Action um einen Datensatz zu bearbeiten.
	 */
	private Action getVawsEditAction() {
		if (vawsEditAction == null) {
			vawsEditAction = new AbstractAction("Bearbeiten") {
				public void actionPerformed(ActionEvent e) {
					int row = getVawsTable().getSelectedRow();
					
					// Nat�rlich nur editieren, wenn wirklich eine Zeile ausgew�hlt ist
					if (row != -1) {
						VawsFachdaten fd = vawsModel.getDatenSatz(row);
						editDatensatz(fd);
					}
				}
			};
			vawsEditAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
			vawsEditAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
		}
		
		return vawsEditAction;
	}
	
	/**
	 * Liefert die Action um einen Datensatz zu l�schen.
	 */
	private Action getVawsLoeschAction() {
		if (vawsLoeschAction == null) {
			vawsLoeschAction = new AbstractAction("L�schen") {
				public void actionPerformed(ActionEvent e) {
					int row = getVawsTable().getSelectedRow();
					
					// Nat�rlich nur, wenn wirklich eine Zeile ausgew�hlt ist
					if (row != -1) {
						VawsFachdaten fd = vawsModel.getDatenSatz(row);
						loescheDatensatz(fd);
					}
				}
			};
			vawsLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
			vawsLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
		}
		
		return vawsLoeschAction;
	}
	
	/**
	 * Liefert die Action um einen Datensatz zu l�schen.
	 */
	private Action getReportAnlageAction() {
		if (reportAnlageAction == null) {
			reportAnlageAction = new AbstractAction("PDF-Datenblatt generieren") {
				public void actionPerformed(ActionEvent e) {
					showReportAnlage();
				}
			};
			reportAnlageAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_D));
//			reportAnlageAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.ALT_DOWN_MASK, false));
		}
		
		return reportAnlageAction;
	}
	
	/**
	 * Zeigt ein Kontextmen� an, wenn ein entsprechendes 
	 * MouseEvent vorliegt.
	 * @param e Das MouseEvent.
	 */
	private void showVawsPopup(MouseEvent e) {
		if (vawsPopup == null) {
			vawsPopup = new JPopupMenu("VAWS");
			vawsPopup.add(new JMenuItem(getVawsEditAction()));
			vawsPopup.add(new JMenuItem(getVawsLoeschAction()));
			vawsPopup.add(new JMenuItem(getReportAnlageAction()));
		}
		
		if (e.isPopupTrigger()) {
			Point origin = e.getPoint();
			int row = getVawsTable().rowAtPoint(origin);
			
			if (row != -1) {
				getVawsTable().setRowSelectionInterval(row, row);
				vawsPopup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
	// Widget-Getter:
	
	private JTable getVawsTable() {
		if (vawsTable == null) {
			vawsTable = new JTable(vawsModel);
			vawsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			vawsTable.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if((e.getClickCount() == 2) && (e.getButton() == 1)) {
						Point origin = e.getPoint();
						int row = vawsTable.rowAtPoint(origin);
						
						VawsFachdaten fd = vawsModel.getDatenSatz(row);
						editDatensatz(fd);
					}
				}
				
				public void mousePressed(MouseEvent e) {
					showVawsPopup(e);
				}
				
				public void mouseReleased(MouseEvent e) {
					showVawsPopup(e);
				}
			});
			
			vawsTable.getInputMap().put((KeyStroke)getVawsEditAction().getValue(Action.ACCELERATOR_KEY), getVawsEditAction().getValue(Action.NAME));
			vawsTable.getActionMap().put(getVawsEditAction().getValue(Action.NAME), getVawsEditAction());
			
			vawsTable.getInputMap().put((KeyStroke)getVawsLoeschAction().getValue(Action.ACCELERATOR_KEY), getVawsLoeschAction().getValue(Action.NAME));
			vawsTable.getActionMap().put(getVawsLoeschAction().getValue(Action.NAME), getVawsLoeschAction());
			
//			vawsTable.getInputMap().put((KeyStroke)getReportAnlageAction().getValue(Action.ACCELERATOR_KEY), getReportAnlageAction().getValue(Action.NAME));
//			vawsTable.getActionMap().put(getReportAnlageAction().getValue(Action.NAME), getReportAnlageAction());
		}
		return vawsTable;
	}
}
