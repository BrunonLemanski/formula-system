package com.brunonlemanski.pjatk.logger.model;

public class Race {

    private Integer actualTime;

    private Integer actualLap;

    public Race() {
    }

    public Race(Integer actualTime, Integer actualLap) {
        this.actualTime = actualTime;
        this.actualLap = actualLap;
    }

    public Integer getActualTime() {
        return actualTime;
    }

    public void setActualTime(Integer actualTime) {
        this.actualTime = actualTime;
    }

    public Integer getActualLap() {
        return actualLap;
    }

    public void setActualLap(Integer actualLap) {
        this.actualLap = actualLap;
    }

    @Override
    public String toString() {
        return "Race{" +
                "actualTime=" + actualTime +
                ", actualLap=" + actualLap +
                '}';
    }
}
