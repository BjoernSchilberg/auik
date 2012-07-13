package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:05 by Hibernate Tools 3.3.0.GA


import java.util.Date;

/**
 * InkaBetriebId generated by hbm2java
 */
public abstract class AbstractInkaBetriebId  implements java.io.Serializable {


     private Integer betriebNr;
     private Integer betriebVer;
     private Date gueltigVon;
     private Date gueltigBis;
     private Date aenderungsDatum;
     private Date erfassungsDatum;
     private Integer historienNr;
     private Boolean istAktuellJn;
     private Integer adresseStandNr;
     private Integer adresseStandVer;
     private Integer adresseEinleitNr;
     private Integer adresseEinleitVer;
     private Integer adresseAnsprNr;
     private Integer adresseAnsprVer;
     private String gemeindekennzahl;
     private Integer gemeindeVer;

    public AbstractInkaBetriebId() {
    }

    public AbstractInkaBetriebId(Integer betriebNr, Integer betriebVer, Date gueltigVon, Date gueltigBis, Date aenderungsDatum, Date erfassungsDatum, Integer historienNr, Boolean istAktuellJn, Integer adresseStandNr, Integer adresseStandVer, Integer adresseEinleitNr, Integer adresseEinleitVer, Integer adresseAnsprNr, Integer adresseAnsprVer, String gemeindekennzahl, Integer gemeindeVer) {
       this.betriebNr = betriebNr;
       this.betriebVer = betriebVer;
       this.gueltigVon = gueltigVon;
       this.gueltigBis = gueltigBis;
       this.aenderungsDatum = aenderungsDatum;
       this.erfassungsDatum = erfassungsDatum;
       this.historienNr = historienNr;
       this.istAktuellJn = istAktuellJn;
       this.adresseStandNr = adresseStandNr;
       this.adresseStandVer = adresseStandVer;
       this.adresseEinleitNr = adresseEinleitNr;
       this.adresseEinleitVer = adresseEinleitVer;
       this.adresseAnsprNr = adresseAnsprNr;
       this.adresseAnsprVer = adresseAnsprVer;
       this.gemeindekennzahl = gemeindekennzahl;
       this.gemeindeVer = gemeindeVer;
    }
   
    public Integer getBetriebNr() {
        return this.betriebNr;
    }
    
    public void setBetriebNr(Integer betriebNr) {
        this.betriebNr = betriebNr;
    }
    public Integer getBetriebVer() {
        return this.betriebVer;
    }
    
    public void setBetriebVer(Integer betriebVer) {
        this.betriebVer = betriebVer;
    }
    public Date getGueltigVon() {
        return this.gueltigVon;
    }
    
    public void setGueltigVon(Date gueltigVon) {
        this.gueltigVon = gueltigVon;
    }
    public Date getGueltigBis() {
        return this.gueltigBis;
    }
    
    public void setGueltigBis(Date gueltigBis) {
        this.gueltigBis = gueltigBis;
    }
    public Date getAenderungsDatum() {
        return this.aenderungsDatum;
    }
    
    public void setAenderungsDatum(Date aenderungsDatum) {
        this.aenderungsDatum = aenderungsDatum;
    }
    public Date getErfassungsDatum() {
        return this.erfassungsDatum;
    }
    
    public void setErfassungsDatum(Date erfassungsDatum) {
        this.erfassungsDatum = erfassungsDatum;
    }
    public Integer getHistorienNr() {
        return this.historienNr;
    }
    
    public void setHistorienNr(Integer historienNr) {
        this.historienNr = historienNr;
    }
    public Boolean getIstAktuellJn() {
        return this.istAktuellJn;
    }
    
    public void setIstAktuellJn(Boolean istAktuellJn) {
        this.istAktuellJn = istAktuellJn;
    }
    public Integer getAdresseStandNr() {
        return this.adresseStandNr;
    }
    
