package com.google.gwt.sample.stockwatcher.shared.models;

import java.io.Serializable;

/**
 * Created by Brian on 13/01/2015.
 */

public class GainGWT implements Serializable {
    private double gain;

    public GainGWT(){}

    public GainGWT(double g){
        gain = g;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }
}
