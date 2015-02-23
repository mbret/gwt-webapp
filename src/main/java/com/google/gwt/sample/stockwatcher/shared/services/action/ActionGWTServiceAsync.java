package com.google.gwt.sample.stockwatcher.shared.services.action;

import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ActionGWTServiceAsync {
    void getAction(String symbol, AsyncCallback<ActionGWT> async);
}
