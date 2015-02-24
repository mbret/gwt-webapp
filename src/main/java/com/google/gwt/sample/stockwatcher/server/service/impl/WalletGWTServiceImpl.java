package com.google.gwt.sample.stockwatcher.server.service.impl;

import com.google.gwt.sample.stockwatcher.server.util.ContextGenerator;
import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.BoughtActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.WalletGWT;
import com.google.gwt.sample.stockwatcher.shared.remote.WalletEJBRemote;
import com.google.gwt.sample.stockwatcher.shared.services.wallet.WalletGWTService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by Maxime on 13/01/2015.
 */
public class WalletGWTServiceImpl extends RemoteServiceServlet implements WalletGWTService {
    
    @Override
    public BoughtActionGWT buyAction(ActionGWT action, int qte) {
        try {
            InitialContext ctx = ContextGenerator.getContext();

            WalletEJBRemote remote = (WalletEJBRemote) ctx.lookup(ContextGenerator.getPath("WalletEJB", WalletEJBRemote.class));

            return remote.saveBoughtAction(action, qte);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;

//        WalletEJBRemote remote = new WalletEJB();
//        return remote.saveBoughtAction(action, qte);
    }

    @Override
    public WalletGWT fetchBoughtActions() {
        try {
            InitialContext ctx = ContextGenerator.getContext();

            WalletEJBRemote remote = (WalletEJBRemote) ctx.lookup(ContextGenerator.getPath("WalletEJB", WalletEJBRemote.class));

            return remote.fetchBoughtActions();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
        
//        WalletEJBRemote remote = new WalletEJB();
//        return remote.fetchBoughtActions();
    }

    @Override
    public Integer sellAction(Integer id, int number) {
        try {
            InitialContext ctx = ContextGenerator.getContext();
            WalletEJBRemote remote = (WalletEJBRemote) ctx.lookup(ContextGenerator.getPath("WalletEJB", WalletEJBRemote.class));
            return remote.sellAction(id, number);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sellAction(Integer id) {
        try {
            InitialContext ctx = ContextGenerator.getContext();
            WalletEJBRemote remote = (WalletEJBRemote) ctx.lookup(ContextGenerator.getPath("WalletEJB", WalletEJBRemote.class));
            remote.sellAction(id);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
