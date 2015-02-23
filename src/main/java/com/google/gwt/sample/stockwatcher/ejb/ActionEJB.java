package com.google.gwt.sample.stockwatcher.ejb;

import com.google.gwt.sample.stockwatcher.shared.remote.ActionEJBRemote;
import com.google.gwt.sample.stockwatcher.webservice.StockQuote;
import com.google.gwt.sample.stockwatcher.webservice.StockQuoteSoap;
import com.google.gwt.sample.stockwatcher.webservice.model.StockQuotes;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 */
@Stateless
public class ActionEJB implements ActionEJBRemote {

    @Override
    public double getStockPrice(String stock){
        StockQuoteSoap service = new StockQuote().getStockQuoteSoap();

        InputStream xmlStream = new ByteArrayInputStream(service.getQuote(stock).getBytes());
        StockQuotes quotes = new StockQuotes();
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(StockQuotes.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            quotes = (StockQuotes)unmarshaller.unmarshal(xmlStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        double result = Double.parseDouble(quotes.getStocks().get(0).getLast());
        if(result > 0)
            return result;
        return -1;
    }
}
