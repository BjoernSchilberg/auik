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


package de.bielefeld.umweltamt.aui.mappings.basis;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.mappings.basis.Gemarkung;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the BasisObjekt database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Standort  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forObjekt;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Adresse adresse;
    private Lage lage;
    private boolean enabled;
    private boolean deleted;
    private Set<Objekt> objektsForStandortid = new HashSet<Objekt>(0);

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Standort() {
        // This place is intentionally left blank.
    }

    /** Full constructor */
    public Standort(
    	Adresse adresse, Lage lage, boolean enabled, boolean deleted, Set<Objekt> objektsForStandortid) {
    	this.adresse = adresse;
        this.lage = lage;
        this.enabled = enabled;
        this.deleted = deleted;
        this.objektsForStandortid = objektsForStandortid;
    }

    /* Setter and getter methods */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adresse getAdresse() {
        return this.adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Lage getLage() {
        return this.lage;
    }

    public void setLage(Lage lage) {
        this.lage = lage;
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

    public Set<Objekt> getObjektsForStandortid() {
        return this.objektsForStandortid;
    }

    public void setObjektsForStandortid(Set<Objekt> objektsForStandortid) {
        this.objektsForStandortid = objektsForStandortid;
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
        buffer.append("Adresse").append("='").append(getAdresse()).append("' ");			
        buffer.append("Lage").append("='").append(getLage()).append("' ");			
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");			
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");	
        buffer.append("objektsForStandortid").append("='").append(getObjektsForStandortid()).append("' ");		
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
        if (!(other instanceof Standort)) return false;
        return (this.getId().equals(
            ((Standort) other).getId()));
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
     * @return <code>BasisObjekt</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Standort merge(Standort detachedInstance) {
        log.debug("Merging Objekt instance " + detachedInstance);
        return (Standort) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Standort saved = Standort.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this BasisObjekt with its new values.<br>
     * This is meant to be used after merging!
     * @param copy BasisObjekt
     */
    private void copy(Standort copy) {
        this.adresse = copy.getAdresse();            
        this.lage = copy.getLage();          
        this.enabled = copy.isEnabled();            
        this.deleted = copy.isDeleted();    
        this.objektsForStandortid = copy.getObjektsForStandortid();       
            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Standort detachedInstance) {
        log.debug("Deleting Objekt instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Standort.delete(this);
    }

    /**
     * Find an <code>BasisObjekt</code> instance by its primary key
     * @param id the primary key value
     * @return <code>BasisObjekt</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Standort findById(java.lang.Integer id) {
        log.debug("Getting Objekt instance with id: " + id);
        return (Standort)
            new DatabaseAccess().get(Standort.class, id);
    }

    /**
     * Get a list of all <code>BasisObjekt</code>
     * @return <code>List&lt;BasisObjekt&gt;</code>
     *         all <code>BasisObjekt</code>
     */
    public static List<Standort> getAll() {
        return DatabaseQuery.getAll(new Standort());
    }
    
    /* Setter and getter for lage and adresse fields*/
    //Getter

    public String getStrasse(){
        return adresse.getStrasse();
    }

    public Integer getHausnr(){
        return adresse.getHausnr();
    }

    public String getHausnrzus(){
        return adresse.getHausnrzus();
    }

    public String getPlz(){
        return adresse.getPlz();
    }

    public String getOrt(){
        return adresse.getOrt();
    }

    public Gemarkung getGemarkung(){
        return lage.getGemarkung();
    }

    public String getEntgebid(){
        return lage.getEntgebid();
    }

    public Float getE32(){
        return lage.getE32();
    }

    public Float getN32(){
        return lage.getN32();
    }

    public Standortgghwsg getStandortgghwsg(){
        return lage.getStandortgghwsg();
    }

    public Wassereinzugsgebiet getWassereinzugsgebiet(){
        return lage.getWassereinzugsgebiet();
    }

    public String getFlur(){
        return lage.getFlur();
    }

    public String getFlurstueck(){
        return lage.getFlurstueck();
    }

    public String getRevihandz(){
        if(lage.getRevihandz() != null){
            return lage.getRevihandz();
        }
        else{
            return adresse.getRevihandz();
        }
    }

    public Date getRevidatum(){
        if(lage.getRevidatum() != null){
            return lage.getRevidatum();
        }
        else{
            return adresse.getRevidatum();
        }
    }

    public String getSachbe33rav(){
        return lage.getSachbe33rav();
    }

    public Integer getWassermenge(){
        return lage.getWassermenge();
    }    

    //Setter

    public void setHausnr(Integer hausnr){
        this.adresse.setHausnr(hausnr);
    }

    public void setHausnrzus(String hausnrzus){
        this.adresse.setHausnrzus(hausnrzus);
    }

    public void setStrasse(String strasse){
        this.adresse.setStrasse(strasse);
    }

    public void setPlz(String plz){
        this.adresse.setPlz(plz);
    }

    public void setOrt(String ort){
        this.adresse.setOrt(ort);
    }

    public void setStandortgghwsg(Standortgghwsg vaws){
        this.lage.setStandortgghwsg(vaws);
    }

    public void setEntgebid(String entgebid){
        this.lage.setEntgebid(entgebid);
    }

    public void setWassereinzugsgebiet(Wassereinzugsgebiet geb){
        this.lage.setWassereinzugsgebiet(geb);
    }

    public void setGemarkung(Gemarkung gemarkung){
        this.lage.setGemarkung(gemarkung);
    }

    public void setFlur(String flur){
        this.lage.setFlur(flur);
    }

    public void setFlurstueck(String flurstueck){
        this.lage.setFlurstueck(flurstueck);
    }

    public void setE32(Float e32){
        this.lage.setE32(e32);
    }

    public void setN32(Float n32){
        this.lage.setN32(n32);
    }

    public void setRevidatum(Date date){
        this.lage.setRevidatum(date);
        this.adresse.setRevidatum(date);
    }

    public void setRevihandz(String handz){
        this.lage.setRevihandz(handz);
        this.adresse.setRevihandz(handz);
    }

    public void setSachbe33rav(String sachbe33rav){
    	lage.setSachbe33rav(sachbe33rav);
    }

    public void setWassermenge(Integer wassermenge){
    	lage.setWassermenge(wassermenge);
    }

    /* Custom code goes below here! */
	public Integer getObjektid(){
		return getId();
	}

    public static Standort findByLageId(Integer id){
        List<Standort> all = Standort.getAll();
        for(Standort i : all){
            log.debug("Comparing " + i.getLage().getId() + " " + id);
            if(i.getLage().getId().equals(id)){
                log.debug("Returning Objekt: " + i.getId());
                return (Standort) new DatabaseAccess().get(Standort.class, i.getId());
            }
        }
        return null;
    }
    
    public static Standort findByAdresseId(Integer id){

         return (Standort) new DatabaseAccess().get(Standort.class, id);
                    
    }


	public static Standort findByAdresse(Adresse adresse) {
		Standort standort = new Standort();
		Integer id = adresse.getId();
		List standorte = HibernateSessionFactory
				.currentSession()
				.createQuery(
						"from Standort where adresseid= " + id)
				.list();
		if (standorte.size() != 0) {
			standort = (Standort) standorte
					.get(0);
			return standort;
		}
		return null;
	}
}