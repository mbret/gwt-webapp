package com.google.gwt.sample.stockwatcher.shared.models;

import java.io.Serializable;

/**
 *
 */
public class ActionGWT implements Serializable{
    private double price;
    private String symbol;

    public ActionGWT(){}

    public ActionGWT(String s, double p){
        price = p;
        symbol = s;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
