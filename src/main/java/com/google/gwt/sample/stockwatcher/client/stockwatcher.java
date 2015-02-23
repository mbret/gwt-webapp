package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.sample.stockwatcher.shared.services.action.ActionGWTService;
import com.google.gwt.sample.stockwatcher.shared.services.action.ActionGWTServiceAsync;
import com.google.gwt.sample.stockwatcher.shared.services.gain.GainGWTService;
import com.google.gwt.sample.stockwatcher.shared.services.gain.GainGWTServiceAsync;
import com.google.gwt.sample.stockwatcher.shared.models.ActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.BuyedActionGWT;
import com.google.gwt.sample.stockwatcher.shared.models.WalletGWT;
import com.google.gwt.sample.stockwatcher.shared.services.wallet.WalletGWTService;
import com.google.gwt.sample.stockwatcher.shared.services.wallet.WalletGWTServiceAsync;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;

import java.util.ArrayList;
import java.util.List;

public class stockwatcher implements EntryPoint {

    public GUI gui;
    public static final int REFRESH_INTERVAL = 15000; // ms

    public ActionGWTServiceAsync actionSvc = GWT.create(ActionGWTService.class);
    public GainGWTServiceAsync gainSvc = GWT.create(GainGWTService.class);
    public WalletGWTServiceAsync walletSvc = GWT.create(WalletGWTService.class);

    public ArrayList<String> stocks = new ArrayList();
    public ArrayList<String> toPurchase = new ArrayList();

    final stockwatcher that = this;

    /**
     * Entry point.
     * - Load stock panel
     * - Load wallet panel 
     */
    public void onModuleLoad() {
        this.gui = new GUI(this);
        this.gui.initGUI();
        this.loadStocks();
        this.loadWallet();
    }

