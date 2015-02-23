package com.google.gwt.sample.stockwatcher.webservice.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Stock")
public class Stock {

    //string
    private String symbol;
    //double
    private String last;
    //date
    private String date;
    //heure
    private String time;
    //double (+ si positif, - si négatif) -> parser !
    private String change;
    //double
    private String open;
    //double
    private String high;
    //double
    private String low;
    //long
    private String volume;
    //string
    private String mktcap;
    //double 
    private String previousclose;
    //double (+ si positif, - si négatif, en %) -> parser !
    private String percentagechange;
    //string (intervalle)
    private String annrange;
    //double
    private String earns;
    //double
    private String pe;
    //string
    private String name;

    public Stock(){}

    public Stock(String symbol, String last, String date, String time, String change, String open, String high, String low, String volume, String mktcap, String previousclose, String percentagechange, String annrange, String earns, String pe, String name) {
        this.symbol = symbol;
        this.last = last;
        this.date = date;
        this.time = time;
        this.change = change;
        this.open = open;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.mktcap = mktcap;
        this.previousclose = previousclose;
        this.percentagechange = percentagechange;
        this.annrange = annrange;
        this.earns = earns;
        this.pe = pe;
        this.name = name;
    }

    @XmlElement(name = "Symbol")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @XmlElement(name = "Last")
    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @XmlElement(name = "Date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement(name = "Time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @XmlElement(name = "Change")
    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    @XmlElement(name = "Open")
    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    @XmlElement(name = "High")
    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    @XmlElement(name = "Low")
    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    @XmlElement(name = "Volume")
    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @XmlElement(name = "MktCap")
    public String getMktcap() {
        return mktcap;
    }

    public void setMktcap(String mktcap) {
        this.mktcap = mktcap;
    }

    @XmlElement(name = "PreviousClose")
    public String getPreviousclose() {
        return previousclose;
    }

    public void setPreviousclose(String previousclose) {
        this.previousclose = previousclose;
    }

    @XmlElement(name = "PercentageChange")
    public String getPercentagechange() {
        return percentagechange;
    }

    public void setPercentagechange(String percentagechange) {
        this.percentagechange = percentagechange;
    }

    @XmlElement(name = "AnnRange")
    public String getAnnrange() {
        return annrange;
    }

    public void setAnnrange(String annrange) {
        this.annrange = annrange;
    }

    @XmlElement(name = "Earns")
    public String getEarns() {
        return earns;
    }

    public void setEarns(String earns) {
        this.earns = earns;
    }

    @XmlElement(name = "P-E")
    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", last='" + last + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", change='" + change + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", volume='" + volume + '\'' +
                ", mktcap='" + mktcap + '\'' +
                ", previousclose='" + previousclose + '\'' +
                ", percentagechange='" + percentagechange + '\'' +
                ", annrange='" + annrange + '\'' +
                ", earns='" + earns + '\'' +
                ", pe='" + pe + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
