package com.google.gwt.sample.stockwatcher.shared.models;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class WalletGWT implements Serializable {
    
    private List<BuyedActionGWT> buyedActions;

    public WalletGWT(){}

    public WalletGWT(List<BuyedActionGWT> buyedActions) {
        this.buyedActions = buyedActions;
    }

    public List<BuyedActionGWT> getBuyedActions() {
        return buyedActions;
    }

    public void setBuyedActions(List<BuyedActionGWT> buyedActions) {
        this.buyedActions = buyedActions;
    }
}
