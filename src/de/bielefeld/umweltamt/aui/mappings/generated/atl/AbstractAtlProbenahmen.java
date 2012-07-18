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

// Generated 17.07.2012 18:33:28 by Hibernate Tools 3.3.0.GA

package de.bielefeld.umweltamt.aui.mappings.generated.atl;


import de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisSachbearbeiter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link AtlProbenahmen}.
 */
public abstract class AbstractAtlProbenahmen  implements java.io.Serializable {

     private Integer id;
     private AtlStatus atlStatus;
     private AtlProbepkt atlProbepkt;
     private BasisSachbearbeiter basisSachbearbeiter;
     private String kennummer;
     private String art;
     private Date datumDerEntnahme;
     private Date zeitAnfang;
     private Date bisDatum;
     private String zeitDerEntnahmen;
     private Float einwaage;
     private String VM3;
     private String fahrer;
     private String objektNr;
     private Date datumIcp;
     private String sonderparameter;
     private String bemerkung;
     private Date an360x11;
     private String ueberschreitung;
     private Integer anzahlbeteiligte;
     private String uhrzeitbeginn;
     private String uhrzeitende;
     private String fahrtzeit;
     private Date bescheid;
     private Double kosten;
     private String massnahmen;
     private String bezeichnung;
     private Integer uschivorg;
     private boolean enabled;
     private boolean deleted;
     private Set<AtlAnalyseposition> atlAnalysepositions = new HashSet<AtlAnalyseposition>(0);

    public AbstractAtlProbenahmen() {
    }

