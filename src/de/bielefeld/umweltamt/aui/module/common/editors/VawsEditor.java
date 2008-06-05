/*
 * Datei:
 * $Id: VawsEditor.java,v 1.1 2008-06-05 11:38:41 u633d Exp $
 * 
 * Erstellt am 03.09.2005 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/11/02 13:52:39  u633d
 * - Version vom 2.11.
 *
 * Revision 1.4  2005/10/13 13:00:25  u633d
 * Version vom 13.10.
 *
 * Revision 1.3  2005/09/28 11:11:12  u633d
 * - Version vom 28.9.
 *
 * Revision 1.2  2005/09/14 11:25:36  u633d
 * - Version vom 14.9.
 *
 * Revision 1.1  2005/09/07 10:48:22  u633d
 * - Version vom 7.9.05
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.editors;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsAbfuellflaeche;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsAnlagenchrono;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsBehaelterart;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsFachdaten;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsFluessigkeit;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsGebuehrenarten;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsGefaehrdungsstufen;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsKontrollen;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsMaterial;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsPruefer;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsPruefergebnisse;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsVbfeinstufung;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsVerwaltungsgebuehren;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsVerwaltungsverf;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsVerwmassnahmen;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWgk;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.KommaDouble;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein Editor f�r VAWS-Datens�tze.
 * @author David Klotz
 */
public class VawsEditor extends AbstractBaseEditor {
	private VawsAbfuellflaeche abfuellflaeche;
	
	private JPanel topPanel;
	private JTabbedPane tabbedPane;
	
	// F�r das Kontextmen� in den Tabellen
	private Action tabellenItemLoeschAction;
	private JPopupMenu tabellenPopup;
	
	// Widgets f�rs Top-Panel:
	private JLabel header;
//	private JLabel subHeader;
//	private JLabel hnrLabel;
//	private JButton reportButton;
	private LimitedTextField hnrFeld;
	private JComboBox fluessigkeitBox;
	private JComboBox vbfBox;
	private JComboBox gefStufeBox;
	private JComboBox wgkBox;
	
	//  Tabs:
	// Widgets f�r alle Daten-Tabs:
	private IntegerField baujahrFeld;
	private TextFieldDateChooser inbetriebnahmeChooser;
	private TextFieldDateChooser genehmigungChooser;
	private TextFieldDateChooser erfassungChooser;
	private TextFieldDateChooser stillegungChooser;
	private DoubleField pruefTurnusFeld;
	private LimitedTextArea bemerkungArea;
	private JTable anlagenChronoTabelle;
	private VawsAnlagenChronoModel anlagenChronoModel;
	// Ben�tigt bei Lageranlagen & Rohrleitungen
	private JComboBox behaelterArtBox;
	private JComboBox materialBox;
	
	// Daten (Lageranlagen)
	private JPanel datenLageranlagenTab;
	private DoubleField mengeFeld;
	// Schutzvorkehrungen (Lageranlagen)
	private JPanel schutzLageranlagenTab;
	private JCheckBox doppelWandigCheck;
	private JCheckBox leckAnzeigeCheck;
	private JCheckBox auffangRaumCheck;
	private JCheckBox leckSchutzAuskleidungCheck;
	private JCheckBox grenzWertGeberCheck;
	private JCheckBox kellerLagerungCheck;
	private JCheckBox innenBeschichtungCheck;
	private LimitedTextArea beschreibungAFeld;
	private LimitedTextArea beschreibungSFeld;
	// Leitungen (Lageranlagen)
	private JPanel leitungenLageranlagenTab;
	private JCheckBox oberIrdischCheck;
	private JCheckBox unterIrdischCheck;
	private JCheckBox saugLeitungCheck;
	private JCheckBox rohrKathSchCheck;
	private JCheckBox ausKupferCheck;
	private JCheckBox ausStahlCheck;
	private JCheckBox schutzrohrCheck;
	private LimitedTextArea beschreibungRFeld;
	
	// Daten (Rohrleitungen)
	private JPanel datenRohrleitungenTab;
	private JComboBox ausfuehrungBox;
	
	// Daten (Abf�llfl�chen)
	private JPanel datenAbfuellflaechenTab;
	private JCheckBox eohCheck;
	private JCheckBox efCheck;
	private JCheckBox svbCheck;
	private ButtonGroup zustandGroup;
	private JRadioButton saniertCheck;
	private JRadioButton neuErstelltCheck;
	private JRadioButton unbktCheck;
	// Ausf�hrung (Abf�llfl�chen)
	private JPanel ausfuehrungAbfuellflaechenTab;
	private JComboBox bodenflaechenAusfBox;
	private DoubleField dickeFeld;
	private LimitedTextField gueteFeld;
	private LimitedTextField fugenMaterialFeld;
	private JComboBox niederschlagSchutzBox;
	private JCheckBox abscheiderVorhandenCheck;
	private LimitedTextArea beschrBodenflaecheArea;
	private LimitedTextArea beschrFugenmaterialArea;
	private LimitedTextArea beschrAblNiederschlArea;
	
	// Sachverst�ndigenpr�fung
	private JPanel svPruefungTab;
	private JTable svPruefungTabelle;
	private JComboBox prueferBox;
	private JComboBox pruefergebnisBox;
	private VawsKontrollenModel svPruefungModel;
	
	// Verwaltungsverfahren
	private JPanel verwVerfahrenTab;
	private JTable verwVerfahrenTabelle;
	private JComboBox massnahmenBox;
	private VerwVerfahrenModel verwVerfahrenModel;
	
	// Verwaltungsgeb�hren
	private JPanel verwGebuehrenTab;
	private JTable verwGebuehrenTabelle;
	private JComboBox gebArtenBox;
	private VerwGebuehrenModel verwGebuehrenModel;
	
	/**
	 * Erzeugt einen neuen Dialog zum Bearbeiten von VAWS-Fachdaten.
	 */
	public VawsEditor(VawsFachdaten fachdaten, HauptFrame owner) {
		//super("VAwS-Einzelanlage (" + fachdaten + ")", fachdaten, owner);
		super("VAwS-Einzelanlage " + (fachdaten.getBehaelterId() == null ? "(Neu)" : fachdaten.getBehaelterId().toString()), fachdaten, owner);
	}
	
	/**
	 * Erzeugt einen neuen Dialog zum Bearbeiten von VAWS-Fachdaten.
	 * Schaltet zu einem bestimmten Tab um.
	 * @param tab "Sachverst�ndigenpr�fung" oder "Verwaltungsverfahren"
	 */
	public VawsEditor(VawsFachdaten fachdaten, HauptFrame owner, String tab) {
		this(fachdaten, owner);
		if ("Sachverst�ndigenpr�fung".equals(tab)) {
			tabbedPane.setSelectedComponent(getSvPruefungTab());
		} else if ("Verwaltungsverfahren".equals(tab)) {
			tabbedPane.setSelectedComponent(getVerwVerfahrenTab());
		}
	}
	
	/* 
	 * �berschrieben, damit die unterschiedlichen Anlagen-Arten ihre Gr��en
	 * separat speichern, obwohl ja alle von der Klasse VawsFachdaten sind.
	 * 
	 * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#getEditedClassName()
	 */
	protected String getEditedClassName() {
		String className = super.getEditedClassName();
		
		if (getFachdaten().isLageranlage()) {
			className += "-Lageranlage";
		} else {
			className += "-" + getFachdaten().getAnlagenart();
		}
		
		return className;
	}
	
	/**
	 * Liefert das bearbeitete Objekt.
	 * @return Ein VawsFachdaten-Objekt.
	 */
	public VawsFachdaten getFachdaten() {
		return (VawsFachdaten) getEditedObject();
	}
	
