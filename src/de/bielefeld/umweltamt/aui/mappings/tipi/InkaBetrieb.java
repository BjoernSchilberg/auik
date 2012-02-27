/**
 * Copyright 2005-2011, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */
package de.bielefeld.umweltamt.aui.mappings.tipi;

import java.util.Calendar;
import java.util.List;

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;
import de.nrw.lds.tipi.inka.Inka_Betrieb;

/**
 * InkaBetrieb generated by hbm2java
 */
public class InkaBetrieb  implements java.io.Serializable {
    private static final long serialVersionUID = 4672911897922106306L;

    private InkaBetriebId id;
    private Integer adresseStandNr;
    private Integer adresseEinleitNr;
    private String gemeindekennzahl;

    public InkaBetrieb() {
    }

    public InkaBetrieb(InkaBetriebId id) {
        this.id = id;
    }

    public InkaBetrieb(InkaBetriebId id, Integer adresseStandNr, Integer adresseEinleitNr, String gemeindekennzahl) {
       this.id = id;
       this.adresseStandNr = adresseStandNr;
       this.adresseEinleitNr = adresseEinleitNr;
       this.gemeindekennzahl = gemeindekennzahl;
    }

    public InkaBetriebId getId() {
        return this.id;
    }

    public void setId(InkaBetriebId id) {
        this.id = id;
    }

    public Integer getAdresseStandNr() {
        return this.adresseStandNr;
    }

    public void setAdresseStandNr(Integer adresseStandNr) {
        this.adresseStandNr = adresseStandNr;
    }

    public Integer getAdresseEinleitNr() {
        return this.adresseEinleitNr;
    }

    public void setAdresseEinleitNr(Integer adresseEinleitNr) {
        this.adresseEinleitNr = adresseEinleitNr;
    }

    public String getGemeindekennzahl() {
        return this.gemeindekennzahl;
    }

    public void setGemeindekennzahl(String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }

    public static List<?> getInkaBetriebe() {
        String query = "FROM InkaBetrieb";
        return new DatabaseAccess().createQuery(query).list();
    }

    public Inka_Betrieb toServiceType() {
        // Ein default Änderungsdatum.
        Calendar aenderung = Calendar.getInstance();
        aenderung.set(2012, 1, 2);
        // Ein default Erfassungsdatum.
        Calendar erfassung = Calendar.getInstance();
        erfassung.set(2012, 1, 1);
        // Ein default Gültigkeits datum. (gültig_von)
        Calendar gueltig = Calendar.getInstance();
        gueltig.set(2012, 1, 1);

        Inka_Betrieb item = new Inka_Betrieb(
            aenderung,
            erfassung,
            Calendar.getInstance(), // Gültig bis aktuelles Datum.
            gueltig,
            true,
            this.getAdresseEinleitNr(), // MussFeld; Default Wert für nicht vohandenes Feld 'adresse_anspr_nr'.
            1, // Default Wert für nicht vohandenes Feld 'adresse_anspr_ver'.
            this.getAdresseEinleitNr(),
            1,  // Default Wert für nicht vohandenes Feld 'adresse_einleit_ver'.
            this.getAdresseStandNr(),
            1, // Default Wert für nicht vohandenes Feld 'adresse_stand_nr'.
            this.getId().getBetriebNr(),
            1, // Default Wert für nicht vohandenes Feld 'betrieb_ver'.
            1, // Default Wert für nicht vohandenes Feld 'gemeinde_ver'
            this.getGemeindekennzahl()
        );
        return item;
    }

}
