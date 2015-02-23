package com.google.gwt.sample.stockwatcher.server.service.impl;

import com.google.gwt.sample.stockwatcher.ejb.WalletEJB;
import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.BuyedActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.WalletGWT;
import com.google.gwt.sample.stockwatcher.shared.remote.WalletEJBRemote;
import com.google.gwt.sample.stockwatcher.shared.services.wallet.WalletGWTService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Created by Brian on 13/01/2015.
 */
public class WalletGWTServiceImpl extends RemoteServiceServlet implements WalletGWTService {
    
    @Override
    public BuyedActionGWT buyAction(ActionGWT action, int qte) {
//        try {
//            InitialContext ctx = ContextGenerator.getContext();
//
//            WalletEJBRemote remote = (WalletEJBRemote) ctx.lookup(ContextGenerator.getPath("WalletEJB", WalletEJBRemote.class));
//
//            return remote.saveBuyedAction(action, qte);
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//        return null;

        WalletEJBRemote remote = new WalletEJB();
        return remote.saveBuyedAction(action, qte);
    }

    @Override
    public WalletGWT getAllBuyedActions() {
//        try {
//            InitialContext ctx = ContextGenerator.getContext();
//
//            WalletEJBRemote remote = (WalletEJBRemote) ctx.lookup(ContextGenerator.getPath("WalletEJB", WalletEJBRemote.class));
//
//            return remote.getAllBuyedActions();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//        return null;
        
        WalletEJBRemote remote = new WalletEJB();
        return remote.getAllBuyedActions();
    }
}