	/**
	 * Liefert die Abf�llfl�chen-Fachdaten zum bearbeiteten Objekt.
	 * Achtung: Nur bei Abf�llfl�chen-Objekten aufrufen!
	 * @return Ein VawsAbfuellflaechen-Objekt
	 */
	private VawsAbfuellflaeche getAbfuellflaeche() {
		if (abfuellflaeche == null) {
			abfuellflaeche = VawsAbfuellflaeche.getAbfuellflaeche(getFachdaten());
		}
		
		return abfuellflaeche;
	}
	
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.dialogbase.SimpleDialog#buildContentArea()
	 */
	protected JComponent buildContentArea() {
		// Widgets initialisieren:
		// Top-Panel
		header = new JLabel(" ", JLabel.LEFT);
		header.setFont(new Font("SansSerif", Font.BOLD, 18));
//		subHeader = new JLabel(" ", JLabel.LEFT);
//		subHeader.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		hnrFeld = new LimitedTextField(25);
		
		fluessigkeitBox = new JComboBox(VawsFluessigkeit.getFluessigkeiten());
		fluessigkeitBox.setEditable(true);
		//fluessigkeitBox.setPrototypeDisplayValue("L�sungsmittelr�ckst�nde");
		
		vbfBox = new JComboBox(VawsVbfeinstufung.getVbfeinstufungen());
		vbfBox.setEditable(false);
		//vbfBox.setPrototypeDisplayValue(" A III ");
		
		gefStufeBox = new JComboBox(VawsGefaehrdungsstufen.getVbfeinstufungen());
		gefStufeBox.setEditable(false);
		//gefStufeBox.setPrototypeDisplayValue(" A ");
		
		wgkBox = new JComboBox(VawsWgk.getWgk());
		wgkBox.setEditable(false);
		//wgkBox.setPrototypeDisplayValue(" 3 ");
		
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		// Widgets f�r alle Daten-Tabs:
		baujahrFeld = new IntegerField();
		inbetriebnahmeChooser = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		genehmigungChooser = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		erfassungChooser = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		stillegungChooser = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		pruefTurnusFeld = new DoubleField(0);
		
		behaelterArtBox = new JComboBox(VawsBehaelterart.getBehaelterarten());
		behaelterArtBox.setEditable(false);
		
		materialBox = new JComboBox(VawsMaterial.getMaterialien());
		// TODO: Material-Box editable / Tabelleninhalt?
		materialBox.setEditable(true);
		bemerkungArea = new LimitedTextArea(255);
		
		// Daten f�r die Anlagenchronologie-Tabelle:
		anlagenChronoModel = new VawsAnlagenChronoModel();
		anlagenChronoTabelle = new JTable(anlagenChronoModel);
		anlagenChronoTabelle.getColumnModel().getColumn(0).setPreferredWidth(50);
		anlagenChronoTabelle.getColumnModel().getColumn(1).setPreferredWidth(250);
		anlagenChronoTabelle.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		anlagenChronoTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				showTabellenPopup(e);
			}
			
			public void mouseReleased(MouseEvent e) {
				showTabellenPopup(e);
			}
		});
		
		anlagenChronoTabelle.getInputMap().put((KeyStroke)getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY), getTabellenItemLoeschAction().getValue(Action.NAME));
		anlagenChronoTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME), getTabellenItemLoeschAction());
		
		// Daten (Lageranlagen)
		mengeFeld = new DoubleField(0);
		
		// Daten (Rohrleitungen)
		ausfuehrungBox = new JComboBox(VawsFachdaten.getAusfuehrungen());
		ausfuehrungBox.setEditable(false);
		// TODO: Ausf�hrung aus anderer Tabelle / Vorgaben? Welche noch?
		
		// Daten (Abf�llfl�chen)
		eohCheck = new JCheckBox("Einfach o. Herk�mmlich");
		efCheck = new JCheckBox("Eignungsfeststellung");
		svbCheck = new JCheckBox("Sachverst�ndigenbescheinigung");
		svbCheck.setEnabled(false);
		zustandGroup = new ButtonGroup();
		saniertCheck = new JRadioButton("Saniert");
		neuErstelltCheck = new JRadioButton("Neu Erstellt");
		unbktCheck = new JRadioButton("Unbekannt");
		zustandGroup.add(saniertCheck);
		zustandGroup.add(neuErstelltCheck);
		zustandGroup.add(unbktCheck);
		
		// Schutzvorkehrungen (Lageranlagen)
		doppelWandigCheck = new JCheckBox("Doppelwandig");
		leckAnzeigeCheck = new JCheckBox("Leckanzeige");
		auffangRaumCheck = new JCheckBox("Auffangraum");
		leckSchutzAuskleidungCheck = new JCheckBox("Leckschutzauskleidung");
		grenzWertGeberCheck = new JCheckBox("Grenzwertgeber");
		kellerLagerungCheck = new JCheckBox("Kellerlagerung");
		innenBeschichtungCheck = new JCheckBox("Innenbeschichtung");
		beschreibungAFeld = new LimitedTextArea(255);
		beschreibungSFeld = new LimitedTextArea(255);
		
		// Leitungen (Lageranlagen)
		oberIrdischCheck = new JCheckBox("Oberirdisch");
		unterIrdischCheck = new JCheckBox("Unterirdisch");
		saugLeitungCheck = new JCheckBox("Als Saugleitung");
		rohrKathSchCheck = new JCheckBox("Rohr-Kathodenschutz");
		ausKupferCheck = new JCheckBox("Aus Kupfer");
		ausStahlCheck = new JCheckBox("Aus Stahl");
		schutzrohrCheck = new JCheckBox("Mit Schutzrohr");
		beschreibungRFeld = new LimitedTextArea(255);
		
		// Ausf�hrung (Abf�llfl�chen)
		bodenflaechenAusfBox = new JComboBox(VawsAbfuellflaeche.getBodenflaechenausfArray());
		bodenflaechenAusfBox.setEditable(false);
		dickeFeld = new DoubleField(0);
		gueteFeld = new LimitedTextField(50);
		fugenMaterialFeld = new LimitedTextField(50);
		niederschlagSchutzBox = new JComboBox(VawsAbfuellflaeche.getNiederschlagschutzArray());
		niederschlagSchutzBox.setEditable(false);
		abscheiderVorhandenCheck = new JCheckBox("Abscheider vorhanden?");
		beschrBodenflaecheArea = new LimitedTextArea(255);
		beschrFugenmaterialArea = new LimitedTextArea(255);
		beschrAblNiederschlArea = new LimitedTextArea(255);
		
		// Sachverst�ndigenpr�fung
		svPruefungModel = new VawsKontrollenModel();
		svPruefungTabelle = new JTable(svPruefungModel);
		svPruefungTabelle.getColumnModel().getColumn(0).setPreferredWidth(50);
		svPruefungTabelle.getColumnModel().getColumn(1).setPreferredWidth(50);
		svPruefungTabelle.getColumnModel().getColumn(2).setPreferredWidth(200);
		svPruefungTabelle.getColumnModel().getColumn(3).setPreferredWidth(50);
		svPruefungTabelle.getColumnModel().getColumn(4).setPreferredWidth(50);
		
		// F�r die ComboBox bei "Pr�fer"
		prueferBox = new JComboBox(VawsPruefer.getAllPruefer());
		prueferBox.setEditable(false);
		prueferBox.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				prueferBox.showPopup();
			}
		});
		prueferBox.setBorder(BorderFactory.createEmptyBorder());
		svPruefungTabelle.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(prueferBox));
		
		// F�r die ComboBox bei "Pr�fergebnis"
		pruefergebnisBox = new JComboBox(VawsPruefergebnisse.getAllPruefergebnisse());
		pruefergebnisBox.setEditable(false);
		pruefergebnisBox.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				pruefergebnisBox.showPopup();
			}
		});
		pruefergebnisBox.setBorder(BorderFactory.createEmptyBorder());
		svPruefungTabelle.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(pruefergebnisBox));
		
		
		svPruefungTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				showTabellenPopup(e);
			}
			
			public void mouseReleased(MouseEvent e) {
				showTabellenPopup(e);
			}
		});
		
		svPruefungTabelle.getInputMap().put((KeyStroke)getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY), getTabellenItemLoeschAction().getValue(Action.NAME));
		svPruefungTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME), getTabellenItemLoeschAction());
		

		// Verwaltungsverfahren
		verwVerfahrenModel = new VerwVerfahrenModel();
		verwVerfahrenTabelle = new JTable(verwVerfahrenModel);
		verwVerfahrenTabelle.getColumnModel().getColumn(0).setPreferredWidth(50);
		verwVerfahrenTabelle.getColumnModel().getColumn(1).setPreferredWidth(200);
		verwVerfahrenTabelle.getColumnModel().getColumn(2).setPreferredWidth(50);
		verwVerfahrenTabelle.getColumnModel().getColumn(3).setPreferredWidth(50);
		
		// F�r die ComboBox bei "Ma�nahme"
		massnahmenBox = new JComboBox(VawsVerwmassnahmen.getAllMassnahmen());
		massnahmenBox.setEditable(false);
		massnahmenBox.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				massnahmenBox.showPopup();
			}
		});
		massnahmenBox.setBorder(BorderFactory.createEmptyBorder());
		verwVerfahrenTabelle.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(massnahmenBox));
		
		verwVerfahrenTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				showTabellenPopup(e);
			}
			
			public void mouseReleased(MouseEvent e) {
				showTabellenPopup(e);
			}
		});
		
		verwVerfahrenTabelle.getInputMap().put((KeyStroke)getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY), getTabellenItemLoeschAction().getValue(Action.NAME));
		verwVerfahrenTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME), getTabellenItemLoeschAction());
		
		// Verwaltungsgebuehren
		verwGebuehrenModel = new VerwGebuehrenModel();
		verwGebuehrenTabelle = new JTable(verwGebuehrenModel);
		verwGebuehrenTabelle.getColumnModel().getColumn(0).setPreferredWidth(50);
		verwGebuehrenTabelle.getColumnModel().getColumn(1).setPreferredWidth(200);
		verwGebuehrenTabelle.getColumnModel().getColumn(2).setPreferredWidth(50);
		verwGebuehrenTabelle.getColumnModel().getColumn(3).setPreferredWidth(50);
		verwGebuehrenTabelle.getColumnModel().getColumn(4).setPreferredWidth(50);
		
		// F�r die ComboBox bei "Geb�hrenart"
		gebArtenBox = new JComboBox(VawsGebuehrenarten.getAllGebuehrenarten());
		gebArtenBox.setEditable(false);
		gebArtenBox.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				gebArtenBox.showPopup();
			}
		});
		gebArtenBox.setBorder(BorderFactory.createEmptyBorder());
		verwGebuehrenTabelle.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(gebArtenBox));
		
		verwGebuehrenTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				showTabellenPopup(e);
			}
			
			public void mouseReleased(MouseEvent e) {
				showTabellenPopup(e);
			}
		});
		
		verwGebuehrenTabelle.getInputMap().put((KeyStroke)getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY), getTabellenItemLoeschAction().getValue(Action.NAME));
		verwGebuehrenTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME), getTabellenItemLoeschAction());
		
		// Layout topPanel:
		FormLayout topLayout = new FormLayout(
				"p:g, 3dlu:g, p:g, 3dlu:g, p:g, 3dlu:g, p:g, 3dlu:g, p:g"
		);
		DefaultFormBuilder topBuilder = new DefaultFormBuilder(topLayout);
		topBuilder.setBorder(Borders.DLU2_BORDER);
		
		topBuilder.append(header, 9);