    public AbstractAtlProbenahmen(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAtlProbenahmen(
    	AtlStatus atlStatus, AtlProbepkt atlProbepkt, BasisSachbearbeiter basisSachbearbeiter, String kennummer, String art, Date datumDerEntnahme, Date zeitAnfang, Date bisDatum, String zeitDerEntnahmen, Float einwaage, String VM3, String fahrer, String objektNr, Date datumIcp, String sonderparameter, String bemerkung, Date an360x11, String ueberschreitung, Integer anzahlbeteiligte, String uhrzeitbeginn, String uhrzeitende, String fahrtzeit, Date bescheid, Double kosten, String massnahmen, String bezeichnung, Integer uschivorg, boolean enabled, boolean deleted, Set<AtlAnalyseposition> atlAnalysepositions) {
        this.atlStatus = atlStatus;
        this.atlProbepkt = atlProbepkt;
        this.basisSachbearbeiter = basisSachbearbeiter;
        this.kennummer = kennummer;
        this.art = art;
        this.datumDerEntnahme = datumDerEntnahme;
        this.zeitAnfang = zeitAnfang;
        this.bisDatum = bisDatum;
        this.zeitDerEntnahmen = zeitDerEntnahmen;
        this.einwaage = einwaage;
        this.VM3 = VM3;
        this.fahrer = fahrer;
        this.objektNr = objektNr;
        this.datumIcp = datumIcp;
        this.sonderparameter = sonderparameter;
        this.bemerkung = bemerkung;
        this.an360x11 = an360x11;
        this.ueberschreitung = ueberschreitung;
        this.anzahlbeteiligte = anzahlbeteiligte;
        this.uhrzeitbeginn = uhrzeitbeginn;
        this.uhrzeitende = uhrzeitende;
        this.fahrtzeit = fahrtzeit;
        this.bescheid = bescheid;
        this.kosten = kosten;
        this.massnahmen = massnahmen;
        this.bezeichnung = bezeichnung;
        this.uschivorg = uschivorg;
        this.enabled = enabled;
        this.deleted = deleted;
        this.atlAnalysepositions = atlAnalysepositions;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public AtlStatus getAtlStatus() {
        return this.atlStatus;
    }
    public void setAtlStatus(AtlStatus atlStatus) {
        this.atlStatus = atlStatus;
    }

    public AtlProbepkt getAtlProbepkt() {
        return this.atlProbepkt;
    }
    public void setAtlProbepkt(AtlProbepkt atlProbepkt) {
        this.atlProbepkt = atlProbepkt;
    }

    public BasisSachbearbeiter getBasisSachbearbeiter() {
        return this.basisSachbearbeiter;
    }
    public void setBasisSachbearbeiter(BasisSachbearbeiter basisSachbearbeiter) {
        this.basisSachbearbeiter = basisSachbearbeiter;
    }

    public String getKennummer() {
        return this.kennummer;
    }
    public void setKennummer(String kennummer) {
        this.kennummer = kennummer;
    }

    public String getArt() {
        return this.art;
    }
    public void setArt(String art) {
        this.art = art;
    }

    public Date getDatumDerEntnahme() {
        return this.datumDerEntnahme;
    }
    public void setDatumDerEntnahme(Date datumDerEntnahme) {
        this.datumDerEntnahme = datumDerEntnahme;
    }

    public Date getZeitAnfang() {
        return this.zeitAnfang;
    }
    public void setZeitAnfang(Date zeitAnfang) {
        this.zeitAnfang = zeitAnfang;
    }

    public Date getBisDatum() {
        return this.bisDatum;
    }
    public void setBisDatum(Date bisDatum) {
        this.bisDatum = bisDatum;
    }

    public String getZeitDerEntnahmen() {
        return this.zeitDerEntnahmen;
    }
    public void setZeitDerEntnahmen(String zeitDerEntnahmen) {
        this.zeitDerEntnahmen = zeitDerEntnahmen;
    }

    public Float getEinwaage() {
        return this.einwaage;
    }
    public void setEinwaage(Float einwaage) {
        this.einwaage = einwaage;
    }

    public String getVM3() {
        return this.VM3;
    }
    public void setVM3(String VM3) {
        this.VM3 = VM3;
    }

    public String getFahrer() {
        return this.fahrer;
    }
    public void setFahrer(String fahrer) {
        this.fahrer = fahrer;
    }

    public String getObjektNr() {
        return this.objektNr;
    }
    public void setObjektNr(String objektNr) {
        this.objektNr = objektNr;
    }

    public Date getDatumIcp() {
        return this.datumIcp;
    }
    public void setDatumIcp(Date datumIcp) {
        this.datumIcp = datumIcp;
    }

    public String getSonderparameter() {
        return this.sonderparameter;
    }
    public void setSonderparameter(String sonderparameter) {
        this.sonderparameter = sonderparameter;
    }

    public String getBemerkung() {
        return this.bemerkung;
    }
    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    public Date getAn360x11() {
        return this.an360x11;
    }
    public void setAn360x11(Date an360x11) {
        this.an360x11 = an360x11;
    }

    public String getUeberschreitung() {
        return this.ueberschreitung;
    }
    public void setUeberschreitung(String ueberschreitung) {
        this.ueberschreitung = ueberschreitung;
    }

    public Integer getAnzahlbeteiligte() {
        return this.anzahlbeteiligte;
    }
    public void setAnzahlbeteiligte(Integer anzahlbeteiligte) {
        this.anzahlbeteiligte = anzahlbeteiligte;
    }

    public String getUhrzeitbeginn() {
        return this.uhrzeitbeginn;
    }
    public void setUhrzeitbeginn(String uhrzeitbeginn) {
        this.uhrzeitbeginn = uhrzeitbeginn;
    }

    public String getUhrzeitende() {
        return this.uhrzeitende;
    }
    public void setUhrzeitende(String uhrzeitende) {
        this.uhrzeitende = uhrzeitende;
    }

    public String getFahrtzeit() {
        return this.fahrtzeit;
    }
    public void setFahrtzeit(String fahrtzeit) {
        this.fahrtzeit = fahrtzeit;
    }

    public Date getBescheid() {
        return this.bescheid;
    }
    public void setBescheid(Date bescheid) {
        this.bescheid = bescheid;
    }

    public Double getKosten() {
        return this.kosten;
    }
    public void setKosten(Double kosten) {
        this.kosten = kosten;
    }

    public String getMassnahmen() {
        return this.massnahmen;
    }
    public void setMassnahmen(String massnahmen) {
        this.massnahmen = massnahmen;
    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Integer getUschivorg() {
        return this.uschivorg;
    }
    public void setUschivorg(Integer uschivorg) {
        this.uschivorg = uschivorg;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Set<AtlAnalyseposition> getAtlAnalysepositions() {
        return this.atlAnalysepositions;
    }
    public void setAtlAnalysepositions(Set<AtlAnalyseposition> atlAnalysepositions) {
        this.atlAnalysepositions = atlAnalysepositions;
    }

}
