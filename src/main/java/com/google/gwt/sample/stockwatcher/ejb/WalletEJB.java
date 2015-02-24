package com.google.gwt.sample.stockwatcher.ejb;

import com.google.gwt.sample.stockwatcher.ejb.entity.BoughtActionEntity;
import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.BoughtActionGWT;
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

    public BoughtActionGWT saveBoughtAction(ActionGWT action, int nbr){
        BoughtActionGWT buyedaction = new BoughtActionGWT(action.getSymbol(), action.getPrice(), nbr);
        BoughtActionEntity toPersist = new BoughtActionEntity(action.getSymbol(), action.getPrice(), nbr);

        emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        //Enregistre l'action achetée en base
        em.getTransaction().begin();
        em.persist(toPersist);
        em.flush();
        em.getTransaction().commit();
        em.close();

        buyedaction.setId( toPersist.getId() );
        return buyedaction;
    }
    
    public WalletGWT fetchBoughtActions(){
        emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        TypedQuery<BoughtActionEntity> query = em.createQuery("SELECT ba FROM boughtaction ba", BoughtActionEntity.class);

        List<BoughtActionGWT> toReturn = new ArrayList<>();
        for(BoughtActionEntity baejb : query.getResultList())
            toReturn.add(new BoughtActionGWT(baejb.getId(), baejb.getSymbol(), baejb.getPrix(), baejb.getNbr()));

        WalletGWT wallet = new WalletGWT();
        wallet.setBoughtActions(toReturn);
        return wallet;
    }

    /**
     *
     * @param id
     * @param nbr
     */
    @Override
    public Integer sellAction(Integer id, int nbr) {
        emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        // fetch action
        BoughtActionEntity entity = em.find(BoughtActionEntity.class, id);

        // update
        em.getTransaction().begin();
        entity.setNbr( entity.getNbr() - nbr );
        em.getTransaction().commit();
        return entity.getNbr();
    }

    @Override
    public void sellAction(Integer id) {
        emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        // fetch action
        BoughtActionEntity entity = em.find(BoughtActionEntity.class, id);

        // update
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }


}
