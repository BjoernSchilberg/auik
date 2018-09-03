/**
 * Copyright 2005-2042, Stadt Bielefeld
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

// Generated by Hibernate Tools 5.0.0.Final

package de.bielefeld.umweltamt.aui.mappings.atl;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that represents a row in the Probenahme database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Probenahme  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forProbenahme;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Sachbearbeiter sachbearbeiter;
    private Messstelle messstelle;
    private Status status;
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
    private Boolean qbAusschliessen;
    private Set<Analyseposition> analysepositions = new HashSet<Analyseposition>(0);

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Probenahme() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Probenahme(
        Integer id, boolean enabled, boolean deleted) {
        this.id = id;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Probenahme(
        Integer id, Sachbearbeiter sachbearbeiter, Messstelle messstelle, Status status, String kennummer, String art, Date datumDerEntnahme, Date zeitAnfang, Date bisDatum, String zeitDerEntnahmen, Float einwaage, String VM3, String fahrer, String objektNr, Date datumIcp, String sonderparameter, String bemerkung, Date an360x11, String ueberschreitung, Integer anzahlbeteiligte, String uhrzeitbeginn, String uhrzeitende, String fahrtzeit, Date bescheid, Double kosten, String massnahmen, String bezeichnung, Integer uschivorg, boolean enabled, boolean deleted, Boolean qbAusschliessen, Set<Analyseposition> analysepositions) {
        this.id = id;
        this.sachbearbeiter = sachbearbeiter;
        this.messstelle = messstelle;
        this.status = status;
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
        this.qbAusschliessen = qbAusschliessen;
        this.analysepositions = analysepositions;
    }

    /* Setter and getter methods */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sachbearbeiter getSachbearbeiter() {
        return this.sachbearbeiter;
    }

    public void setSachbearbeiter(Sachbearbeiter sachbearbeiter) {
        this.sachbearbeiter = sachbearbeiter;
    }

    public Messstelle getMessstelle() {
        return this.messstelle;
    }

    public void setMessstelle(Messstelle messstelle) {
        this.messstelle = messstelle;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Boolean getQbAusschliessen() {
        return this.qbAusschliessen;
    }

    public void setQbAusschliessen(Boolean qbAusschliessen) {
        this.qbAusschliessen = qbAusschliessen;
    }

    public Set<Analyseposition> getAnalysepositions() {
        return this.analysepositions;
    }

    public void setAnalysepositions(Set<Analyseposition> analysepositions) {
        this.analysepositions = analysepositions;
    }

    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> for a debug
     * string, call on <code>toGuiString</code> for a gui representation or do
     * something completely different.
     * @return String
     */
    @Override
    public String toString() {
        return DatabaseClassToString.toStringForClass(this); 
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("id").append("='").append(getId()).append("' ");			
        buffer.append("sachbearbeiter").append("='").append(getSachbearbeiter()).append("' ");			
        buffer.append("messstelle").append("='").append(getMessstelle()).append("' ");			
        buffer.append("status").append("='").append(getStatus()).append("' ");			
        buffer.append("kennummer").append("='").append(getKennummer()).append("' ");			
        buffer.append("art").append("='").append(getArt()).append("' ");			
        buffer.append("datumDerEntnahme").append("='").append(getDatumDerEntnahme()).append("' ");			
        buffer.append("zeitAnfang").append("='").append(getZeitAnfang()).append("' ");			
        buffer.append("bisDatum").append("='").append(getBisDatum()).append("' ");			
        buffer.append("zeitDerEntnahmen").append("='").append(getZeitDerEntnahmen()).append("' ");			
        buffer.append("einwaage").append("='").append(getEinwaage()).append("' ");			
        buffer.append("VM3").append("='").append(getVM3()).append("' ");			
        buffer.append("fahrer").append("='").append(getFahrer()).append("' ");			
        buffer.append("objektNr").append("='").append(getObjektNr()).append("' ");			
        buffer.append("datumIcp").append("='").append(getDatumIcp()).append("' ");			
        buffer.append("sonderparameter").append("='").append(getSonderparameter()).append("' ");			
        buffer.append("bemerkung").append("='").append(getBemerkung()).append("' ");			
        buffer.append("an360x11").append("='").append(getAn360x11()).append("' ");			
        buffer.append("ueberschreitung").append("='").append(getUeberschreitung()).append("' ");			
        buffer.append("anzahlbeteiligte").append("='").append(getAnzahlbeteiligte()).append("' ");			
        buffer.append("uhrzeitbeginn").append("='").append(getUhrzeitbeginn()).append("' ");			
        buffer.append("uhrzeitende").append("='").append(getUhrzeitende()).append("' ");			
        buffer.append("fahrtzeit").append("='").append(getFahrtzeit()).append("' ");			
        buffer.append("bescheid").append("='").append(getBescheid()).append("' ");			
        buffer.append("kosten").append("='").append(getKosten()).append("' ");			
        buffer.append("massnahmen").append("='").append(getMassnahmen()).append("' ");			
        buffer.append("bezeichnung").append("='").append(getBezeichnung()).append("' ");			
        buffer.append("uschivorg").append("='").append(getUschivorg()).append("' ");			
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");			
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");			
        buffer.append("qbAusschliessen").append("='").append(getQbAusschliessen()).append("' ");			
        buffer.append("analysepositions").append("='").append(getAnalysepositions()).append("' ");			
        buffer.append("]");

        return buffer.toString();
    }

    /**
     * @param other
     * @return <code>true</code>, if this and other are equal,
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (!(other instanceof Probenahme)) return false;
        return (this.getId().equals(
            ((Probenahme) other).getId()));
    }

    /**
     * Calculate a unique hashCode
     * @return <code>int</code>
     */
    @Override
    public int hashCode() {
        int result = 17;
        int idValue = this.getId() == null ?
            0 : this.getId().hashCode();
        result = result * 37 + idValue;
        return result;
    }
    
    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>Probenahme</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Probenahme merge(Probenahme detachedInstance) {
        log.debug("Merging Probenahme instance " + detachedInstance);
        return (Probenahme) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Probenahme saved = Probenahme.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Probenahme with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Probenahme
     */
    private void copy(Probenahme copy) {
        this.id = copy.getId();            
        this.sachbearbeiter = copy.getSachbearbeiter();            
        this.messstelle = copy.getMessstelle();            
        this.status = copy.getStatus();            
        this.kennummer = copy.getKennummer();            
        this.art = copy.getArt();            
        this.datumDerEntnahme = copy.getDatumDerEntnahme();            
        this.zeitAnfang = copy.getZeitAnfang();            
        this.bisDatum = copy.getBisDatum();            
        this.zeitDerEntnahmen = copy.getZeitDerEntnahmen();            
        this.einwaage = copy.getEinwaage();            
        this.VM3 = copy.getVM3();            
        this.fahrer = copy.getFahrer();            
        this.objektNr = copy.getObjektNr();            
        this.datumIcp = copy.getDatumIcp();            
        this.sonderparameter = copy.getSonderparameter();            
        this.bemerkung = copy.getBemerkung();            
        this.an360x11 = copy.getAn360x11();            
        this.ueberschreitung = copy.getUeberschreitung();            
        this.anzahlbeteiligte = copy.getAnzahlbeteiligte();            
        this.uhrzeitbeginn = copy.getUhrzeitbeginn();            
        this.uhrzeitende = copy.getUhrzeitende();            
        this.fahrtzeit = copy.getFahrtzeit();            
        this.bescheid = copy.getBescheid();            
        this.kosten = copy.getKosten();            
        this.massnahmen = copy.getMassnahmen();            
        this.bezeichnung = copy.getBezeichnung();            
        this.uschivorg = copy.getUschivorg();            
        this.enabled = copy.isEnabled();            
        this.deleted = copy.isDeleted();            
        this.qbAusschliessen = copy.getQbAusschliessen();            
        this.analysepositions = copy.getAnalysepositions();            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Probenahme detachedInstance) {
        log.debug("Deleting Probenahme instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Probenahme.delete(this);
    }

    /**
     * Find an <code>Probenahme</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Probenahme</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Probenahme findById(java.lang.Integer id) {
        log.debug("Getting Probenahme instance with id: " + id);
        return (Probenahme)
            new DatabaseAccess().get(Probenahme.class, id);
    }

    /**
     * Get a list of all <code>Probenahme</code>
     * @return <code>List&lt;Probenahme&gt;</code>
     *         all <code>Probenahme</code>
     */
    public static List<Probenahme> getAll() {
        return DatabaseQuery.getAll(new Probenahme());
    }

    /* Custom code goes below here! */

}