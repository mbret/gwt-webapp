package com.google.gwt.sample.stockwatcher.ejb.entity;

import javax.persistence.*;

/**
 * Created by Maxime on 27/01/2015.
 */

@Entity(name = "boughtaction")
@Table(name = "bought_action")
public class BoughtActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="symbol", unique = true, nullable = false)
    private String symbol;

    @Column(name="price", unique = true, nullable = false)
    private double prix;

    @Column(name="number", unique = true, nullable = false)
    private int nbr;

    public BoughtActionEntity(){}

    public BoughtActionEntity(String s, double p, int n){
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