//		topBuilder.append(ButtonBarFactory.buildRightAlignedBar(reportButton), 7);
//		topBuilder.append(subHeader, 9);
		/*hnrLabel =*/ topBuilder.append("Herstell-Nr.:");
		topBuilder.append("Fl�ssigkeit:");
		topBuilder.append("VbF:");
		topBuilder.append("Gef�hrdungsstufe:");
		topBuilder.append("WGK:");
		topBuilder.append(hnrFeld, fluessigkeitBox, vbfBox);
		topBuilder.append(gefStufeBox, wgkBox);
		
		topPanel = topBuilder.getPanel();
		
		// Panel bauen:
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#fillForm()
	 */
	protected void fillForm() {
		header.setText(getFachdaten().getAnlagenart());
//		subHeader.setText("bei: " + getFachdaten().getBasisObjekt().getBasisBetreiber() + "; " + getFachdaten().getBasisObjekt().getBasisObjektarten().getObjektart());
		hnrFeld.setText(getFachdaten().getHerstellnr());
		fluessigkeitBox.setSelectedItem(getFachdaten().getFluessigkeit());
		vbfBox.setSelectedItem(getFachdaten().getVbfeinstufung());
		gefStufeBox.setSelectedItem(getFachdaten().getGefaehrdungsstufe());
		wgkBox.setSelectedItem(getFachdaten().getWgk());
		
		baujahrFeld.setValue(getFachdaten().getBaujahr());
		inbetriebnahmeChooser.setDate(getFachdaten().getDatumInbetriebnahme());
		genehmigungChooser.setDate(getFachdaten().getDatumGenehmigung());
		erfassungChooser.setDate(getFachdaten().getDatumErfassung());
		stillegungChooser.setDate(getFachdaten().getStillegungsdatum());
		pruefTurnusFeld.setValue(getFachdaten().getPruefturnus());
		
		behaelterArtBox.setSelectedItem(getFachdaten().getBehaelterart());
		materialBox.setSelectedItem(getFachdaten().getMaterial());
		bemerkungArea.setText(getFachdaten().getBemerkungen());
		
		anlagenChronoModel.setFachdaten(getFachdaten());
		
		if (getFachdaten().isLageranlage()) {
			tabbedPane.addTab("Daten", getDatenLageranlagenTab());
			tabbedPane.addTab("Schutzvorkehrungen", getSchutzLageranlagenTab());
			tabbedPane.addTab("Leitungen", getLeitungenLageranlagenTab());
			
			mengeFeld.setValue(getFachdaten().getMenge());
			
			if(getFachdaten().getDoppelwandig()!=null)
				doppelWandigCheck.setSelected(getFachdaten().getDoppelwandig());
			else
				doppelWandigCheck.setSelected(false);
			
			if(getFachdaten().getLeckanzeige()!=null)
				leckAnzeigeCheck.setSelected(getFachdaten().getLeckanzeige());
			else
				leckAnzeigeCheck.setSelected(false);
			
			if(getFachdaten().getAuffangraum()!=null)
				auffangRaumCheck.setSelected(getFachdaten().getAuffangraum());
			else
				auffangRaumCheck.setSelected(false);
			
			if(getFachdaten().getLeckschutzauskleidung()!=null)
				leckSchutzAuskleidungCheck.setSelected(getFachdaten().getLeckschutzauskleidung());
			else
				leckSchutzAuskleidungCheck.setSelected(false);
			
			if(getFachdaten().getGrenzwertgeber()!=null)
				grenzWertGeberCheck.setSelected(getFachdaten().getGrenzwertgeber());
			else
				grenzWertGeberCheck.setSelected(false);
			
			if(getFachdaten().getKellerlagerung()!=null)
				kellerLagerungCheck.setSelected(getFachdaten().getKellerlagerung());
			else
				kellerLagerungCheck.setSelected(false);
			
			if(getFachdaten().getInnenbeschichtung()!=null)
				innenBeschichtungCheck.setSelected(getFachdaten().getInnenbeschichtung());
			else
				innenBeschichtungCheck.setSelected(false);

			beschreibungAFeld.setText(getFachdaten().getBeschreibung_a());
			beschreibungSFeld.setText(getFachdaten().getBeschreibung_s());
			
			if(getFachdaten().getOberirdisch()!=null)
				oberIrdischCheck.setSelected(getFachdaten().getOberirdisch());
			else
				oberIrdischCheck.setSelected(false);
			
			if(getFachdaten().getUnterirdisch()!=null)
				unterIrdischCheck.setSelected(getFachdaten().getUnterirdisch());
			else
				unterIrdischCheck.setSelected(false);
			
			if(getFachdaten().getSaugleitung()!=null)
				saugLeitungCheck.setSelected(getFachdaten().getSaugleitung());
			else
				saugLeitungCheck.setSelected(false);
			
			if(getFachdaten().getRohrKathodenschutz()!=null)
				rohrKathSchCheck.setSelected(getFachdaten().getRohrKathodenschutz());
			else
				rohrKathSchCheck.setSelected(false);
			
			if(getFachdaten().getAusKupfer()!=null)
				ausKupferCheck.setSelected(getFachdaten().getAusKupfer());
			else
				ausKupferCheck.setSelected(false);
			
			if(getFachdaten().getAusStahl()!=null)
				ausStahlCheck.setSelected(getFachdaten().getAusStahl());
			else
				ausStahlCheck.setSelected(false);
			
			if(getFachdaten().getMitSchutzrohr()!=null)
				schutzrohrCheck.setSelected(getFachdaten().getMitSchutzrohr());
			else
				schutzrohrCheck.setSelected(false);
			
			beschreibungRFeld.setText(getFachdaten().getBeschreibung_r());
		} else if (getFachdaten().isRohrleitung()) {
			tabbedPane.addTab("Daten", getDatenRohrleitungenTab());
			
			ausfuehrungBox.setSelectedItem(getFachdaten().getAusfuehrung());
		} else if (getFachdaten().isAbfuellflaeche()) {
			tabbedPane.addTab("Daten", getDatenAbfuellflaechenTab());
			tabbedPane.addTab("Ausf�hrung", getAusfuehrungAbfuellflaechenTab());
			
			if(getAbfuellflaeche().getEoh()!=null)
				eohCheck.setSelected(getAbfuellflaeche().getEoh());
			else
				eohCheck.setSelected(false);
			
			if(getAbfuellflaeche().getEf()!=null)
				efCheck.setSelected(getAbfuellflaeche().getEf());
			else
				efCheck.setSelected(false);
			
			if(getAbfuellflaeche().getAbfsaniert()!=null)
				saniertCheck.setSelected(getAbfuellflaeche().getAbfsaniert());
			else
				saniertCheck.setSelected(false);
			
			if(getAbfuellflaeche().getAbfneuerstellt()!=null)
				neuErstelltCheck.setSelected(getAbfuellflaeche().getAbfneuerstellt());
			else
				neuErstelltCheck.setSelected(false);

			
			bodenflaechenAusfBox.setSelectedItem(getAbfuellflaeche().getBodenflaechenausf());
			dickeFeld.setValue(getAbfuellflaeche().getDicke());
			gueteFeld.setText(getAbfuellflaeche().getGuete());
			fugenMaterialFeld.setText(getAbfuellflaeche().getFugenmaterial());
			niederschlagSchutzBox.setSelectedItem(getAbfuellflaeche().getNiederschlagschutz());
			
			if(getFachdaten().getDoppelwandig()!=null)
				abscheiderVorhandenCheck.setSelected(getFachdaten().getDoppelwandig());
			else
				abscheiderVorhandenCheck.setSelected(false);

			beschrBodenflaecheArea.setText(getAbfuellflaeche().getBeschbodenfl());
			beschrFugenmaterialArea.setText(getAbfuellflaeche().getBeschfugenmat());
			beschrAblNiederschlArea.setText(getAbfuellflaeche().getBeschableitung());
		}
		tabbedPane.addTab("Sachverst�ndigenpr�fung", getSvPruefungTab());
		svPruefungModel.setFachdaten(getFachdaten());
		tabbedPane.addTab("Verwaltungsverfahren", getVerwVerfahrenTab());
		verwVerfahrenModel.setFachdaten(getFachdaten());
		tabbedPane.addTab("Verwaltungsgeb�hren", getVerwGebuehrenTab());
		verwGebuehrenModel.setFachdaten(getFachdaten());
		
		this.pack();
		this.setLocationRelativeTo(frame);
	}
	

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#canSave()
	 */
	protected boolean canSave() {
		// TODO: Irgendwelche �berpr�fungen n�tig?
//		String hnr = hnrFeld.getText();
//		
//		if (hnr == null || "".equals(hnr.trim())) {
//			hnrLabel.setForeground(Color.RED);
//			hnrFeld.requestFocus();
//			return false;
//		} else {
			return true;
//		}
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#doSave()
	 */
	protected boolean doSave() {
		String tmp = hnrFeld.getText();
		if (tmp != null) {
			tmp = tmp.trim();
		}
		getFachdaten().setHerstellnr(tmp);
		
		tmp = (String) fluessigkeitBox.getSelectedItem();
		if (tmp != null) {
			tmp = tmp.trim();
		}
		getFachdaten().setFluessigkeit(tmp);
		getFachdaten().setVbfeinstufung((String)vbfBox.getSelectedItem());
		getFachdaten().setGefaehrdungsstufe((String)gefStufeBox.getSelectedItem());
		getFachdaten().setWgk((Integer)wgkBox.getSelectedItem());
		
		getFachdaten().setBaujahr(baujahrFeld.getIntValue());
		getFachdaten().setDatumInbetriebnahme(inbetriebnahmeChooser.getDate());
		getFachdaten().setDatumGenehmigung(genehmigungChooser.getDate());
		getFachdaten().setDatumErfassung(erfassungChooser.getDate());
		getFachdaten().setStillegungsdatum(stillegungChooser.getDate());
		getFachdaten().setPruefturnus(pruefTurnusFeld.getDoubleValue());
		
		getFachdaten().setBehaelterart((String)behaelterArtBox.getSelectedItem());
		getFachdaten().setMaterial((String)materialBox.getSelectedItem());
		getFachdaten().setBemerkungen(bemerkungArea.getText());
		
		boolean success = true;
		
		if (getFachdaten().isLageranlage()) {
			getFachdaten().setMenge(mengeFeld.getDoubleValue());
			
			getFachdaten().setDoppelwandig(doppelWandigCheck.isSelected());
			getFachdaten().setLeckanzeige(leckAnzeigeCheck.isSelected());
			getFachdaten().setAuffangraum(auffangRaumCheck.isSelected());
			getFachdaten().setLeckschutzauskleidung(leckSchutzAuskleidungCheck.isSelected());
			getFachdaten().setGrenzwertgeber(grenzWertGeberCheck.isSelected());
			getFachdaten().setKellerlagerung(kellerLagerungCheck.isSelected());
			getFachdaten().setInnenbeschichtung(innenBeschichtungCheck.isSelected());
			getFachdaten().setBeschreibung_a(beschreibungAFeld.getText());
			getFachdaten().setBeschreibung_s(beschreibungSFeld.getText());
			
			getFachdaten().setOberirdisch(oberIrdischCheck.isSelected());
			getFachdaten().setUnterirdisch(unterIrdischCheck.isSelected());
			getFachdaten().setSaugleitung(saugLeitungCheck.isSelected());
			getFachdaten().setRohrKathodenschutz(rohrKathSchCheck.isSelected());
			getFachdaten().setAusKupfer(ausKupferCheck.isSelected());
			getFachdaten().setAusStahl(ausStahlCheck.isSelected());
			getFachdaten().setMitSchutzrohr(schutzrohrCheck.isSelected());
			getFachdaten().setBeschreibung_r(beschreibungRFeld.getText());
		} else if (getFachdaten().isRohrleitung()) {
			getFachdaten().setAusfuehrung((String)ausfuehrungBox.getSelectedItem());
		}
		
		success = success && VawsFachdaten.saveFachdaten(getFachdaten());
		
		// F�r Abf�llfl�chen (wg. dem VawsAbfuellflaechen-Objekt)
		if (getFachdaten().isAbfuellflaeche()) {
			getAbfuellflaeche().setEoh(eohCheck.isSelected());
			getAbfuellflaeche().setEf(efCheck.isSelected());
			if (unbktCheck.isSelected()) {
				getAbfuellflaeche().setAbfneuerstellt(null);
				getAbfuellflaeche().setAbfsaniert(null);
			} else {
				getAbfuellflaeche().setAbfneuerstellt(neuErstelltCheck.isSelected());
				getAbfuellflaeche().setAbfsaniert(saniertCheck.isSelected());
			}
			
			getAbfuellflaeche().setBodenflaechenausf((String)bodenflaechenAusfBox.getSelectedItem());
			getAbfuellflaeche().setDicke(dickeFeld.getFloatValue());
			getAbfuellflaeche().setGuete(gueteFeld.getText());
			getAbfuellflaeche().setFugenmaterial(fugenMaterialFeld.getText());
			getAbfuellflaeche().setNiederschlagschutz((String)niederschlagSchutzBox.getSelectedItem());
			getAbfuellflaeche().setAbscheidervorh(abscheiderVorhandenCheck.isSelected());
			getAbfuellflaeche().setBeschbodenfl(beschrBodenflaecheArea.getText());
			getAbfuellflaeche().setBeschfugenmat(beschrFugenmaterialArea.getText());
			getAbfuellflaeche().setBeschableitung(beschrAblNiederschlArea.getText());
			
			success = success && VawsAbfuellflaeche.saveAbfuellflaeche(getAbfuellflaeche());
		}
		
		// Anlagenchronologie speichern:
		for (Iterator it = anlagenChronoModel.getList().iterator(); it.hasNext();) {
			success = success && VawsAnlagenchrono.saveAnlagenChrono((VawsAnlagenchrono) it.next());
		}
		for (Iterator it = anlagenChronoModel.getGeloeschte().iterator(); it.hasNext();) {
			success = success && VawsAnlagenchrono.removeAnlagenChrono((VawsAnlagenchrono) it.next());
		}
		AUIKataster.debugOutput(
				anlagenChronoModel.getList().size() + " AnlagenChrono-Eintr�ge neu/behalten, " + 
				anlagenChronoModel.getGeloeschte().size()+" Eintr�ge gel�scht.", "VawsEditor"
		);
		
		// Sachverst�ndigenpr�fung speichern:
		for (Iterator it = svPruefungModel.getList().iterator(); it.hasNext();) {
			success = success && VawsKontrollen.saveKontrolle((VawsKontrollen) it.next());
		}
		for (Iterator it = svPruefungModel.getGeloeschte().iterator(); it.hasNext();) {
			success = success && VawsKontrollen.removeKontrolle((VawsKontrollen) it.next());
		}
		AUIKataster.debugOutput(
				svPruefungModel.getList().size() + " Sachverst�ndigenpr�fungs-Eintr�ge neu/behalten, " + 
				svPruefungModel.getGeloeschte().size()+" Eintr�ge gel�scht.", "VawsEditor"
		);
		
		// Verwaltungsverfahren speichern:
		for (Iterator it = verwVerfahrenModel.getList().iterator(); it.hasNext();) {
			success = success && VawsVerwaltungsverf.saveVerfahren((VawsVerwaltungsverf) it.next());
		}
		for (Iterator it = verwVerfahrenModel.getGeloeschte().iterator(); it.hasNext();) {
			success = success && VawsVerwaltungsverf.removeVerfahren((VawsVerwaltungsverf) it.next());
		}
		AUIKataster.debugOutput(
				verwVerfahrenModel.getList().size() + " Verwaltungsverfahren-Eintr�ge neu/behalten, " + 
				verwVerfahrenModel.getGeloeschte().size()+" Eintr�ge gel�scht.", "VawsEditor"
		);
		
		// Verwaltunggeb�hren speichern:
		for (Iterator it = verwGebuehrenModel.getList().iterator(); it.hasNext();) {
			success = success && VawsVerwaltungsgebuehren.saveGebuehr((VawsVerwaltungsgebuehren) it.next());
		}
		for (Iterator it = verwGebuehrenModel.getGeloeschte().iterator(); it.hasNext();) {
			success = success && VawsVerwaltungsgebuehren.removeGebuehr((VawsVerwaltungsgebuehren) it.next());
		}
		AUIKataster.debugOutput(
				verwGebuehrenModel.getList().size() + " Verwaltungsgeb�hren-Eintr�ge neu/behalten, " + 
				verwGebuehrenModel.getGeloeschte().size()+" Eintr�ge gel�scht.", "VawsEditor"
		);
		
		return success;
	}
	
	// Kontextmen� f�r die Tabellen:
	
	/**
	 * Zeigt ein Kontextmen� an, wenn ein entsprechendes 
	 * MouseEvent vorliegt.
	 * @param e Das MouseEvent.
	 */
	private void showTabellenPopup(MouseEvent e) {
		if (tabellenPopup == null) {
			tabellenPopup = new JPopupMenu();
			JMenuItem loeschItem = new JMenuItem(getTabellenItemLoeschAction());
			
			tabellenPopup.add(loeschItem);
		}
		
		if (e.isPopupTrigger()) {
			Point origin = e.getPoint();
			JTable table = (JTable) e.getSource();
			int row = table.rowAtPoint(origin);
			
			if (row != -1) {
				table.setRowSelectionInterval(row, row);
				tabellenPopup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
	private Action getTabellenItemLoeschAction() {
		if (tabellenItemLoeschAction == null) {
			tabellenItemLoeschAction = new AbstractAction("Eintrag l�schen") {
				public void actionPerformed(ActionEvent e) {
					int index = tabbedPane.getSelectedIndex();
					System.out.println("index: " + index);
					if (index == 0) {
						removeRowFromTable(anlagenChronoTabelle, anlagenChronoModel);
					} else if (index == (tabbedPane.getTabCount()-3)) {
						removeRowFromTable(svPruefungTabelle, svPruefungModel);
					} else if (index == (tabbedPane.getTabCount()-2)) {
						removeRowFromTable(verwVerfahrenTabelle, verwVerfahrenModel);
					} else if (index == (tabbedPane.getTabCount()-1)) {
						removeRowFromTable(verwGebuehrenTabelle, verwGebuehrenModel);
					}
				}
			};
			tabellenItemLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
			tabellenItemLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
		}
		return tabellenItemLoeschAction;
	}
	
	private void removeRowFromTable(JTable table, EditableListTableModel model) {
		int row = table.getSelectedRow();
		
		// Nat�rlich nur, wenn wirklich eine Zeile ausgew�hlt ist
		if (row != -1) {
			model.removeRow(row);
		}
	}
	
	// Tabs:
	private JPanel getDatenLageranlagenTab() {
		if (datenLageranlagenTab == null) {
			FormLayout layout = new FormLayout(
					"r:p, 3dlu, f:p:g, 10dlu, r:p, 3dlu, f:p:g, 0:g"
			);
			layout.setColumnGroups(new int[][]{{1,5}, {3,7}});
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.setDefaultDialogBorder();
			
			builder.appendSeparator("Stammdaten");
			builder.append("Baujahr:", baujahrFeld);
			builder.append("Menge [m�]:", mengeFeld);
			builder.nextLine();
			
			builder.append("Inbetriebnahme:", inbetriebnahmeChooser);
			builder.append("Pr�fturnus [Jahre]:", pruefTurnusFeld);
			builder.nextLine();
			
			builder.append("Genehmigung:", genehmigungChooser);
			builder.append("Beh�lterart:", behaelterArtBox);
			builder.nextLine();
			
			builder.append("Erfassung:", erfassungChooser);
			builder.append("Material:", materialBox);
			builder.nextLine();
			
			builder.append("Stillegung:", stillegungChooser);
			builder.nextLine();
			
			builder.appendSeparator("Bemerkungen");
			builder.appendRow("3dlu");
			builder.appendRow("fill:30dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(bemerkungArea),8);
			
			builder.appendSeparator("Anlagen-Chronologie");
			builder.appendRow("3dlu");
			builder.appendRow("fill:40dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(anlagenChronoTabelle),8);
			
			datenLageranlagenTab = builder.getPanel();
		}
		return datenLageranlagenTab;
	}
	
	private JPanel getSchutzLageranlagenTab() {
		if (schutzLageranlagenTab == null) {
			FormLayout layout = new FormLayout("p, 3dlu:g, p, 3dlu:g, p");
			layout.setColumnGroups(new int[][]{{1,3,5}});
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.setDefaultDialogBorder();
			
			builder.append(doppelWandigCheck, leckAnzeigeCheck, leckSchutzAuskleidungCheck);
			builder.append(auffangRaumCheck, kellerLagerungCheck, innenBeschichtungCheck);
			builder.append(grenzWertGeberCheck);
			builder.nextLine();
			
			builder.appendSeparator("Beschreibung: Schutzvorkehrungen");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschreibungSFeld), 5);
			
			builder.appendSeparator("Beschreibung: Auffangraum");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschreibungAFeld), 5);
			
			schutzLageranlagenTab = builder.getPanel();
		}
		return schutzLageranlagenTab;
	}
	
	private JPanel getLeitungenLageranlagenTab() {
		if (leitungenLageranlagenTab == null) {
			FormLayout layout = new FormLayout("p, 3dlu:g, p, 3dlu:g, p");
			layout.setColumnGroups(new int[][]{{1,3,5}});
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.setDefaultDialogBorder();
			
			builder.append(oberIrdischCheck, ausKupferCheck, saugLeitungCheck);
			builder.append(unterIrdischCheck, ausStahlCheck, rohrKathSchCheck);
			builder.append(schutzrohrCheck);
			builder.nextLine();
			
			builder.appendSeparator("Beschreibung: Rohrleitung");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschreibungRFeld), 5);
			
			leitungenLageranlagenTab = builder.getPanel();
		}
		return leitungenLageranlagenTab;
	}
	
	private JPanel getDatenRohrleitungenTab() {
		if (datenRohrleitungenTab == null) {
			FormLayout layout = new FormLayout(
					"r:p, 3dlu, p, 10dlu, r:p, 3dlu, p, 0:g"
			);
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.setDefaultDialogBorder();
			
			builder.appendSeparator("Stammdaten");
			builder.append("Baujahr:", baujahrFeld);
			builder.append("Ausf�hrung:", ausfuehrungBox);
			builder.nextLine();
			
			builder.append("Inbetriebnahme:", inbetriebnahmeChooser);
			builder.append("Pr�fturnus [Jahre]:", pruefTurnusFeld);
			builder.nextLine();
			
			builder.append("Genehmigung:", genehmigungChooser);
			builder.append("Bauart:", behaelterArtBox);
			builder.nextLine();
			
			builder.append("Erfassung:", erfassungChooser);
			builder.append("Material:", materialBox);
			builder.nextLine();
			
			builder.append("Stillegung:", stillegungChooser);
			builder.nextLine();
			
			builder.appendSeparator("Beschreibung der Rohrleitung:");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(bemerkungArea), 8);
			builder.nextLine();
			
			builder.appendSeparator("Anlagen-Chronologie");
			builder.appendRow("3dlu");
			builder.appendRow("fill:40dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(anlagenChronoTabelle), 8);
			
			datenRohrleitungenTab = builder.getPanel();
		}
		return datenRohrleitungenTab;
	}
	
	private JPanel getDatenAbfuellflaechenTab() {
		if (datenAbfuellflaechenTab == null) {
			FormLayout layout = new FormLayout(
					"r:p, 3dlu, f:p:g, 10dlu, r:p, 3dlu, f:p:g"//, 10dlu, l:p:g"
			);
			layout.setColumnGroups(new int[][]{{1,5},{3,7}});
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.setDefaultDialogBorder();
			
			builder.appendSeparator("Stammdaten");
			builder.append("Baujahr:", baujahrFeld);
			builder.append("Pr�fturnus [Jahre]:", pruefTurnusFeld);
			
			builder.append("Inbetriebnahme:", inbetriebnahmeChooser);
			builder.append("Erfassung:", erfassungChooser);
			
			builder.append("Genehmigung:", genehmigungChooser);
			builder.append("Stillegung:", stillegungChooser);
			
			builder.appendSeparator("Abf�llfl�che");

			builder.append("", neuErstelltCheck);
			builder.append("", eohCheck);
			builder.append("", saniertCheck);
			builder.append("", efCheck);
			builder.append("", unbktCheck);
			builder.append("", svbCheck);
			
			builder.appendSeparator("Beschreibung der Abf�llfl�che");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow(0.3)");
			builder.nextLine(2);
			builder.append(new JScrollPane(bemerkungArea), 7);
			builder.nextLine();
			
			builder.appendSeparator("Anlagen-Chronologie");
			builder.appendRow("3dlu");
			builder.appendRow("fill:40dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(anlagenChronoTabelle),7);
			
			datenAbfuellflaechenTab = builder.getPanel();
		}
		return datenAbfuellflaechenTab;
	}
	
	private JPanel getAusfuehrungAbfuellflaechenTab() {
		if (ausfuehrungAbfuellflaechenTab == null) {
			FormLayout layout = new FormLayout(
					"r:p, 3dlu, f:p:g, 10dlu, r:p, 3dlu, f:p:g"
			);
			layout.setColumnGroups(new int[][]{{1,5},{3,7}});
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.setDefaultDialogBorder();
			
			builder.appendSeparator("Daten");
			builder.append("Bodenfl�chenausf�hrung:", bodenflaechenAusfBox);
			builder.append("Fugenmaterial:", fugenMaterialFeld);
			
			builder.append("Dicke [cm]:", dickeFeld);
			builder.append("Niederschlagsschutz:", niederschlagSchutzBox);
			
			builder.append("G�te:", gueteFeld);
			builder.append("", abscheiderVorhandenCheck);
			
			builder.appendSeparator("Beschreibung Bodenfl�che");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschrBodenflaecheArea), 7);
			builder.nextLine();
			
			builder.appendSeparator("Beschreibung Fugenmaterial");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschrFugenmaterialArea), 7);
			builder.nextLine();
			
			builder.appendSeparator("Beschreibung Ableitung / Niederschlagswasser");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschrAblNiederschlArea),7);
			
			ausfuehrungAbfuellflaechenTab = builder.getPanel();
		}
		return ausfuehrungAbfuellflaechenTab;
	}
	
	private JPanel getSvPruefungTab() {
		if (svPruefungTab == null) {
			FormLayout layout = new FormLayout(
					"f:p:g"
			);
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.setDefaultDialogBorder();
			
			builder.appendRow("fill:25dlu:grow");
			builder.append(new JScrollPane(svPruefungTabelle));//,7);
			
			svPruefungTab = builder.getPanel();
		}
		return svPruefungTab;
	}
	
	private JPanel getVerwVerfahrenTab() {
		if (verwVerfahrenTab == null) {
			FormLayout layout = new FormLayout(
					"f:p:g"
			);
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.setDefaultDialogBorder();
			
			builder.appendRow("fill:25dlu:grow");
			builder.append(new JScrollPane(verwVerfahrenTabelle));//,7);
			
			verwVerfahrenTab = builder.getPanel();
		}
		return verwVerfahrenTab;
	}
	
	private JPanel getVerwGebuehrenTab() {
		if (verwGebuehrenTab == null) {
			FormLayout layout = new FormLayout(
					"f:p:g"
			);
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.setDefaultDialogBorder();
			
			builder.appendRow("fill:25dlu:grow");
			builder.append(new JScrollPane(verwGebuehrenTabelle));//,7);
			
			verwGebuehrenTab = builder.getPanel();
		}
		return verwGebuehrenTab;
	}
}


