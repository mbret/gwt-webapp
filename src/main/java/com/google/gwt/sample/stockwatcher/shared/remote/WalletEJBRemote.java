package com.google.gwt.sample.stockwatcher.shared.remote;

import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.BuyedActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.WalletGWT;

import javax.ejb.Remote;

/**
 * Created by Brian on 27/01/2015.
 */

@Remote
public interface WalletEJBRemote {
    
    public BuyedActionGWT saveBuyedAction(ActionGWT action, int nbr);
    
    public WalletGWT getAllBuyedActions();
    
    public void sellAction(ActionGWT action, int nbr);
}
