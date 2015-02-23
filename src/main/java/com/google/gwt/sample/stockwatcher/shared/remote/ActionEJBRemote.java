package com.google.gwt.sample.stockwatcher.shared.remote;

import javax.ejb.Remote;

/**
 * Created by Brian on 27/01/2015.
 */

@Remote
public interface ActionEJBRemote {
    public double getStockPrice(String stock);
}
