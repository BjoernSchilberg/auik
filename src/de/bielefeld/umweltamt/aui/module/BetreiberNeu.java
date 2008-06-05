/*
 * Datei:
 * $Id: BetreiberNeu.java,v 1.1 2008-06-05 11:38:33 u633d Exp $
 * 
 * Erstellt am 12.01.2005 von David Klotz (u633z)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2005/06/09 15:27:03  u633z
 * - (CVS-)Header hinzugef�gt
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.HibernateException;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWirtschaftszweige;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.LongNameComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;

/**
 * Ein Modul zum neuen Anlegen eines Betreibers.
 * @author David Klotz
 */
public class BetreiberNeu extends AbstractModul {
	private JButton speichernButton;
	
	private JLabel handzeichenLabel;
	private JLabel namenLabel;
	
	private JTextField anredeFeld;
	private JTextField namenFeld;
	private JTextField nameZusFeld;
	//private JTextField strassenFeld;
	private JFormattedTextField hausnrFeld;
	private JTextField hausnrZusFeld;
	//private JFormattedTextField plzZsFeld;
	private JTextField plzZsFeld;
	private JTextField plzFeld;
	private JTextField ortsFeld;
	private JTextField telefonFeld;
	private JTextField telefaxFeld;
	private JTextField emailFeld;
	private JTextField betrBeaufVornameFeld;
	private JTextField betrBeaufNachnameFeld;
	private JTextField revdatumsFeld;
	private JTextField handzeichenNeuFeld;
	
	private JTextArea bemerkungsArea;
	
	private JComboBox strassenBox;
	private JComboBox wirtschaftszweigBox;
	