    public void setAdresseStandNr(Integer adresseStandNr) {
        this.adresseStandNr = adresseStandNr;
    }
    public Integer getAdresseStandVer() {
        return this.adresseStandVer;
    }
    
    public void setAdresseStandVer(Integer adresseStandVer) {
        this.adresseStandVer = adresseStandVer;
    }
    public Integer getAdresseEinleitNr() {
        return this.adresseEinleitNr;
    }
    
    public void setAdresseEinleitNr(Integer adresseEinleitNr) {
        this.adresseEinleitNr = adresseEinleitNr;
    }
    public Integer getAdresseEinleitVer() {
        return this.adresseEinleitVer;
    }
    
    public void setAdresseEinleitVer(Integer adresseEinleitVer) {
        this.adresseEinleitVer = adresseEinleitVer;
    }
    public Integer getAdresseAnsprNr() {
        return this.adresseAnsprNr;
    }
    
    public void setAdresseAnsprNr(Integer adresseAnsprNr) {
        this.adresseAnsprNr = adresseAnsprNr;
    }
    public Integer getAdresseAnsprVer() {
        return this.adresseAnsprVer;
    }
    
    public void setAdresseAnsprVer(Integer adresseAnsprVer) {
        this.adresseAnsprVer = adresseAnsprVer;
    }
    public String getGemeindekennzahl() {
        return this.gemeindekennzahl;
    }
    
    public void setGemeindekennzahl(String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }
    public Integer getGemeindeVer() {
        return this.gemeindeVer;
    }
    
