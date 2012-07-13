package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:05 by Hibernate Tools 3.3.0.GA



/**
 * DeaGemeindeId generated by hbm2java
 */
public abstract class AbstractDeaGemeindeId  implements java.io.Serializable {


     private String gemeindeId;
     private int gemeindeVersion;

    public AbstractDeaGemeindeId() {
    }

    public AbstractDeaGemeindeId(String gemeindeId, int gemeindeVersion) {
       this.gemeindeId = gemeindeId;
       this.gemeindeVersion = gemeindeVersion;
    }
   
    public String getGemeindeId() {
        return this.gemeindeId;
    }
    
    public void setGemeindeId(String gemeindeId) {
        this.gemeindeId = gemeindeId;
    }
    public int getGemeindeVersion() {
        return this.gemeindeVersion;
    }
    
    public void setGemeindeVersion(int gemeindeVersion) {
        this.gemeindeVersion = gemeindeVersion;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaGemeindeId) ) return false;
		 DeaGemeindeId castOther = ( DeaGemeindeId ) other; 
         
		 return ( (this.getGemeindeId()==castOther.getGemeindeId()) || ( this.getGemeindeId()!=null && castOther.getGemeindeId()!=null && this.getGemeindeId().equals(castOther.getGemeindeId()) ) )
 && (this.getGemeindeVersion()==castOther.getGemeindeVersion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getGemeindeId() == null ? 0 : this.getGemeindeId().hashCode() );
         result = 37 * result + this.getGemeindeVersion();
         return result;
   }   


}


