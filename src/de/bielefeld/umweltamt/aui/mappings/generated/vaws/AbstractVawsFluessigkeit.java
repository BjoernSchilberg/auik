package de.bielefeld.umweltamt.aui.mappings.generated.vaws;
// Generated 13.07.2012 16:41:05 by Hibernate Tools 3.3.0.GA



/**
 * VawsFluessigkeit generated by hbm2java
 */
public abstract class AbstractVawsFluessigkeit  implements java.io.Serializable {


     private int id;
     private String fluessigkeit;
     private Integer idland;

    public AbstractVawsFluessigkeit() {
    }

	
    public AbstractVawsFluessigkeit(int id) {
        this.id = id;
    }
    public AbstractVawsFluessigkeit(int id, String fluessigkeit, Integer idland) {
       this.id = id;
       this.fluessigkeit = fluessigkeit;
       this.idland = idland;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getFluessigkeit() {
        return this.fluessigkeit;
    }
    
    public void setFluessigkeit(String fluessigkeit) {
        this.fluessigkeit = fluessigkeit;
    }
    public Integer getIdland() {
        return this.idland;
    }
    
    public void setIdland(Integer idland) {
        this.idland = idland;
    }




}


