package com.google.gwt.sample.stockwatcher.shared.services.wallet;

import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.BoughtActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.WalletGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface WalletGWTServiceAsync {
    
    void buyAction(ActionGWT action, int qte, AsyncCallback<BoughtActionGWT> async);
    
    void fetchBoughtActions(AsyncCallback<WalletGWT> async);

    void sellAction(Integer id, int number, AsyncCallback<Integer> async);

    void sellAction(Integer id, AsyncCallback<Void> async);
}
