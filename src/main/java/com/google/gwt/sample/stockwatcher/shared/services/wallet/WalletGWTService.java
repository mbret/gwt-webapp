package com.google.gwt.sample.stockwatcher.shared.services.wallet;

import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.BuyedActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.WalletGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *  Get and retrieve all actions
 */
@RemoteServiceRelativePath("WalletService")
public interface WalletGWTService extends RemoteService{
    
    BuyedActionGWT buyAction(ActionGWT action, int qte);
    
    WalletGWT getAllBuyedActions();
}
