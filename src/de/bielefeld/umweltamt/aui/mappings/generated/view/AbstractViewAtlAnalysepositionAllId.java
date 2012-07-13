package de.bielefeld.umweltamt.aui.mappings.generated.view;
// Generated 13.07.2012 16:41:05 by Hibernate Tools 3.3.0.GA



/**
 * ViewAtlAnalysepositionAllId generated by hbm2java
 */
public abstract class AbstractViewAtlAnalysepositionAllId  implements java.io.Serializable {


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

    public AbstractViewAtlAnalysepositionAllId() {
    }

    public AbstractViewAtlAnalysepositionAllId(Integer id, String grkl, Float wert, String analyseVon, String bericht, Double normwert, Integer einheitenId, String parameterId, Integer probenahmeId, Boolean enabled, Boolean deleted) {
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
    }
   
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


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ViewAtlAnalysepositionAllId) ) return false;
		 ViewAtlAnalysepositionAllId castOther = ( ViewAtlAnalysepositionAllId ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getGrkl()==castOther.getGrkl()) || ( this.getGrkl()!=null && castOther.getGrkl()!=null && this.getGrkl().equals(castOther.getGrkl()) ) )
 && ( (this.getWert()==castOther.getWert()) || ( this.getWert()!=null && castOther.getWert()!=null && this.getWert().equals(castOther.getWert()) ) )
 && ( (this.getAnalyseVon()==castOther.getAnalyseVon()) || ( this.getAnalyseVon()!=null && castOther.getAnalyseVon()!=null && this.getAnalyseVon().equals(castOther.getAnalyseVon()) ) )
 && ( (this.getBericht()==castOther.getBericht()) || ( this.getBericht()!=null && castOther.getBericht()!=null && this.getBericht().equals(castOther.getBericht()) ) )
 && ( (this.getNormwert()==castOther.getNormwert()) || ( this.getNormwert()!=null && castOther.getNormwert()!=null && this.getNormwert().equals(castOther.getNormwert()) ) )
 && ( (this.getEinheitenId()==castOther.getEinheitenId()) || ( this.getEinheitenId()!=null && castOther.getEinheitenId()!=null && this.getEinheitenId().equals(castOther.getEinheitenId()) ) )
 && ( (this.getParameterId()==castOther.getParameterId()) || ( this.getParameterId()!=null && castOther.getParameterId()!=null && this.getParameterId().equals(castOther.getParameterId()) ) )
 && ( (this.getProbenahmeId()==castOther.getProbenahmeId()) || ( this.getProbenahmeId()!=null && castOther.getProbenahmeId()!=null && this.getProbenahmeId().equals(castOther.getProbenahmeId()) ) )
 && ( (this.getEnabled()==castOther.getEnabled()) || ( this.getEnabled()!=null && castOther.getEnabled()!=null && this.getEnabled().equals(castOther.getEnabled()) ) )
 && ( (this.getDeleted()==castOther.getDeleted()) || ( this.getDeleted()!=null && castOther.getDeleted()!=null && this.getDeleted().equals(castOther.getDeleted()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getGrkl() == null ? 0 : this.getGrkl().hashCode() );
         result = 37 * result + ( getWert() == null ? 0 : this.getWert().hashCode() );
         result = 37 * result + ( getAnalyseVon() == null ? 0 : this.getAnalyseVon().hashCode() );
         result = 37 * result + ( getBericht() == null ? 0 : this.getBericht().hashCode() );
         result = 37 * result + ( getNormwert() == null ? 0 : this.getNormwert().hashCode() );
         result = 37 * result + ( getEinheitenId() == null ? 0 : this.getEinheitenId().hashCode() );
         result = 37 * result + ( getParameterId() == null ? 0 : this.getParameterId().hashCode() );
         result = 37 * result + ( getProbenahmeId() == null ? 0 : this.getProbenahmeId().hashCode() );
         result = 37 * result + ( getEnabled() == null ? 0 : this.getEnabled().hashCode() );
         result = 37 * result + ( getDeleted() == null ? 0 : this.getDeleted().hashCode() );
         return result;
   }   


}


