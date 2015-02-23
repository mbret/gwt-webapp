package com.google.gwt.sample.stockwatcher.webservice.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "StockQuotes")
public class StockQuotes {

    private List<Stock> stocks;

    public StockQuotes(){}

    public StockQuotes(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @XmlElement(name = "Stock")
    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "StockQuotes{" +
                "stocks=" + stocks +
                '}';
    }
}
