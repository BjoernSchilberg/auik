/*
 * Datei:
 * $Id: ChartDataSets.java,v 1.1 2008-06-05 11:38:38 u633d Exp $
 * 
 * Erstellt am 11.05.2005 von David Klotz (u633z)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/06/01 15:15:59  u633z
 * - Erste Version der Auswertungs-Tabelle
 *
 * Revision 1.1  2005/05/31 15:52:18  u633z
 * - Charts und ChartDataSets nach utils.charts verschoben
 * - Neue Klasse APosDataItem um eine Analyseposition als Punkt einer
 *   TimeSeries Datenreihe zu repr�sentieren
 * - Bessere Tooltips im Auswertungs-Diagramm
 *
 * Revision 1.5  2005/05/19 11:28:35  u633z
 * Kommentare verbessert
 *
 * Revision 1.4  2005/05/18 15:31:59  u633z
 * Diagramm-Erzeugung verbessert, Funktionalit�t der Auswertung hinzugef�gt
 *
 * Revision 1.3  2005/05/17 11:18:50  u633z
 * Einen Parameter umbenannt
 *
 * Revision 1.2  2005/05/12 08:58:01  u633z
 * JavaDoc / Kommentare verbessert
 *
 * Revision 1.1  2005/05/11 15:38:39  u633z
 * Erster Test mit Diagrammen (aus echten Daten)
 *
 */
package de.bielefeld.umweltamt.aui.utils.charts;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;

/**
 * Eine Factory-Klasse um DataSets etc. f�r JFreeChart-Diagramme aus
 * verschiedenen Daten zu erzeugen.
 * @author David Klotz
 */
public class ChartDataSets {
	/**
	 * Erzeugt eine TimeSeriesCollection (eine Sammlung von Zeit/Wert-Datenreihen)
	 * mit einer Datenreihe. Die Zeitwerte werden als Zeitpunkte und nicht 
	 * als Zeitabschnitte behandelt.
	 * @param series Die erste Datenreihe (mehr k�nnen hinzugef�gt werden)
	 * @return Eine TimeSeriesCollection mit einer Datenreihe
	 */
	public static TimeSeriesCollection createDataset(TimeSeries series) {
		TimeSeriesCollection dataset = new TimeSeriesCollection(series);
		dataset.setDomainIsPointsInTime(true);
		
		return dataset;
	}
	
	/**
	 * Erzeugt eine TimeSeries (eine Zeit/Wert-Datenreihe) aus einer Liste
	 * mit AtlAnalysepositionen. Die Analysepositionen sollten nat�rlich 
	 * alle den selben Parameter und die selbe Einheit haben.
	 * @param list Die Liste aus <code>AtlAnalyseposition</code>en
	 * @param name Der Name des Parameters/der Datenreihe (f�r die Legende des Diagramms) 
	 * @param einheit Der Name der Einheit (f�r die Achsenbeschriftung des Diagramms)
	 * @return Eine Analysepositionen-Datenreihe
	 */
	public static TimeSeries createAnalysePositionenSeries(List list, String name, String einheit) {
		TimeSeries result = new TimeSeries(name, "Datum", einheit, Minute.class);
		AUIKataster.debugOutput("Erzeuge TimeSeries: " + name, "ChartDataSets.createAnalysepositionenSeries");
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof AtlAnalyseposition) {
					AtlAnalyseposition pos = (AtlAnalyseposition) list.get(i);
					
					APosDataItem item = new APosDataItem(pos);
					
					addPosToMinuteSeries(result, item);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * F�gt den Wert einer AtlAnalyseposition zu einer TimeSeries an 
	 * einer bestimmten Position (in Minuten) hinzu.
	 * Wenn an dieser Position schon ein Wert existiert, ruft sich diese
	 * Methode rekursiv mit einer Position eine Minute sp�ter wieder auf.
	 * @param series Die Datenreihe
	 * @param minute Die Position in der Datenreihe (in Minuten)
	 * @param pos Die Analyseposition
	 */
	private static void addPosToMinuteSeries(TimeSeries series, APosDataItem item/*Minute minute, AtlAnalyseposition pos*/) {
		if (series.getDataItem(item.getPeriod()) == null) {
			//AUIKataster.debugOutput("  |- F�ge " + pos + " bei " + minute + " hinzu.", "ChartDataSets.createAnalysepositionenSeries");
			series.add(item);
		} else {
			//AUIKataster.debugOutput("  |- !Bei " + minute + " existiert schon ein Eintrag -> Rekursion!.", "ChartDataSets.createAnalysepositionenSeries");
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(item.getAnalysePosition().getAtlProbenahmen().getDatumDerEntnahme());
			Minute minute = item.getMinute();
			APosDataItem item2 = new APosDataItem(item.getAnalysePosition(), new Minute(minute.getMinute()+1, minute.getHour().getHour(), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR)));
			
			
			addPosToMinuteSeries(series, item2);
		}
	}
	
	/*
	 * Private, da nie eine Instanz dieser Klasse erzeugt werden soll.
	 */
	private ChartDataSets() {
	}
}
