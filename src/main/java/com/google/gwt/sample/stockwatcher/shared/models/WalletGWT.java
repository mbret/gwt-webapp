package com.google.gwt.sample.stockwatcher.shared.models;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class WalletGWT implements Serializable {
    
    private List<BoughtActionGWT> boughtActions;

    public WalletGWT(){}

    public WalletGWT(List<BoughtActionGWT> boughtActions) {
        this.boughtActions = boughtActions;
    }

    public List<BoughtActionGWT> getBoughtActions() {
        return boughtActions;
    }

    public void setBoughtActions(List<BoughtActionGWT> boughtActions) {
        this.boughtActions = boughtActions;
    }
}