/**
 * Ein editierbares TableModel f�r die Vaws-Anlagenchronologie.
 * @author David Klotz
 */
class VawsAnlagenChronoModel extends EditableListTableModel {
	private List geloeschte;
	private VawsFachdaten fachdaten;
	
	/**
	 * Erzeugt ein einfaches TableModel f�r die Vaws-Anlagenchronologie.
	 */
	public VawsAnlagenChronoModel() {
		super(new String[]{
				"Datum", 
				"Sachverhalt", 
				"Wiedervorlage"
		}, 
		false, true);
		geloeschte = new ArrayList();
	}

	/**
	 * Setzt das Fachdaten-Objekt und aktualisiert die Tabelle.
	 * @param fachdaten Das Fachdaten-Objekt
	 */
	public void setFachdaten(VawsFachdaten fachdaten) {
		this.fachdaten = fachdaten;
		
		if (fachdaten != null) {
			setList(VawsAnlagenchrono.getAnlagenChrono(fachdaten));
			fireTableDataChanged();
		}
	}
	
	public void editObject(Object objectAtRow, int columnIndex, Object newValue) {
		VawsAnlagenchrono chrono = (VawsAnlagenchrono) objectAtRow;
		String tmp = (String) newValue;
		
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
		
		switch (columnIndex) {
		case 0:
			try {
				Date tmpDate = format.parse(tmp);
				chrono.setDatum(tmpDate);
			} catch (ParseException e) {
				//.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
			}
			break;
		case 1:
			// Auf 255 Zeichen k�rzen, da die Datenbank-Spalte nur 255 Zeichen breit ist
			if (tmp.length() > 255) {
				tmp = tmp.substring(0,255);
			}
			chrono.setSachverhalt(tmp);
			break;
		case 2:
			if ("".equals(tmp)) {
				chrono.setWv(null);
			} else {
				try {
					Date tmpDate = format.parse(tmp);
					chrono.setWv(tmpDate);
				} catch (ParseException e) {
					//.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
				}
			}
			break;

		default:
			break;
		}
	}
	