	private String[] strassen = null;
	private VawsWirtschaftszweige[] wirtschaftszweige = null;
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Neuer Betreiber";
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 */
	public String getIdentifier() {
		return "m_betreiber_neu";
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	public String getCategory() {
		return "Betreiber";
	}
	
	/**
	 * @see de.bielefeld.umweltamt.aui.Modul#getIcon()
	 */
	public Icon getIcon() {
		return super.getIcon("filenew32.png");
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
	 */
	public JPanel getPanel() {
		if (panel == null) {
			speichernButton = new JButton("Speichern");
			
			anredeFeld = new LimitedTextField(100);
			namenFeld = new LimitedTextField(100);
			nameZusFeld = new LimitedTextField(50);
			hausnrFeld = new IntegerField();
			hausnrZusFeld = new LimitedTextField(10);
			plzZsFeld = new LimitedTextField(3, "");
			plzFeld = new JTextField();
			ortsFeld = new LimitedTextField(50);
			telefonFeld = new LimitedTextField(50);
			telefaxFeld = new LimitedTextField(50);
			emailFeld = new LimitedTextField(50);
			betrBeaufVornameFeld = new LimitedTextField(50);
			betrBeaufNachnameFeld = new LimitedTextField(50);
			
			revdatumsFeld = new JTextField();
			revdatumsFeld.setEditable(false);
			revdatumsFeld.setFocusable(false);
			revdatumsFeld.setToolTipText("Wird automatisch gesetzt.");
			
			handzeichenNeuFeld = new LimitedTextField(10, "");
			handzeichenNeuFeld.setToolTipText("Handzeichen obligatorisch!");
			
			bemerkungsArea = new LimitedTextArea(2000);
			bemerkungsArea.setLineWrap(true);
			bemerkungsArea.setWrapStyleWord(true);
			JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			strassenBox = new JComboBox();
			strassenBox.setEditable(true);
			
			wirtschaftszweigBox = new JComboBox();
			wirtschaftszweigBox.setRenderer(new LongNameComboBoxRenderer());
			
			JPanel buttonBar = ButtonBarFactory.buildOKBar(speichernButton);
			
			// Der folgende KeyListener wird benutzt um bei Enter 
			// im Handzeichen-Feld (wenn das Feld nicht leer ist) 
			// zum Speichern-Button zu springen.
			handzeichenNeuFeld.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getSource().equals(handzeichenNeuFeld)) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							if (handzeichenNeuFeld.getText().equals("")) {
								handzeichenLabel.setForeground(Color.RED);
								handzeichenNeuFeld.requestFocus();
							} else {
								speichernButton.requestFocus();
							}
						}
					}
				}
			});
			
			// Erm�gliche TAB aus dem Bemerkungs-Feld zu springen
			bemerkungsScroller.getVerticalScrollBar().setFocusable(false);
			bemerkungsScroller.getHorizontalScrollBar().setFocusable(false);
			TabAction tac = new TabAction(bemerkungsArea, handzeichenNeuFeld);
		    
			FormLayout layout = new FormLayout(
					"right:pref, 3dlu, 20dlu, 40dlu, 5dlu, right:pref, 3dlu, 27dlu, 3dlu, 30dlu, 5dlu:grow(0.5)",	// Spalten
					"pref, 3dlu, " +	//1 - Stammdaten
					"pref, 3dlu, " +	//3
					"pref, 3dlu, " +	//5
					"pref, 3dlu, " +	//7
					"pref, 3dlu, " +	//9
					
					"pref, 3dlu, " +	//11 - Adresse
					"pref, 3dlu, " +	//13
					"pref, 3dlu, " +	//15
					"pref, 3dlu, " +	//17
					"pref, 3dlu, " +	//19
					"pref, 3dlu, " +	//21
					
					"pref, 3dlu, " +	//23 - Betriebsbeauftrager
					"pref, 3dlu, " +	//25
					
					"pref, 3dlu, " +	//27 - Bemerkungen 
					"pref, 3dlu, " +	//29 
					"pref, 3dlu, " +	//31
					
					"pref, 3dlu, " +	//33 - Revision
					"pref, 3dlu, " +	//35
					"pref, 10dlu, " +	//37
					
					"top:pref:grow");//39 - Buttons
			layout.setRowGroups(new int[][]{{1,3,5,7,9, 11,13,15,17,19,21, 23,25, 27,29,30,31, 33,35,37}});
			
			PanelBuilder builder = new PanelBuilder(layout);
			builder.setDefaultDialogBorder();
			CellConstraints cc = new CellConstraints();
			
			// Stamdaten ------------------------------------
			builder.addSeparator("Stammdaten", cc.xyw(1,1,10));
			// Anrede
			builder.addLabel("Anrede:",	cc.xy( 1, 3));
			builder.add(anredeFeld,		cc.xyw(3, 3,4));
			// Name
			namenLabel = builder.addLabel("Name:",	cc.xy( 1, 5));
			builder.add(namenFeld,					cc.xyw(3, 5,4));
			// Zusatz
			builder.addLabel("Zusatz:", 	cc.xy( 1, 7));
			builder.add(nameZusFeld, 		cc.xyw(3, 7,4));
			// Wirtschaftszweig
			builder.addLabel("Wirtschaftszweig:",	cc.xy( 1,9));
			builder.add(wirtschaftszweigBox, 		cc.xyw(3,9,4));
			
			// Adresse --------------------------------------
			builder.addSeparator("Adresse", cc.xyw(1,11,10));
			// Stra�e
			builder.addLabel("Stra�e:",	cc.xy( 1,13));
			builder.add(strassenBox, 	cc.xyw(3,13,4));
			builder.add(hausnrFeld, 	cc.xy( 8,13));
			builder.add(hausnrZusFeld, 	cc.xy(10,13));
			// Ort
			builder.addLabel("Ort:", 	cc.xy( 1,15));
			builder.add(plzZsFeld, 		cc.xy( 3,15));
			builder.add(plzFeld, 		cc.xy( 4,15));
			builder.add(ortsFeld, 		cc.xyw(6,15,5));
			// Telefon
			builder.addLabel("Telefon:",cc.xy( 1,17));
			builder.add(telefonFeld, 	cc.xyw(3,17,4));
			// Telefax
			builder.addLabel("Telefax:",cc.xy( 1,19));
			builder.add(telefaxFeld, 	cc.xyw(3,19,4));
			// eMail
			builder.addLabel("E-Mail:", cc.xy(1,21));
			builder.add(emailFeld, 		cc.xyw(3,21,4));
			
			// Betriebsbeauftragter -------------------------
			builder.addSeparator("Betriebsbeauftragter", cc.xyw( 1,23,10));
			// Vorname
			builder.addLabel("Vorname:",cc.xy(1,25));
			builder.add(betrBeaufVornameFeld, 		cc.xyw(3,25,2));
			// Nachname
			builder.addLabel("Nachname:",cc.xy(6,25));
			builder.add(betrBeaufNachnameFeld, 		cc.xyw(8,25,3));
			
			// Bemerkungen ----------------------------------
			builder.addSeparator("Bemerkungen", cc.xyw( 1,27,10));
			builder.add(bemerkungsScroller,		cc.xywh(1,29,10,3));
			
			// Revision -------------------------------------
			builder.addSeparator("Revision", cc.xyw( 1,33,10));
			// Datum
			builder.addLabel("Datum:", 	cc.xy(  1,35));
			builder.add(revdatumsFeld, 	cc.xyw( 3,35,4));
			// Handzeichen
			handzeichenLabel = builder.addLabel("Handzeichen:", cc.xy( 1,37));
			builder.add(handzeichenNeuFeld, 					cc.xyw(3,37,4));
			
			// Buttons
			builder.add(buttonBar, 				cc.xyw( 1, 39, 10));
			
			BetreiberNeuListener dialogListener = new BetreiberNeuListener();
			
			speichernButton.addActionListener(dialogListener);
			strassenBox.addActionListener(dialogListener);
			
			panel = builder.getPanel();
		}
		return panel;
	}
	
	public void show() {
		super.show();
		clearForm();
	}
	
	/**
	 * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
	 * Speichern die Werte des Formulars in einen neuen Standort.
	 * @throws HibernateException Wenn beim Speichern ein Fehler auftritt
	 */
	private void doSave() {
		// Eingaben �berpr�fen:
		// Der Name darf nicht leer sein
		if (namenFeld.getText().equals("")) {
			namenLabel.setForeground(HauptFrame.ERROR_COLOR);
			namenFeld.requestFocus();
			String nameErr = "Der Name darf nicht leer sein!";
			frame.changeStatus(nameErr, HauptFrame.ERROR_COLOR);
			AUIKataster.debugOutput(nameErr, "BetreiberNeu.doSave");
			// Das Handzeichen darf nicht leer sein
		} else if (handzeichenNeuFeld.getText().equals("")) {
			handzeichenLabel.setForeground(HauptFrame.ERROR_COLOR);
			handzeichenNeuFeld.requestFocus();
			String handzErr = "Neues Handzeichen erforderlich!";
			frame.changeStatus(handzErr, HauptFrame.ERROR_COLOR);
			AUIKataster.debugOutput(handzErr, "BetreiberNeu.doSave");
		} else {
			// Wenn die Eingaben korrekt sind
			
			setAllEnabled(false);
			
			// Neues Standortobjekt erzeugen
			BasisBetreiber betrn = new BasisBetreiber();
			
			
			// Anrede
			String anrede = anredeFeld.getText();
			if (anrede.equals("")) {
				betrn.setBetranrede(null);
			} else {
				betrn.setBetranrede(anrede);
			}
			// Name
			String name = namenFeld.getText();
			if (name.equals("")) {
				betrn.setBetrname(null);
			} else {
				betrn.setBetrname(name);
			}
			// Zusatz
			String nameZusatz = nameZusFeld.getText();
			if (nameZusatz.equals("")) {
				betrn.setBetrnamezus(null);
			} else {
				betrn.setBetrnamezus(nameZusatz);
			}
			// Stra�e
			String stra = (String) strassenBox.getSelectedItem();
			if (stra != null) {
				stra = stra.trim();
				
				// Weil ich bis jetzt noch keine LimitedComboBox oder so habe...
				if (stra.length() > 50) {
					// ... k�rze ich hier den String auf 50 Zeichen
					stra = stra.substring(0, 50);
				}
				
				if (stra.equals("")) {
					betrn.setStrasse(null);
				} else {
					betrn.setStrasse(stra);
				}
			}
			// Hausnummer:
			Integer hausnr;
			try {
				hausnrFeld.commitEdit();
			} catch (ParseException e1) {
				hausnrFeld.setValue(new Integer(0));
			}
			if (hausnrFeld.getValue() instanceof Long) {
				hausnr = new Integer(((Long) hausnrFeld.getValue()).intValue());
			} else {
				hausnr = (Integer) hausnrFeld.getValue();
			}
			betrn.setHausnr(hausnr);
			// Hausnummer-Zusatz:
			String hausnrZus = hausnrZusFeld.getText();
			if (hausnrZus.equals("")) {
				betrn.setHausnrzus(null);
			} else {
				betrn.setHausnrzus(hausnrZus);
			}
			// PLZ-Zusatz
			String plzZs = plzZsFeld.getText();
			if (plzZs.equals("")) {
				betrn.setPlzzs(null);
			} else {
				betrn.setPlzzs(plzZs.toUpperCase().trim());
			}
			// PLZ:
			String plz = plzFeld.getText().trim();
			if (plz.equals("")) {
				betrn.setPlz(null);
			} else {
				betrn.setPlz(plz);
			}
			// Ort
			String ort = ortsFeld.getText().trim();
			if (ort.equals("")) {
				betrn.setOrt(null);
			} else {
				betrn.setOrt(ort);
			}
			// Telefon
			String telefon = telefonFeld.getText().trim();
			if (telefon.equals("")) {
				betrn.setTelefon(null);
			} else {
				betrn.setTelefon(telefon);
			}
			// Telefax
			String telefax = telefaxFeld.getText().trim();
			if (telefax.equals("")) {
				betrn.setTelefax(null);
			} else {
				betrn.setTelefax(telefax);
			}
			// eMail
			String email = emailFeld.getText().trim();
			if (email.equals("")) {					
				betrn.setEmail(null);
			} else {
				betrn.setEmail(email);
			}
			// Betriebsbeauftragter-Vorname
			String betrBeaufVorname = betrBeaufVornameFeld.getText().trim();
			if (betrBeaufVorname.equals("")) {
				betrn.setVornamebetrbeauf(null);
			} else {
				betrn.setVornamebetrbeauf(betrBeaufVorname);
			}
			// Betriebsbeauftragter-Nachname
			String betrBeaufNachname = betrBeaufNachnameFeld.getText().trim();
			if (betrBeaufNachname.equals("")) {
				betrn.setNamebetrbeauf(null);
			} else {
				betrn.setNamebetrbeauf(betrBeaufNachname);
			}
			// Wirtschaftszweig
			VawsWirtschaftszweige wizw = (VawsWirtschaftszweige) wirtschaftszweigBox.getSelectedItem(); 
			betrn.setVawsWirtschaftszweige(wizw);
			// Bemerkungen
			String bemerkungen = bemerkungsArea.getText().trim();
			if (bemerkungen.equals("")) {
				betrn.setBemerkungen(null);
			} else {
				betrn.setBemerkungen(bemerkungen);
			}
			
			betrn.setRevidatum(Calendar.getInstance().getTime());
			betrn.setRevihandz(handzeichenNeuFeld.getText().trim());
			
			betrn = BasisBetreiber.saveBetreiber(betrn);
			if (betrn != null) {
				frame.changeStatus("Neuer Betreiber "+betrn.getBetreiberid()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
				
				// Wenn wir vom Objekt anlegen kommen,
				if (manager.getSettingsManager().getBoolSetting("auik.imc.return_to_objekt")) {
					manager.getSettingsManager().setSetting("auik.imc.use_betreiber", betrn.getBetreiberid().intValue(), false);
					manager.getSettingsManager().removeSetting("auik.imc.return_to_objekt");
					// ... kehren wir direkt dorthin zur�ck:
					manager.switchModul("m_objekt_bearbeiten");
				} else {
					// Sonst einfach das Formular zur�cksetzen
					clearForm();
				}
			} else {
				frame.changeStatus("Konnte Betreiber nicht speichern!", Color.RED);
				AUIKataster.debugOutput("Konnte nicht speichern", "BetreiberNeu.doSave");
			}
		}
	}
	
	private void clearForm() {
		setAllEnabled(false);
		//frame.changeStatus("Besch�ftigt...");

		SwingWorkerVariant worker = new SwingWorkerVariant(anredeFeld) {
			
			protected void doNonUILogic() throws RuntimeException {
				try {
					if (strassen == null) {
						strassen = BasisStrassen.getStrassen();
					}
					if (wirtschaftszweige == null) {
						wirtschaftszweige = VawsWirtschaftszweige.getWirtschaftszweige();
					}
				} catch (HibernateException e) {
					throw new RuntimeException(e);
				}
			}

			protected void doUIUpdateLogic() throws RuntimeException {
				if (strassen != null) {
					strassenBox.setModel(new DefaultComboBoxModel(strassen));
					strassenBox.setSelectedIndex(-1);
				}
				if (wirtschaftszweige != null) {
					wirtschaftszweigBox.setModel(new DefaultComboBoxModel(wirtschaftszweige));
				}
				
				hausnrFeld.setValue(null);
				hausnrZusFeld.setText("");
				plzZsFeld.setText("D");
				plzFeld.setText("");
				anredeFeld.setText("");
				namenFeld.setText("");
				namenLabel.setForeground(panel.getForeground());
				nameZusFeld.setText("");
				ortsFeld.setText("");
				telefonFeld.setText("");
				telefaxFeld.setText("");
				emailFeld.setText("");
				betrBeaufNachnameFeld.setText("");
				betrBeaufVornameFeld.setText("");
				bemerkungsArea.setText("");
				
				revdatumsFeld.setText(AuikUtils.getStringFromCurrentDate());
				handzeichenNeuFeld.setText("");
				handzeichenLabel.setForeground(panel.getForeground());
				
				setAllEnabled(true);
				//frame.clearStatus();
				AUIKataster.debugOutput("Formular zur�ckgesetzt", getIdentifier()+".clearForm");
			}
		};
		worker.start();
	}
	
	/**
	 * Aktiviert oder deaktiviert alle Felder im Formular.
	 * @param enabled <code>true</true>, wenn die Felder aktiviert werden sollen, sonst <code>false</code>
	 */
	private void setAllEnabled(boolean enabled) {
		speichernButton.setEnabled(enabled);
		
		strassenBox.setEnabled(enabled);
		//strassenFeld.setEnabled(enabled);
		wirtschaftszweigBox.setEnabled(enabled);
		
		hausnrFeld.setEditable(enabled);
		hausnrZusFeld.setEditable(enabled);
		plzFeld.setEditable(enabled);
		plzZsFeld.setEditable(enabled);
		anredeFeld.setEditable(enabled);
		namenFeld.setEditable(enabled);
		nameZusFeld.setEditable(enabled);
		ortsFeld.setEditable(enabled);
		telefonFeld.setEditable(enabled);
		telefaxFeld.setEditable(enabled);
		emailFeld.setEditable(enabled);
		betrBeaufVornameFeld.setEditable(enabled);
		betrBeaufNachnameFeld.setEditable(enabled);
		
		bemerkungsArea.setEnabled(enabled);
		bemerkungsArea.setEditable(enabled);
		
		handzeichenNeuFeld.setEditable(enabled);
	}
	
	/**
	 * Ein Listener f�r die Events des "Neuer Betreiber"-Moduls.
	 * @author David Klotz
	 */
	private final class BetreiberNeuListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == speichernButton) {
				AUIKataster.debugOutput("Speichern gedr�ckt!", BetreiberNeu.this.getIdentifier());
				doSave();
			} /*else if (e.getSource() == strassenBox) {
				// Wenn wir eine Stra�e ausw�hlen, wird die PLZ upgedatet
				String straStr = (String) strassenBox.getSelectedItem();
				
				if (straStr != null) {
					BasisStrassen stra = BasisStrassen.getStrasseByName(straStr);
					// Nat�rlich nur, wenn die Stra�e eine eindeutige PLZ hat
					if (stra.getPlz() != null) {
						plzFeld.setText(stra.getPlz().toString());
					} else {
						plzFeld.setText("");
					}
				}
			}*/
		}
	}
}
