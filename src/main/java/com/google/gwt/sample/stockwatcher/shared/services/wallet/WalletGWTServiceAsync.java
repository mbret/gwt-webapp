package com.google.gwt.sample.stockwatcher.shared.services.wallet;

import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.BuyedActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.WalletGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface WalletGWTServiceAsync {
    
    void buyAction(ActionGWT action, int qte, AsyncCallback<BuyedActionGWT> async);
    
    void getAllBuyedActions(AsyncCallback<WalletGWT> async);
}