	public Object newObject() {
		VawsAnlagenchrono chr = new VawsAnlagenchrono();
		chr.setVawsFachdaten(fachdaten);
		chr.setDatum(new Date());
		return chr;
	}
	
	public boolean objectRemoved(Object objectAtRow) {
		VawsAnlagenchrono chrono = (VawsAnlagenchrono) objectAtRow;
		if (chrono.getId() != null) {
			geloeschte.add(chrono);
		}
		return true;
	}
	
	public List getGeloeschte() {
		return geloeschte;
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		VawsAnlagenchrono ac = (VawsAnlagenchrono) objectAtRow;
		Object tmp;
		
		switch (columnIndex) {
		// Datum:
		case 0:
			tmp = AuikUtils.getStringFromDate(ac.getDatum());
			break;
		// Sachverhalt:
		case 1:
			tmp = ac.getSachverhalt();
			break;
		// Wv:
		case 2:
			tmp = AuikUtils.getStringFromDate(ac.getWv());
			break;
		
		// Andere Spalten sollten nicht vorkommen, deshalb "Fehler":
		default:
			tmp = "ERROR";
			break;
		}
		
		return tmp;
	}
	
	/**
	 * Liefert einen Datensatz in einer bestimmten Zeile.
	 * @param row Die Zeile der Tabelle.
	 * @return Den Datensatz, der in dieser Zeile angezeigt wird.
	 */
	public VawsAnlagenchrono getDatenSatz(int row) {
		return (VawsAnlagenchrono) getObjectAtRow(row);
	}
	
