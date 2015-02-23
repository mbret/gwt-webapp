package com.google.gwt.sample.stockwatcher.ejb;

import com.google.gwt.sample.stockwatcher.ejb.entity.BuyedActionEntity;
import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.BuyedActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.WalletGWT;
import com.google.gwt.sample.stockwatcher.shared.remote.WalletEJBRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Stateless
public class WalletEJB implements WalletEJBRemote {

    private transient EntityManagerFactory emf;

    public EntityManagerFactory getEntityManagerFactory() {
        if(emf == null)
            emf = Persistence.createEntityManagerFactory("stockwatcher");
        return emf;
    }

    public BuyedActionGWT saveBuyedAction(ActionGWT action, int nbr){
        BuyedActionGWT buyedaction = new BuyedActionGWT(action.getSymbol(), action.getPrice(), nbr);
        BuyedActionEntity toPersist = new BuyedActionEntity(action.getSymbol(), action.getPrice(), nbr);

        emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        //Enregistre l'action achetée en base
        em.getTransaction().begin();
        em.persist(toPersist);
        em.flush();
        em.getTransaction().commit();
        em.close();

        return buyedaction;
    }
    
    public WalletGWT getAllBuyedActions(){
        emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        TypedQuery<BuyedActionEntity> query = em.createQuery("SELECT ba FROM buyedaction ba", BuyedActionEntity.class);

        List<BuyedActionGWT> toReturn = new ArrayList<>();
        for(BuyedActionEntity baejb : query.getResultList())
            toReturn.add(new BuyedActionGWT(baejb.getSymbol(), baejb.getPrix(), baejb.getNbr()));

        WalletGWT wallet = new WalletGWT();
        wallet.setBuyedActions(toReturn);
        return wallet;
    }

    @Override
    public void sellAction(ActionGWT action, int nbr) {
        emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
//        em.find(BuyedActionEntity.class, action.get);
    }


}