    public void loadStocks(){
        
        // Setup timer to refresh list automatically.
        Timer refreshTimer = new Timer() {
            @Override
            public void run() {
                that.refreshWatchList();
                that.gui.displayMessage(GUI.MESSAGE_ACTION_FETCHING);
            }
        };
        refreshTimer.scheduleRepeating(this.REFRESH_INTERVAL);

        this.gui.searchedSymbolTextBox.addKeyPressHandler(new KeyPressHandler() {
            
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getCharCode() == KeyCodes.KEY_ENTER) {
                    
                    // look for symbol
                    that.searchSymbol(that.gui.searchedSymbolTextBox.getText().trim().toUpperCase(), new AsyncCallback<ActionGWT>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            // ...
                        }

                        @Override
                        public void onSuccess(ActionGWT result) {
                            if(result != null){
                                that.addSymbolStockList(result);
                                that.gui.resetActionSearch();
                            }
                        }
                    });
                    
                }
            }
        });

        // Search symbol event: on click
        this.gui.searchActionButton.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                that.searchSymbol(that.gui.searchedSymbolTextBox.getText().trim().toUpperCase(), new AsyncCallback<ActionGWT>() {
                    
                    @Override
                    public void onFailure(Throwable caught) {
                        // ...
                    }

                    @Override
                    public void onSuccess(ActionGWT result) {
                        if( result != null ){
                            that.addSymbolStockList(result);
                            that.gui.resetActionSearch();
                        }
                    }
                });

            }
        });

        // Refresh UI
        gui.loadStocks(this.gui.searchedSymbolTextBox, this.gui.searchActionButton);
    }

    public void loadWallet(){
        gui.loadWallet();

        if (this.walletSvc == null){
            this.walletSvc = GWT.create(WalletGWTService.class);
        }
        this.walletSvc.getAllBuyedActions(new AsyncCallback<WalletGWT>() {
            
            @Override
            public void onFailure(Throwable caught) {
                that.gui.displayError(caught);
            }

            @Override
            public void onSuccess(WalletGWT result) {
                if(result == null){
                    
                }
                else{
                    for(BuyedActionGWT ba : result.getBuyedActions()){
                        that.addWalletAction(ba);
                    }
                    that.gui.displayMessage(GUI.MESSAGE_WALLET_FETCHING);
                }
            }
        });
    }

    /**
     * Look for a stock with its symbol. 
     * @param researchedSymbol looking for symbol
     */
    public void searchSymbol(String researchedSymbol, final AsyncCallback<ActionGWT> cb){

        // Don't add the stock if it's already in the table.
        if (toPurchase.contains(researchedSymbol)){
            that.gui.alert(gui.MESSAGE_STOCK_ALREADY_HERE);
            cb.onSuccess(null);
            return;
        }

        // Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
        if (!researchedSymbol.matches("^[0-9a-zA-Z\\.]{1,10}$")) {
            Window.alert("'" + researchedSymbol + "' is not a valid symbol.");
            that.gui.newSymbolTextBox.selectAll();
            cb.onSuccess(null);
            return;
        }
        
        if(actionSvc == null)
            actionSvc = GWT.create(ActionGWTService.class);

        this.gui.displayMessage(GUI.MESSAGE_ACTION_SEARCHING);
        
        actionSvc.getAction(researchedSymbol, new AsyncCallback<ActionGWT>() {
            @Override
            public void onFailure(Throwable caught) {
                caught.printStackTrace();
                gui.displayError(caught);
                cb.onFailure(caught);
            }
            @Override
            public void onSuccess(ActionGWT result) {
                cb.onSuccess(result);
            }
        });
    }

    /**
     * add stock to wallet
     */
    public void addWalletAction(BuyedActionGWT result) {
        final BuyedActionGWT buyed = result;
        final String symbol = Filter.symbol(result.getSymbol());
        this.stocks.add(symbol);

        // Add a button to remove this stock from the table.
        Button sellButton = new Button("Sell");
        sellButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                int removedIndex = stocks.indexOf(symbol);
                that.stocks.remove(removedIndex);
                that.gui.removeWalletAction(removedIndex + 1);
                that.gui.displayMessage(GUI.MESSAGE_ACTION_SOLD);
            }
        });

        Button detailsButton = new Button("Details");
        detailsButton.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                updateDetails(buyed);
            }
        });
        
        // Refresh UI
        this.gui.addActionToWallet(symbol, result.getPrix(), result.getNbr(), sellButton, detailsButton);
    }

    /**
     * Add a new row an action inside list
     */
    public void addSymbolStockList(final ActionGWT result) {
        final TextBox number = new TextBox();
        final String symbol = result.getSymbol().toUpperCase().trim();

        // Add the stock to the table.
        this.toPurchase.add(symbol);
        
        Button purchaseButton = new Button("Buy");
        purchaseButton.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {

                // check number desired
                if ( ! Validator.isValidNumber(number.getText()) ) {
                    that.gui.alert(gui.MESSAGE_INVALID_NUMBER);
                }
                else{

                    if (walletSvc == null){
                        walletSvc = GWT.create(WalletGWTService.class);
                    }

                    walletSvc.buyAction(result, Integer.valueOf(number.getText()), new AsyncCallback<BuyedActionGWT>() {
                        
                        @Override
                        public void onFailure(Throwable caught) {
                            gui.displayError(caught);
                        }

                        @Override
                        public void onSuccess(BuyedActionGWT result) {
                            // Add new action to wallet
                            that.addWalletAction(result);
                            
                            // Remove action row
                            int removedIndex = toPurchase.indexOf(symbol);
                            toPurchase.remove(removedIndex);
                            that.gui.removeStockRow(removedIndex + 1);
                            that.gui.displayMessage(gui.MESSAGE_ACTION_BOUGHT);
                        }
                    });

                    
                }
                
            }
        });

        // set graphical changes
        this.gui.addStockRow(symbol, number, result.getPrice(), purchaseButton);
        
    }

    public void refreshWatchList() {  
        if (stocks.size() == 0) {  
            return;  
        }

        this.gui.removeStockRows();
        this.gui.initHeaderActionsList();
        
        List<String> toRefresh = (ArrayList<String>)toPurchase.clone();
        toPurchase = new ArrayList();
        for(String s : toRefresh)
            searchSymbol(s, new AsyncCallback<ActionGWT>() {
                @Override
                public void onFailure(Throwable caught) {
                    // ...
                }

                @Override
                public void onSuccess(ActionGWT result){
                    if( result != null ){
                        that.addSymbolStockList(result);
                    }
                }
            });
    }

    private void updateDetails(BuyedActionGWT result){
        final BuyedActionGWT buyed = result;

        this.gui.refreshActionDetail(result.getSymbol(), ((Double)result.getPrix()).toString(), result.getNbr()+1);

        if(actionSvc == null)
            actionSvc = GWT.create(ActionGWTService.class);

        actionSvc.getAction(result.getSymbol(), new AsyncCallback<ActionGWT>() {
            public void onFailure(Throwable caught) {
                that.gui.displayError(caught);
            }

            public void onSuccess(ActionGWT action) {
                actualPriceDetail.setText("Actual price: "+action.getPrice()+"");

                double total = action.getPrice() * buyed.getNbr();
                BigDecimal bd = new BigDecimal(total);
                bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                totalDetail.setText("Total: "+bd.doubleValue()+"");

                double diff = action.getPrice() - buyed.getPrix();
                if(diff >= 0)
                    diffDetail.getElement().getStyle().setColor("darkgreen");
                else
                    diffDetail.getElement().getStyle().setColor("red");
                bd = new BigDecimal(diff);
                bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                diffDetail.setText("Difference: "+bd.doubleValue()+"");
            }
        });
    }
    
}