	/* 
	 * Leer, da kein Updaten der Liste n�tig/m�glich.
	 * Die Liste wird direkt mittels setList "bef�llt".
	 */
	public void updateList() {
	}
}

/**
 * Ein editierbares TableModel f�r die Vaws-Kontrollen 
 * (Sachverst�ndigenpr�fung).
 * @author David Klotz
 */
class VawsKontrollenModel extends EditableListTableModel {
	private List geloeschte;
	private VawsFachdaten fachdaten;
	
	/**
	 * Erzeugt ein einfaches TableModel f�r die Vaws-Kontrollen.
	 */
	public VawsKontrollenModel() {
		super(new String[]{
				"Pr�fdatum", 
				"Pr�fer", 
				"Pr�fergebnis",
				"N�chste Pr�fung",
				"Prfg. abgeschl."
		}, 
		false, true);
		geloeschte = new ArrayList();
	}

	/**
	 * Setzt das Fachdaten-Objekt und aktualisiert die Tabelle.
	 * @param fachdaten Das Fachdaten-Objekt
	 */
	public void setFachdaten(VawsFachdaten fachdaten) {
		this.fachdaten = fachdaten;
		
		if (fachdaten != null) {
			setList(VawsKontrollen.getKontrollen(fachdaten));
			fireTableDataChanged();
		}
	}
	
