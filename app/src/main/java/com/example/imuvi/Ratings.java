package com.example.imuvi;

public class Ratings {
    //RATINGS
    String idimdbr;
    String source;
    String value;

    public Ratings(String idImbdRR, String sourceR, String valueR){
        this.idimdbr = idImbdRR;
        this.source = sourceR;
        this.value = valueR;

    }

    public Ratings(){

    }

    //IDIMDBR

    public String getIdimdbr() {
        return idimdbr;
    }

    public void setIdimdbr(String idimdbr) {
        this.idimdbr = idimdbr;
    }

    //

    //SOURCE
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    //

    //VALUE
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //
}
