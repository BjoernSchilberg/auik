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

package de.bielefeld.umweltamt.aui.mappings.basis;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that represents a row in the Adresse database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Adresse  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAdresse;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Wirtschaftszweig wirtschaftszweig;
    private String betranrede;
    private String betrname;
    private String betrnamezus;
    private String namebetrbeauf;
    private String vornamebetrbeauf;
    private String strasse;
    private Integer hausnr;
    private String hausnrzus;
    private String plzzs;
    private String plz;
    private String ort;
    private String telefon;
    private String telefax;
    private String email;
    private String bemerkungen;
    private Date revidatum;
    private String revihandz;
    private String kassenzeichen;
    private String betrvorname;
    private boolean enabled;
    private boolean deleted;
    private String auikWzCode;
    private boolean datenschutzAwsv;
    private boolean datenschutzEsatzung;
    private boolean datenschutzWhg;
    private Date erstellDat;
    private Set<Objekt> objektsForBetreiberid = new HashSet<Objekt>(0);
    private Set<Standort> standorts = new HashSet<Standort>(0);

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Adresse() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Adresse(
        Integer id, boolean enabled, boolean deleted) {
        this.id = id;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Adresse(
        Integer id, Wirtschaftszweig wirtschaftszweig, String betranrede, String betrname, String betrnamezus, String namebetrbeauf, String vornamebetrbeauf, String strasse, Integer hausnr, String hausnrzus, String plzzs, String plz, String ort, String telefon, String telefax, String email, String bemerkungen, Date revidatum, String revihandz, String kassenzeichen, String betrvorname, boolean enabled, boolean deleted, String auikWzCode, Date erstellDat, Set<Standort> standorts, Set<Objekt> objektsForBetreiberid) {
        this.id = id;
        this.wirtschaftszweig = wirtschaftszweig;
        this.betranrede = betranrede;
        this.betrname = betrname;
        this.betrnamezus = betrnamezus;
        this.namebetrbeauf = namebetrbeauf;
        this.vornamebetrbeauf = vornamebetrbeauf;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.hausnrzus = hausnrzus;
        this.plzzs = plzzs;
        this.plz = plz;
        this.ort = ort;
        this.telefon = telefon;
        this.telefax = telefax;
        this.email = email;
        this.bemerkungen = bemerkungen;
        this.revidatum = revidatum;
        this.revihandz = revihandz;
        this.kassenzeichen = kassenzeichen;
        this.betrvorname = betrvorname;
        this.enabled = enabled;
        this.deleted = deleted;
        this.auikWzCode = auikWzCode;
        this.erstellDat = erstellDat;
        this.standorts = standorts;
        this.objektsForBetreiberid = objektsForBetreiberid;
    }

    /* Setter and getter methods */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Wirtschaftszweig getWirtschaftszweig() {
        return this.wirtschaftszweig;
    }

    public void setWirtschaftszweig(Wirtschaftszweig wirtschaftszweig) {
        this.wirtschaftszweig = wirtschaftszweig;
    }

    public String getBetranrede() {
        return this.betranrede;
    }

    public void setBetranrede(String betranrede) {
        this.betranrede = betranrede;
    }

    public String getBetrname() {
        return this.betrname;
    }

    public void setBetrname(String betrname) {
        this.betrname = betrname;
    }

    public String getBetrnamezus() {
        return this.betrnamezus;
    }

    public void setBetrnamezus(String betrnamezus) {
        this.betrnamezus = betrnamezus;
    }

    public String getNamebetrbeauf() {
        return this.namebetrbeauf;
    }

    public void setNamebetrbeauf(String namebetrbeauf) {
        this.namebetrbeauf = namebetrbeauf;
    }

    public String getVornamebetrbeauf() {
        return this.vornamebetrbeauf;
    }

    public void setVornamebetrbeauf(String vornamebetrbeauf) {
        this.vornamebetrbeauf = vornamebetrbeauf;
    }

    public String getStrasse() {
        return this.strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public Integer getHausnr() {
        return this.hausnr;
    }

    public void setHausnr(Integer hausnr) {
        this.hausnr = hausnr;
    }

    public String getHausnrzus() {
        return this.hausnrzus;
    }

    public void setHausnrzus(String hausnrzus) {
        this.hausnrzus = hausnrzus;
    }

    public String getPlzzs() {
        return this.plzzs;
    }

    public void setPlzzs(String plzzs) {
        this.plzzs = plzzs;
    }

    public String getPlz() {
        return this.plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return this.ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelefax() {
        return this.telefax;
    }

    public void setTelefax(String telefax) {
        this.telefax = telefax;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }

    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public Date getRevidatum() {
        return this.revidatum;
    }

    public void setRevidatum(Date revidatum) {
        this.revidatum = revidatum;
    }

    public String getRevihandz() {
        return this.revihandz;
    }

    public void setRevihandz(String revihandz) {
        this.revihandz = revihandz;
    }

    public String getKassenzeichen() {
        return this.kassenzeichen;
    }

    public void setKassenzeichen(String kassenzeichen) {
        this.kassenzeichen = kassenzeichen;
    }

    public String getBetrvorname() {
        return this.betrvorname;
    }

    public void setBetrvorname(String betrvorname) {
        this.betrvorname = betrvorname;
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

    public String getAuikWzCode() {
        return this.auikWzCode;
    }

    public void setAuikWzCode(String auikWzCode) {
        this.auikWzCode = auikWzCode;
    }

    public Boolean getDatenschutzAwsv() {
		return datenschutzAwsv;
	}

	public void setDatenschutzAwsv(boolean datenschutzAwsv) {
		this.datenschutzAwsv = datenschutzAwsv;
	}

	public Boolean getDatenschutzEsatzung() {
		return datenschutzEsatzung;
	}

	public void setDatenschutzEsatzung(boolean datenschutzEsatzung) {
		this.datenschutzEsatzung = datenschutzEsatzung;
	}

	public Boolean getDatenschutzWhg() {
		return datenschutzWhg;
	}

	public void setDatenschutzWhg(boolean datenschutzWhg) {
		this.datenschutzWhg = datenschutzWhg;
	}

	public Date getErstellDat() {
        return this.erstellDat;
    }

    public void setErstellDat(Date erstellDat) {
        this.erstellDat = erstellDat;
    }

    public Set<Objekt> getObjektsForBetreiberid() {
        return this.objektsForBetreiberid;
    }

    public void setObjektsForBetreiberid(Set<Objekt> objektsForBetreiberid) {
        this.objektsForBetreiberid = objektsForBetreiberid;
    }

    public Set<Standort> getStandorts() {
		return standorts;
	}

	public void setStandorts(Set<Standort> standorts) {
		this.standorts = standorts;
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
        buffer.append("wirtschaftszweig").append("='").append(getWirtschaftszweig()).append("' ");			
        buffer.append("betranrede").append("='").append(getBetranrede()).append("' ");			
        buffer.append("betrname").append("='").append(getBetrname()).append("' ");			
        buffer.append("betrnamezus").append("='").append(getBetrnamezus()).append("' ");			
        buffer.append("namebetrbeauf").append("='").append(getNamebetrbeauf()).append("' ");			
        buffer.append("vornamebetrbeauf").append("='").append(getVornamebetrbeauf()).append("' ");			
        buffer.append("strasse").append("='").append(getStrasse()).append("' ");			
        buffer.append("hausnr").append("='").append(getHausnr()).append("' ");			
        buffer.append("hausnrzus").append("='").append(getHausnrzus()).append("' ");			
        buffer.append("plzzs").append("='").append(getPlzzs()).append("' ");			
        buffer.append("plz").append("='").append(getPlz()).append("' ");			
        buffer.append("ort").append("='").append(getOrt()).append("' ");			
        buffer.append("telefon").append("='").append(getTelefon()).append("' ");			
        buffer.append("telefax").append("='").append(getTelefax()).append("' ");			
        buffer.append("email").append("='").append(getEmail()).append("' ");			
        buffer.append("bemerkungen").append("='").append(getBemerkungen()).append("' ");			
        buffer.append("revidatum").append("='").append(getRevidatum()).append("' ");			
        buffer.append("revihandz").append("='").append(getRevihandz()).append("' ");			
        buffer.append("kassenzeichen").append("='").append(getKassenzeichen()).append("' ");			
        buffer.append("betrvorname").append("='").append(getBetrvorname()).append("' ");			
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");			
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");			
        buffer.append("auikWzCode").append("='").append(getAuikWzCode()).append("' ");			
        buffer.append("erstellDat").append("='").append(getErstellDat()).append("' ");			
        buffer.append("Standorts").append("='").append(getStandorts()).append("' ");			
         buffer.append("objektsForBetreiberid").append("='").append(getObjektsForBetreiberid()).append("' ");			
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
        if (!(other instanceof Adresse)) return false;
        return (this.getId().equals(
            ((Adresse) other).getId()));
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
     * @return <code>Adresse</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Adresse merge(Adresse detachedInstance) {
        log.debug("Merging Adresse instance " + detachedInstance);
        return (Adresse) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Adresse saved = Adresse.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Adresse with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Adresse
     */
    private void copy(Adresse copy) {
        this.id = copy.getId();                    
        this.wirtschaftszweig = copy.getWirtschaftszweig();            
        this.betranrede = copy.getBetranrede();            
        this.betrname = copy.getBetrname();            
        this.betrnamezus = copy.getBetrnamezus();            
        this.namebetrbeauf = copy.getNamebetrbeauf();            
        this.vornamebetrbeauf = copy.getVornamebetrbeauf();            
        this.strasse = copy.getStrasse();            
        this.hausnr = copy.getHausnr();            
        this.hausnrzus = copy.getHausnrzus();            
        this.plzzs = copy.getPlzzs();            
        this.plz = copy.getPlz();            
        this.ort = copy.getOrt();            
        this.telefon = copy.getTelefon();            
        this.telefax = copy.getTelefax();            
        this.email = copy.getEmail();            
        this.bemerkungen = copy.getBemerkungen();            
        this.revidatum = copy.getRevidatum();            
        this.revihandz = copy.getRevihandz();            
        this.kassenzeichen = copy.getKassenzeichen();            
        this.betrvorname = copy.getBetrvorname();            
        this.enabled = copy.isEnabled();            
        this.deleted = copy.isDeleted();            
        this.auikWzCode = copy.getAuikWzCode();            
        this.erstellDat = copy.getErstellDat();            
        this.standorts = copy.getStandorts();            
        this.objektsForBetreiberid = copy.getObjektsForBetreiberid();            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Adresse detachedInstance) {
        log.debug("Deleting Adresse instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Adresse.delete(this);
    }

    /**
     * Find an <code>Adresse</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Adresse</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Adresse findById(java.lang.Integer id) {
        log.debug("Getting Adresse instance with id: " + id);
        return (Adresse)
            new DatabaseAccess().get(Adresse.class, id);
    }

    /**
     * Get a list of all <code>Adresse</code>
     * @return <code>List&lt;Adresse&gt;</code>
     *         all <code>Adresse</code>
     */
    public static List<Adresse> getAll() {
        return DatabaseQuery.getAll(new Adresse());
    }

	public Standort getStandort() {
		// TODO Auto-generated method stub
		return null;
	}

    /* Custom code goes below here! */

}