	public void editObject(Object objectAtRow, int columnIndex, Object newValue) {
		VawsKontrollen ktrl = (VawsKontrollen) objectAtRow;
		String tmp = "";
		if (newValue instanceof String) {
			tmp = (String) newValue;
		}
		
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
		
		switch (columnIndex) {
		case 0:
			try {
				Date tmpDate = format.parse(tmp);
				ktrl.setPruefdatum(tmpDate);
			} catch (ParseException e) {
				//.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
			}
			break;
		case 1:
			ktrl.setPruefer(tmp);
			break;
		case 2:
			ktrl.setPruefergebnis(tmp);
			break;
		case 3:
			if ("".equals(tmp)) {
				ktrl.setNaechstepruefung(null);
			} else {
				try {
					Date tmpDate = format.parse(tmp);
					ktrl.setNaechstepruefung(tmpDate);
				} catch (ParseException e) {
					//.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
				}
			}
			break;
		case 4:
			Boolean tmpB = (Boolean) newValue;
			ktrl.setPruefungabgeschlossen(tmpB.booleanValue());
			break;

		default:
			break;
		}
	}
	
	public Object newObject() {
		VawsKontrollen ktr = new VawsKontrollen();
		ktr.setVawsFachdaten(fachdaten);
		ktr.setPruefdatum(new Date());
		ktr.setPruefungabgeschlossen(false);
		return ktr;
	}
	
	public boolean objectRemoved(Object objectAtRow) {
		VawsKontrollen ktr = (VawsKontrollen) objectAtRow;
		if (ktr.getId() != null) {
			geloeschte.add(ktr);
		}
		return true;
	}
	
	public List getGeloeschte() {
		return geloeschte;
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		VawsKontrollen ac = (VawsKontrollen) objectAtRow;
		Object tmp;
		
		switch (columnIndex) {
		// Pr�fdatum:
		case 0:
			tmp = AuikUtils.getStringFromDate(ac.getPruefdatum());
			break;
		// Pr�fer:
		case 1:
			tmp = ac.getPruefer();
			break;
		// Pr�fergebnis:
		case 2:
			tmp = ac.getPruefergebnis();
			break;
		// N�chste Pr�fung:
		case 3:
			tmp = AuikUtils.getStringFromDate(ac.getNaechstepruefung());
			break;
		// Pr�fung abgeschlossen?:
		case 4:
			tmp = ac.getPruefungabgeschlossen();
			break;
		
		// Andere Spalten sollten nicht vorkommen, deshalb "Fehler":
		default:
			tmp = "ERROR";
			break;
		}
		
		return tmp;
	}
	
	public Class getColumnClass(int columnIndex) {
		if (columnIndex == 4) {
			return Boolean.class;
		} else {
			return String.class;
		}
	}
	
	/**
	 * Liefert einen Datensatz in einer bestimmten Zeile.
	 * @param row Die Zeile der Tabelle.
	 * @return Den Datensatz, der in dieser Zeile angezeigt wird.
	 */
	public VawsKontrollen getDatenSatz(int row) {
		return (VawsKontrollen) getObjectAtRow(row);
	}
	
	/* 
	 * Leer, da kein Updaten der Liste n�tig/m�glich.
	 * Die Liste wird direkt mittels setList "bef�llt".
	 */
	public void updateList() {
	}
}

/**
 * Ein editierbares TableModel f�r die VawsVerwaltungsverfahren.
 * @author David Klotz
 */
class VerwVerfahrenModel extends EditableListTableModel {
	private List geloeschte;
	private VawsFachdaten fachdaten;
	
	/**
	 * Erzeugt ein einfaches TableModel f�r die Vaws-Verwaltungsverfahren.
	 */
	public VerwVerfahrenModel() {
		super(new String[]{
				"Datum",  
				"Ma�nahmen der Verwaltung",
				"Wiedervorlage",
				"abgeschl."
		}, 
		false, true);
		geloeschte = new ArrayList();
	}

