package com.google.gwt.sample.stockwatcher.shared.remote;

import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.BoughtActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.WalletGWT;

import javax.ejb.Remote;

/**
 * Created by Maxime on 27/01/2015.
 */

@Remote
public interface WalletEJBRemote {
    
    public BoughtActionGWT saveBoughtAction(ActionGWT action, int nbr);
    
    public WalletGWT fetchBoughtActions();
    
    public Integer sellAction(Integer id, int nbr);

    public void sellAction(Integer id);
}
