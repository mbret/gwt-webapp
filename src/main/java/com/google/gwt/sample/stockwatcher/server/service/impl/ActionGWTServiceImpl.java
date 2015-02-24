package com.google.gwt.sample.stockwatcher.server.service.impl;

import com.google.gwt.sample.stockwatcher.server.util.ContextGenerator;
import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.remote.ActionEJBRemote;
import com.google.gwt.sample.stockwatcher.shared.services.action.ActionGWTService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 */
public class ActionGWTServiceImpl extends RemoteServiceServlet implements ActionGWTService {
    
    @Override
    public ActionGWT getAction(String symbol) {
        try {
            InitialContext ctx = ContextGenerator.getContext();

            ActionEJBRemote remote = (ActionEJBRemote) ctx.lookup(ContextGenerator.getPath("ActionEJB", ActionEJBRemote.class));

            double result = remote.getStockPrice(symbol);
            if(result > 0)
                return new ActionGWT(symbol, remote.getStockPrice(symbol));
            return null;
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
        
//        ActionEJB ejb = new ActionEJB();
//        return new ActionGWT(symbol, ejb.getStockPrice(symbol));
    }

}
