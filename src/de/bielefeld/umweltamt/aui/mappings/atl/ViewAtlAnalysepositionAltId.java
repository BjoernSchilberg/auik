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

import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import java.util.Date;

/**
 * A class that represents a row in the ViewAtlAnalysepositionAltId database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class ViewAtlAnalysepositionAltId  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forViewAtlAnalysepositionAltId;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private String grkl;
    private Float wert;
    private String analyseVon;
    private String bericht;
    private Double normwert;
    private Integer einheitenId;
    private String parameterId;
    private Integer probenahmeId;
    private Boolean enabled;
    private Boolean deleted;
    private Date datumDerEntnahme;
    private Integer probepktId;

    /** Default constructor */
    public ViewAtlAnalysepositionAltId() {
        // This place is intentionally left blank.
    }


    /** Full constructor */
    public ViewAtlAnalysepositionAltId(
        Integer id, String grkl, Float wert, String analyseVon, String bericht, Double normwert, Integer einheitenId, String parameterId, Integer probenahmeId, Boolean enabled, Boolean deleted, Date datumDerEntnahme, Integer probepktId) {
        this.id = id;
        this.grkl = grkl;
        this.wert = wert;
        this.analyseVon = analyseVon;
        this.bericht = bericht;
        this.normwert = normwert;
        this.einheitenId = einheitenId;
        this.parameterId = parameterId;
        this.probenahmeId = probenahmeId;
        this.enabled = enabled;
        this.deleted = deleted;
        this.datumDerEntnahme = datumDerEntnahme;
        this.probepktId = probepktId;
    }

    /* Setter and getter methods */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrkl() {
        return this.grkl;
    }

    public void setGrkl(String grkl) {
        this.grkl = grkl;
    }

    public Float getWert() {
        return this.wert;
    }

    public void setWert(Float wert) {
        this.wert = wert;
    }

    public String getAnalyseVon() {
        return this.analyseVon;
    }

    public void setAnalyseVon(String analyseVon) {
        this.analyseVon = analyseVon;
    }

    public String getBericht() {
        return this.bericht;
    }

    public void setBericht(String bericht) {
        this.bericht = bericht;
    }

    public Double getNormwert() {
        return this.normwert;
    }

    public void setNormwert(Double normwert) {
        this.normwert = normwert;
    }

    public Integer getEinheitenId() {
        return this.einheitenId;
    }

    public void setEinheitenId(Integer einheitenId) {
        this.einheitenId = einheitenId;
    }

    public String getParameterId() {
        return this.parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    public Integer getProbenahmeId() {
        return this.probenahmeId;
    }

    public void setProbenahmeId(Integer probenahmeId) {
        this.probenahmeId = probenahmeId;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getDatumDerEntnahme() {
        return this.datumDerEntnahme;
    }

    public void setDatumDerEntnahme(Date datumDerEntnahme) {
        this.datumDerEntnahme = datumDerEntnahme;
    }

    public Integer getProbepktId() {
        return this.probepktId;
    }

    public void setProbepktId(Integer probepktId) {
        this.probepktId = probepktId;
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
        buffer.append("grkl").append("='").append(getGrkl()).append("' ");			
        buffer.append("wert").append("='").append(getWert()).append("' ");			
        buffer.append("analyseVon").append("='").append(getAnalyseVon()).append("' ");			
        buffer.append("bericht").append("='").append(getBericht()).append("' ");			
        buffer.append("normwert").append("='").append(getNormwert()).append("' ");			
        buffer.append("einheitenId").append("='").append(getEinheitenId()).append("' ");			
        buffer.append("parameterId").append("='").append(getParameterId()).append("' ");			
        buffer.append("probenahmeId").append("='").append(getProbenahmeId()).append("' ");			
        buffer.append("enabled").append("='").append(getEnabled()).append("' ");			
        buffer.append("deleted").append("='").append(getDeleted()).append("' ");			
        buffer.append("datumDerEntnahme").append("='").append(getDatumDerEntnahme()).append("' ");			
        buffer.append("probepktId").append("='").append(getProbepktId()).append("' ");			
        buffer.append("]");

        return buffer.toString();
    }

    /* Custom code goes below here! */

}