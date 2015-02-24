package com.google.gwt.sample.stockwatcher.shared.models;

import java.io.Serializable;

/**
 *
 */
public class BoughtActionGWT implements Serializable {

    private int id;

    private String symbol;

    private double prix;

    private int nbr;

    public BoughtActionGWT(){}

    public BoughtActionGWT(int id, String symbol, double prix, int nbr) {
        this.id = id;
        this.symbol = symbol;
        this.prix = prix;
        this.nbr = nbr;
    }

    public BoughtActionGWT(String s, double p, int n){
        symbol = s;
        prix = p;
        nbr = n;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }
}
