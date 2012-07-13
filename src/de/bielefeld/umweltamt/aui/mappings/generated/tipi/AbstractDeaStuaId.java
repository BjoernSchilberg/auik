package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:05 by Hibernate Tools 3.3.0.GA



/**
 * DeaStuaId generated by hbm2java
 */
public abstract class AbstractDeaStuaId  implements java.io.Serializable {


     private int stuaNr;
     private int stuaVersion;

    public AbstractDeaStuaId() {
    }

    public AbstractDeaStuaId(int stuaNr, int stuaVersion) {
       this.stuaNr = stuaNr;
       this.stuaVersion = stuaVersion;
    }
   
    public int getStuaNr() {
        return this.stuaNr;
    }
    
    public void setStuaNr(int stuaNr) {
        this.stuaNr = stuaNr;
    }
    public int getStuaVersion() {
        return this.stuaVersion;
    }
    
    public void setStuaVersion(int stuaVersion) {
        this.stuaVersion = stuaVersion;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaStuaId) ) return false;
		 DeaStuaId castOther = ( DeaStuaId ) other; 
         
		 return (this.getStuaNr()==castOther.getStuaNr())
 && (this.getStuaVersion()==castOther.getStuaVersion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getStuaNr();
         result = 37 * result + this.getStuaVersion();
         return result;
   }   


}


