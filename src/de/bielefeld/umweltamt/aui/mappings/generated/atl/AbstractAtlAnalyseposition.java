package de.bielefeld.umweltamt.aui.mappings.generated.atl;
// Generated 13.07.2012 16:41:05 by Hibernate Tools 3.3.0.GA



/**
 * AtlAnalyseposition generated by hbm2java
 */
public abstract class AbstractAtlAnalyseposition  implements java.io.Serializable {


     private int id;
     private AtlParameter atlParameter;
     private AtlProbenahmen atlProbenahmen;
     private AtlEinheiten atlEinheiten;
     private String grkl;
     private float wert;
     private String analyseVon;
     private String bericht;
     private Double normwert;
     private boolean enabled;
     private boolean deleted;

    public AbstractAtlAnalyseposition() {
    }

	
    public AbstractAtlAnalyseposition(int id, AtlParameter atlParameter, AtlProbenahmen atlProbenahmen, float wert, boolean enabled, boolean deleted) {
        this.id = id;
        this.atlParameter = atlParameter;
        this.atlProbenahmen = atlProbenahmen;
        this.wert = wert;
        this.enabled = enabled;
        this.deleted = deleted;
    }
    public AbstractAtlAnalyseposition(int id, AtlParameter atlParameter, AtlProbenahmen atlProbenahmen, AtlEinheiten atlEinheiten, String grkl, float wert, String analyseVon, String bericht, Double normwert, boolean enabled, boolean deleted) {
       this.id = id;
       this.atlParameter = atlParameter;
       this.atlProbenahmen = atlProbenahmen;
       this.atlEinheiten = atlEinheiten;
       this.grkl = grkl;
       this.wert = wert;
       this.analyseVon = analyseVon;
       this.bericht = bericht;
       this.normwert = normwert;
       this.enabled = enabled;
       this.deleted = deleted;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public AtlParameter getAtlParameter() {
        return this.atlParameter;
    }
    
    public void setAtlParameter(AtlParameter atlParameter) {
        this.atlParameter = atlParameter;
    }
    public AtlProbenahmen getAtlProbenahmen() {
        return this.atlProbenahmen;
    }
    
    public void setAtlProbenahmen(AtlProbenahmen atlProbenahmen) {
        this.atlProbenahmen = atlProbenahmen;
    }
    public AtlEinheiten getAtlEinheiten() {
        return this.atlEinheiten;
    }
    
    public void setAtlEinheiten(AtlEinheiten atlEinheiten) {
        this.atlEinheiten = atlEinheiten;
    }
    public String getGrkl() {
        return this.grkl;
    }
    
    public void setGrkl(String grkl) {
        this.grkl = grkl;
    }
    public float getWert() {
        return this.wert;
    }
    
    public void setWert(float wert) {
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




}