	/**
	 * Setzt das Fachdaten-Objekt und aktualisiert die Tabelle.
	 * @param fachdaten Das Fachdaten-Objekt
	 */
	public void setFachdaten(VawsFachdaten fachdaten) {
		this.fachdaten = fachdaten;
		
		if (fachdaten != null) {
			setList(VawsVerwaltungsverf.getVerwaltungsverf(fachdaten));
			fireTableDataChanged();
		}
	}
	
	public void editObject(Object objectAtRow, int columnIndex, Object newValue) {
		VawsVerwaltungsverf verf = (VawsVerwaltungsverf) objectAtRow;
		String tmp = "";
		if (newValue instanceof String) {
			tmp = (String) newValue;
		}
		
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
		
		switch (columnIndex) {
		case 0:
			try {
				Date tmpDate = format.parse(tmp);
				verf.setDatum(tmpDate);
			} catch (ParseException e) {
				//.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
			}
			break;
		case 1:
			verf.setMassnahme(tmp);
			break;
		case 2:
			if ("".equals(tmp)) {
				verf.setWiedervorlage(null);
			} else {
				try {
					Date tmpDate = format.parse(tmp);
					verf.setWiedervorlage(tmpDate);
				} catch (ParseException e) {
					//.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
				}
			}
			break;
		case 3:
			Boolean tmpB = (Boolean) newValue;
			verf.setWvverwverf(tmpB.booleanValue());
			break;

		default:
			break;
		}
	}
	
	public Object newObject() {
		VawsVerwaltungsverf verf = new VawsVerwaltungsverf();
		verf.setVawsFachdaten(fachdaten);
		verf.setWvverwverf(false);
		verf.setDatum(new Date());
		return verf;
	}
	
	public boolean objectRemoved(Object objectAtRow) {
		VawsVerwaltungsverf verf = (VawsVerwaltungsverf) objectAtRow;
		if (verf.getVerwverfid() != null) {
			geloeschte.add(verf);
		}
		return true;
	}
	
	public List getGeloeschte() {
		return geloeschte;
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		VawsVerwaltungsverf verf = (VawsVerwaltungsverf) objectAtRow;
		Object tmp;
		
		switch (columnIndex) {
		// Datum:
		case 0:
			tmp = AuikUtils.getStringFromDate(verf.getDatum());
			break;
		// Ma�nahme:
		case 1:
			tmp = verf.getMassnahme();
			break;
		// WV:
		case 2:
			tmp = AuikUtils.getStringFromDate(verf.getWiedervorlage());
			break;
		// Pr�fung abgeschlossen?:
		case 3:
			tmp = new Boolean(verf.getWvverwverf());
			break;
		
		// Andere Spalten sollten nicht vorkommen, deshalb "Fehler":
		default:
			tmp = "ERROR";
			break;
		}
		
		return tmp;
	}
	
	public Class getColumnClass(int columnIndex) {
		if (columnIndex == 3) {
			return Boolean.class;
		} else {
			return String.class;
		}
	}
	
	/**
	 * Liefert einen Datensatz in einer bestimmten Zeile.
	 * @param row Die Zeile der Tabelle.
	 * @return Den Datensatz, der in dieser Zeile angezeigt wird.
	 */
	public VawsVerwaltungsverf getDatenSatz(int row) {
		return (VawsVerwaltungsverf) getObjectAtRow(row);
	}
	
	/* 
	 * Leer, da kein Updaten der Liste n�tig/m�glich.
	 * Die Liste wird direkt mittels setList "bef�llt".
	 */
	public void updateList() {
	}
}

/**
 * Ein editierbares TableModel f�r die VawsVerwaltungsverfahren.
 * @author David Klotz
 */
class VerwGebuehrenModel extends EditableListTableModel {
	private List geloeschte;
	private VawsFachdaten fachdaten;
	
	/**
	 * Erzeugt ein einfaches TableModel f�r Vaws-Fachdaten.
	 */
	public VerwGebuehrenModel() {
		super(new String[]{
				"Datum",  
				"Geb�hrenart",
				"Betrag",
				"Abschnitt",
				"Kassenzeichen"
		}, 
		false, true);
		geloeschte = new ArrayList();
	}

	/**
	 * Setzt das Fachdaten-Objekt und aktualisiert die Tabelle.
	 * @param fachdaten Das Fachdaten-Objekt
	 */
	public void setFachdaten(VawsFachdaten fachdaten) {
		this.fachdaten = fachdaten;
		
		if (fachdaten != null) {
			setList(VawsVerwaltungsgebuehren.getVerwaltungsgebuehren(fachdaten));
			fireTableDataChanged();
		}
	}
	
	public void editObject(Object objectAtRow, int columnIndex, Object newValue) {
		VawsVerwaltungsgebuehren gebuehr = (VawsVerwaltungsgebuehren) objectAtRow;
		String tmp = "";
		if (newValue instanceof String) {
			tmp = (String) newValue;
		}
		
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
		
		switch (columnIndex) {
		case 0:
			try {
				Date tmpDate = format.parse(tmp);
				gebuehr.setDatum(tmpDate);
			} catch (ParseException e) {
				//.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
			}
			break;
		case 1:
			gebuehr.setVawsGebuehrenart((VawsGebuehrenarten) newValue);
			break;
		case 2:
			Float tmpWert = null;
			if (newValue instanceof Float) {
				tmpWert = (Float) newValue;
			} else if (newValue instanceof KommaDouble) {
				tmpWert = new Float(((KommaDouble)newValue).getValue().doubleValue());
			}
			gebuehr.setBetrag(tmpWert);
			break;
		case 3:
			gebuehr.setAbschnitt(tmp);
			break;
		case 4:
			gebuehr.setKassenzeichen(tmp);
			break;

		default:
			break;
		}
	}
	
	public Object newObject() {
		VawsVerwaltungsgebuehren gebuehr = new VawsVerwaltungsgebuehren();
		gebuehr.setVawsFachdaten(fachdaten);
		gebuehr.setDatum(new Date());
		return gebuehr;
	}
	
	public boolean objectRemoved(Object objectAtRow) {
		VawsVerwaltungsgebuehren gebuehr = (VawsVerwaltungsgebuehren) objectAtRow;
		if (gebuehr.getId() != null) {
			geloeschte.add(gebuehr);
		}
		return true;
	}
	
	public List getGeloeschte() {
		return geloeschte;
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		VawsVerwaltungsgebuehren gebuehr = (VawsVerwaltungsgebuehren) objectAtRow;
		Object tmp;
		
		switch (columnIndex) {
		// Datum:
		case 0:
			tmp = AuikUtils.getStringFromDate(gebuehr.getDatum());
			break;
		// Geb�hrenart:
		case 1:
			tmp = gebuehr.getVawsGebuehrenart();
			break;
		// Betrag:
		case 2:
			tmp = new KommaDouble(gebuehr.getBetrag().doubleValue());
			break;
		// Abschnitt:
		case 3:
			tmp = gebuehr.getAbschnitt();
			break;
		// Kassenzeichen:
		case 4:
			tmp = gebuehr.getKassenzeichen();
			break;
		
		// Andere Spalten sollten nicht vorkommen, deshalb "Fehler":
		default:
			tmp = "ERROR";
			break;
		}
		
		return tmp;
	}
	
	public Class getColumnClass(int columnIndex) {
		if (columnIndex == 1) {
			return VawsGebuehrenarten.class;
		} else if (columnIndex == 2) {
			return KommaDouble.class;
		} else {
			return String.class;
		}
	}
	
	/**
	 * Liefert einen Datensatz in einer bestimmten Zeile.
	 * @param row Die Zeile der Tabelle.
	 * @return Den Datensatz, der in dieser Zeile angezeigt wird.
	 */
	public VawsVerwaltungsgebuehren getDatenSatz(int row) {
		return (VawsVerwaltungsgebuehren) getObjectAtRow(row);
	}
	
	/* 
	 * Leer, da kein Updaten der Liste n�tig/m�glich.
	 * Die Liste wird direkt mittels setList "bef�llt".
	 */
	public void updateList() {
	}
}