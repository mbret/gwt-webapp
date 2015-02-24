package com.google.gwt.sample.stockwatcher.shared.services.wallet;

import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.BoughtActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.WalletGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *  Get and retrieve all actions
 */
@RemoteServiceRelativePath("WalletService")
public interface WalletGWTService extends RemoteService{
    
    BoughtActionGWT buyAction(ActionGWT action, int qte);
    
    WalletGWT fetchBoughtActions();

    Integer sellAction(Integer id, int number);

    void sellAction(Integer id);
}
