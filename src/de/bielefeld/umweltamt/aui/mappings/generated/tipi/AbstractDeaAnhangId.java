package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:05 by Hibernate Tools 3.3.0.GA



/**
 * DeaAnhangId generated by hbm2java
 */
public abstract class AbstractDeaAnhangId  implements java.io.Serializable {


     private String anhId;
     private int anhVersion;

    public AbstractDeaAnhangId() {
    }

    public AbstractDeaAnhangId(String anhId, int anhVersion) {
       this.anhId = anhId;
       this.anhVersion = anhVersion;
    }
   
    public String getAnhId() {
        return this.anhId;
    }
    
    public void setAnhId(String anhId) {
        this.anhId = anhId;
    }
    public int getAnhVersion() {
        return this.anhVersion;
    }
    
    public void setAnhVersion(int anhVersion) {
        this.anhVersion = anhVersion;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaAnhangId) ) return false;
		 DeaAnhangId castOther = ( DeaAnhangId ) other; 
         
		 return ( (this.getAnhId()==castOther.getAnhId()) || ( this.getAnhId()!=null && castOther.getAnhId()!=null && this.getAnhId().equals(castOther.getAnhId()) ) )
 && (this.getAnhVersion()==castOther.getAnhVersion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAnhId() == null ? 0 : this.getAnhId().hashCode() );
         result = 37 * result + this.getAnhVersion();
         return result;
   }   


}


