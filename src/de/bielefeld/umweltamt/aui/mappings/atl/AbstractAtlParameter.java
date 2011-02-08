package de.bielefeld.umweltamt.aui.mappings.atl;
// Generated 20.01.2011 14:20:20 by Hibernate Tools 3.3.0.GA



/**
 * AtlParameter generated by hbm2java
 */
public class AbstractAtlParameter  implements java.io.Serializable {


     private String ordnungsbegriff;
     private String analyseverfahren;
     private String bezeichnung;
     private Integer wirdgemessenineinheit;
     private Double grenzwert;
     private String grenzwertname;
     private Double sielhautGw;
     private Double klaerschlammGw;
     private Double preisfueranalyse;
     private Boolean einzelnBeauftragbar;
     private AtlParameterGruppen atlParameterGruppe;
     private String kennzeichnung;
     private String konservierung;

    public AbstractAtlParameter() {
    }

	
    public AbstractAtlParameter(String ordnungsbegriff) {
        this.ordnungsbegriff = ordnungsbegriff;
    }
    public AbstractAtlParameter(String ordnungsbegriff, String analyseverfahren, String bezeichnung, Integer wirdgemessenineinheit, Double grenzwert, String grenzwertname, Double sielhautGw, Double klaerschlammGw, Double preisfueranalyse, Boolean einzelnBeauftragbar, AtlParameterGruppen atlParameterGruppe) {
       this.ordnungsbegriff = ordnungsbegriff;
       this.analyseverfahren = analyseverfahren;
       this.bezeichnung = bezeichnung;
       this.wirdgemessenineinheit = wirdgemessenineinheit;
       this.grenzwert = grenzwert;
       this.grenzwertname = grenzwertname;
       this.sielhautGw = sielhautGw;
       this.klaerschlammGw = klaerschlammGw;
       this.preisfueranalyse = preisfueranalyse;
       this.einzelnBeauftragbar = einzelnBeauftragbar;
       this.atlParameterGruppe = atlParameterGruppe;
       this.kennzeichnung = kennzeichnung;
       this.konservierung = konservierung;
    }
   
    public String getOrdnungsbegriff() {
        return this.ordnungsbegriff;
    }
    
    public void setOrdnungsbegriff(String ordnungsbegriff) {
        this.ordnungsbegriff = ordnungsbegriff;
    }
    public String getAnalyseverfahren() {
        return this.analyseverfahren;
    }
    
    public void setAnalyseverfahren(String analyseverfahren) {
        this.analyseverfahren = analyseverfahren;
    }
    public String getBezeichnung() {
        return this.bezeichnung;
    }
    
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    public Integer getWirdgemessenineinheit() {
        return this.wirdgemessenineinheit;
    }
    
    public void setWirdgemessenineinheit(Integer wirdgemessenineinheit) {
        this.wirdgemessenineinheit = wirdgemessenineinheit;
    }
    public Double getGrenzwert() {
        return this.grenzwert;
    }
    
    public void setGrenzwert(Double grenzwert) {
        this.grenzwert = grenzwert;
    }
    public String getGrenzwertname() {
        return this.grenzwertname;
    }
    
    public void setGrenzwertname(String grenzwertname) {
        this.grenzwertname = grenzwertname;
    }
    public Double getSielhautGw() {
        return this.sielhautGw;
    }
    
    public void setSielhautGw(Double sielhautGw) {
        this.sielhautGw = sielhautGw;
    }
    public Double getKlaerschlammGw() {
        return this.klaerschlammGw;
    }
    
    public void setKlaerschlammGw(Double klaerschlammGw) {
        this.klaerschlammGw = klaerschlammGw;
    }
    public Double getPreisfueranalyse() {
        return this.preisfueranalyse;
    }
    
    public void setPreisfueranalyse(Double preisfueranalyse) {
        this.preisfueranalyse = preisfueranalyse;
    }
    public Boolean getEinzelnBeauftragbar() {
        return this.einzelnBeauftragbar;
    }
    
    public void setEinzelnBeauftragbar(Boolean einzelnBeauftragbar) {
        this.einzelnBeauftragbar = einzelnBeauftragbar;
    }
    public AtlParameterGruppen getAtlParameterGruppe() {
        return this.atlParameterGruppe;
    }
    
    public void setAtlParameterGruppe(AtlParameterGruppen atlParameterGruppe) {
        this.atlParameterGruppe = atlParameterGruppe;
    }
    
    public String getKennzeichnung() {
        return this.kennzeichnung;
    }
    
    public void setKennzeichnung(String kennzeichnung) {
        this.kennzeichnung = kennzeichnung;
    }
    public String getKonservierung() {
        return this.konservierung;
    }
    
    public void setKonservierung(String konservierung) {
        this.konservierung = konservierung;
    }

}


