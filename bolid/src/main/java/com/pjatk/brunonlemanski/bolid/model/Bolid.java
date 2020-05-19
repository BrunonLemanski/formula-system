package com.pjatk.brunonlemanski.bolid.model;

public class Bolid {

    private Double tempEngine;

    private Double tirePressure;

    private Double oilPressure;

    public Bolid(Double tempEngine, Double tirePressure, Double oilPressure) {
        this.tempEngine = tempEngine;
        this.tirePressure = tirePressure;
        this.oilPressure = oilPressure;
    }

    public Double getTempEngine() {
        return tempEngine;
    }

    public void setTempEngine(Double tempEngine) {
        this.tempEngine = tempEngine;
    }

    public Double getTirePressure() {
        return tirePressure;
    }

    public void setTirePressure(Double tirePressure) {
        this.tirePressure = tirePressure;
    }

    public Double getOilPressure() {
        return oilPressure;
    }

    public void setOilPressure(Double oilPressure) {
        this.oilPressure = oilPressure;
    }
}
