package com.pjatk.brunonlemanski.bolid.model;

public class Race {

    private Long actualTime;

    private Integer actualLap;

    public Race() {
    }

    public Race(Long actualTime, Integer actualLap) {
        this.actualTime = actualTime;
        this.actualLap = actualLap;
    }

    public Long getActualTime() {
        return actualTime;
    }

    public void setActualTime(Long actualTime) {
        this.actualTime = actualTime;
    }

    public Integer getActualLap() {
        return actualLap;
    }

    public void setActualLap(Integer actualLap) {
        this.actualLap = actualLap;
    }
}
