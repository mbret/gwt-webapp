package com.google.gwt.sample.stockwatcher.shared.services.action;

import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 */
@RemoteServiceRelativePath("ActionService")
public interface ActionGWTService extends RemoteService{
    ActionGWT getAction(String symbol);
}