    public void setGemeindeVer(Integer gemeindeVer) {
        this.gemeindeVer = gemeindeVer;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InkaBetriebId) ) return false;
		 InkaBetriebId castOther = ( InkaBetriebId ) other; 
         
		 return ( (this.getBetriebNr()==castOther.getBetriebNr()) || ( this.getBetriebNr()!=null && castOther.getBetriebNr()!=null && this.getBetriebNr().equals(castOther.getBetriebNr()) ) )
 && ( (this.getBetriebVer()==castOther.getBetriebVer()) || ( this.getBetriebVer()!=null && castOther.getBetriebVer()!=null && this.getBetriebVer().equals(castOther.getBetriebVer()) ) )
 && ( (this.getGueltigVon()==castOther.getGueltigVon()) || ( this.getGueltigVon()!=null && castOther.getGueltigVon()!=null && this.getGueltigVon().equals(castOther.getGueltigVon()) ) )
 && ( (this.getGueltigBis()==castOther.getGueltigBis()) || ( this.getGueltigBis()!=null && castOther.getGueltigBis()!=null && this.getGueltigBis().equals(castOther.getGueltigBis()) ) )
 && ( (this.getAenderungsDatum()==castOther.getAenderungsDatum()) || ( this.getAenderungsDatum()!=null && castOther.getAenderungsDatum()!=null && this.getAenderungsDatum().equals(castOther.getAenderungsDatum()) ) )
 && ( (this.getErfassungsDatum()==castOther.getErfassungsDatum()) || ( this.getErfassungsDatum()!=null && castOther.getErfassungsDatum()!=null && this.getErfassungsDatum().equals(castOther.getErfassungsDatum()) ) )
 && ( (this.getHistorienNr()==castOther.getHistorienNr()) || ( this.getHistorienNr()!=null && castOther.getHistorienNr()!=null && this.getHistorienNr().equals(castOther.getHistorienNr()) ) )
 && ( (this.getIstAktuellJn()==castOther.getIstAktuellJn()) || ( this.getIstAktuellJn()!=null && castOther.getIstAktuellJn()!=null && this.getIstAktuellJn().equals(castOther.getIstAktuellJn()) ) )
 && ( (this.getAdresseStandNr()==castOther.getAdresseStandNr()) || ( this.getAdresseStandNr()!=null && castOther.getAdresseStandNr()!=null && this.getAdresseStandNr().equals(castOther.getAdresseStandNr()) ) )
 && ( (this.getAdresseStandVer()==castOther.getAdresseStandVer()) || ( this.getAdresseStandVer()!=null && castOther.getAdresseStandVer()!=null && this.getAdresseStandVer().equals(castOther.getAdresseStandVer()) ) )
 && ( (this.getAdresseEinleitNr()==castOther.getAdresseEinleitNr()) || ( this.getAdresseEinleitNr()!=null && castOther.getAdresseEinleitNr()!=null && this.getAdresseEinleitNr().equals(castOther.getAdresseEinleitNr()) ) )
 && ( (this.getAdresseEinleitVer()==castOther.getAdresseEinleitVer()) || ( this.getAdresseEinleitVer()!=null && castOther.getAdresseEinleitVer()!=null && this.getAdresseEinleitVer().equals(castOther.getAdresseEinleitVer()) ) )
 && ( (this.getAdresseAnsprNr()==castOther.getAdresseAnsprNr()) || ( this.getAdresseAnsprNr()!=null && castOther.getAdresseAnsprNr()!=null && this.getAdresseAnsprNr().equals(castOther.getAdresseAnsprNr()) ) )
 && ( (this.getAdresseAnsprVer()==castOther.getAdresseAnsprVer()) || ( this.getAdresseAnsprVer()!=null && castOther.getAdresseAnsprVer()!=null && this.getAdresseAnsprVer().equals(castOther.getAdresseAnsprVer()) ) )
 && ( (this.getGemeindekennzahl()==castOther.getGemeindekennzahl()) || ( this.getGemeindekennzahl()!=null && castOther.getGemeindekennzahl()!=null && this.getGemeindekennzahl().equals(castOther.getGemeindekennzahl()) ) )
 && ( (this.getGemeindeVer()==castOther.getGemeindeVer()) || ( this.getGemeindeVer()!=null && castOther.getGemeindeVer()!=null && this.getGemeindeVer().equals(castOther.getGemeindeVer()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBetriebNr() == null ? 0 : this.getBetriebNr().hashCode() );
         result = 37 * result + ( getBetriebVer() == null ? 0 : this.getBetriebVer().hashCode() );
         result = 37 * result + ( getGueltigVon() == null ? 0 : this.getGueltigVon().hashCode() );
         result = 37 * result + ( getGueltigBis() == null ? 0 : this.getGueltigBis().hashCode() );
         result = 37 * result + ( getAenderungsDatum() == null ? 0 : this.getAenderungsDatum().hashCode() );
         result = 37 * result + ( getErfassungsDatum() == null ? 0 : this.getErfassungsDatum().hashCode() );
         result = 37 * result + ( getHistorienNr() == null ? 0 : this.getHistorienNr().hashCode() );
         result = 37 * result + ( getIstAktuellJn() == null ? 0 : this.getIstAktuellJn().hashCode() );
         result = 37 * result + ( getAdresseStandNr() == null ? 0 : this.getAdresseStandNr().hashCode() );
         result = 37 * result + ( getAdresseStandVer() == null ? 0 : this.getAdresseStandVer().hashCode() );
         result = 37 * result + ( getAdresseEinleitNr() == null ? 0 : this.getAdresseEinleitNr().hashCode() );
         result = 37 * result + ( getAdresseEinleitVer() == null ? 0 : this.getAdresseEinleitVer().hashCode() );
         result = 37 * result + ( getAdresseAnsprNr() == null ? 0 : this.getAdresseAnsprNr().hashCode() );
         result = 37 * result + ( getAdresseAnsprVer() == null ? 0 : this.getAdresseAnsprVer().hashCode() );
         result = 37 * result + ( getGemeindekennzahl() == null ? 0 : this.getGemeindekennzahl().hashCode() );
         result = 37 * result + ( getGemeindeVer() == null ? 0 : this.getGemeindeVer().hashCode() );
         return result;
   }   


}


