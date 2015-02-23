package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;

/**
 * Created by Maxime on 2/23/2015.
 */
public class GUI {

    public static final int ERROR_INTERVAL = 5000; // ms
    public static final int INFO_INTERVAL = 5000; // ms

    public static final String MESSAGE_INVALID_NUMBER = "Please specify a valid number";
    public static final String MESSAGE_ACTION_BOUGHT = "Action bought!";
    public static final String MESSAGE_ACTION_SOLD = "Action sold!";
    public static final String MESSAGE_STOCK_ALREADY_HERE = "Stock already present";
    public static final String MESSAGE_WALLET_FETCHING = "Wallet is fetching...";
    public static final String MESSAGE_ACTION_FETCHING = "Action are refreshing...";
    public static final String MESSAGE_ACTION_SEARCHING = "Action is searching...";

    private stockwatcher stockwatcher;

    public Label errorMsgLabel = new Label();
    Timer errorMessageTimer = new Timer() {
        @Override
        public void run() {
            errorMsgLabel.setVisible(false);
        }
    };

    public Label infoMsgLabel = new Label();
    Timer infoMessageTimer = new Timer() {
        @Override
        public void run() {
            infoMsgLabel.setVisible(false);
        }
    };

    //buyedaction: porte feuille d'actions achetées
    private VerticalPanel verticalStockPanel = new VerticalPanel();
    private FlexTable buyedActionsFlexTable = new FlexTable();
    private HorizontalPanel addPanel = new HorizontalPanel();
    public TextBox newSymbolTextBox = new TextBox();
    private Label lastUpdatedLabel = new Label();

    //action: action recherchees, et que l'on peut acheter
    private FlexTable actionsFlexTable = new FlexTable();
    private VerticalPanel verticalPurchasePanel = new VerticalPanel();
    public Button searchActionButton = new Button("Search");
    public TextBox searchedSymbolTextBox = new TextBox();
    private HorizontalPanel searchPanel = new HorizontalPanel();

    //details: detail de l'action achetée
    private VerticalPanel verticalDetailsPanel = new VerticalPanel();
    private Label symbolDetail = new Label();
    private Label priceDetail = new Label();
    private Label nbDetail = new Label();
    private Label actualPriceDetail = new Label();
    private Label totalDetail = new Label();
    private Label diffDetail = new Label();
    
    public GUI(stockwatcher stockwatcher) {
        this.stockwatcher = stockwatcher;
    }

    public void initGUI(){
        this.errorMsgLabel.setStyleName("errorMessage");
        this.errorMsgLabel.setVisible(false);
        this.infoMsgLabel.setStyleName("infoMessage");
        this.infoMsgLabel.setVisible(false);
        com.google.gwt.dom.client.Document.get().getBody().appendChild(this.errorMsgLabel.getElement());
        com.google.gwt.dom.client.Document.get().getBody().appendChild(this.infoMsgLabel.getElement());
    }
    
    public void initHeaderActionsList(){
        this.actionsFlexTable.setText(0, 0, "Name");
        this.actionsFlexTable.setText(0, 1, "Price");
        this.actionsFlexTable.setText(0, 2, "Number");
        this.actionsFlexTable.setText(0, 3, "Purchase");

        // Add styles to elements in the stock list table.
        this.actionsFlexTable.setCellPadding(6);
        this.actionsFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
        this.actionsFlexTable.addStyleName("watchList");
        this.actionsFlexTable.addStyleName("table");
        this.actionsFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        this.actionsFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        this.actionsFlexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");
    }
   
    
    /**
     * Prepare display of stock list and search field.
     */
    public void loadStocks(TextBox searchedSymbolTextBox, Button searchActionButton){
        this.initHeaderActionsList();

        // Assemble Add Stock panel.
        this.searchPanel.add(this.searchedSymbolTextBox);
        this.searchPanel.add(this.searchActionButton);
        this.searchPanel.addStyleName("addPanel");

        //verticalPurchasePanel.add(errorMsgLabel);
        this.verticalPurchasePanel.add(this.searchPanel);
        this.verticalPurchasePanel.add(this.actionsFlexTable);

        // Associate the Main panel with the HTML host page.
        RootPanel.get("actionInfos").add(this.verticalPurchasePanel);

        // Move cursor focus to the input box.
        this.searchedSymbolTextBox.setFocus(true);
        
        this.searchedSymbolTextBox = searchedSymbolTextBox;
        this.searchActionButton = searchActionButton;

    }

    /**
     * Chargement graphique des composants du porte feuille Wallet 
     */
    public void loadWallet(){
        // Create table for stock data.
        this.buyedActionsFlexTable.setText(0, 0, "Name");
        this.buyedActionsFlexTable.setText(0, 1, "Price");
        this.buyedActionsFlexTable.setText(0, 2, "Number");
        this.buyedActionsFlexTable.setText(0, 3, "Sell");

        // Add styles to elements in the stock list table.
        this.buyedActionsFlexTable.setCellPadding(6);
        this.buyedActionsFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
        this.buyedActionsFlexTable.addStyleName("watchList");
        this.buyedActionsFlexTable.addStyleName("table");
        this.buyedActionsFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        this.buyedActionsFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
        this.buyedActionsFlexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");

        // Assemble Add Stock panel.
        this.addPanel.add(this.newSymbolTextBox);
        this.addPanel.addStyleName("addPanel");

        // Assemble Main panel.


//        this.verticalStockPanel.add(this.errorMsgLabel);
        this.verticalStockPanel.add(this.buyedActionsFlexTable);
        this.verticalStockPanel.add(this.lastUpdatedLabel);

        // Associate the Main panel with the HTML host page.
        RootPanel.get("stockList").add(this.verticalStockPanel);

        // Move cursor focus to the input box.
        this.newSymbolTextBox.setFocus(true);


    }
    
    public void addStockRow(String symbol, TextBox number, Double price, Button purchaseButton){
        this.newSymbolTextBox.setFocus(true);
        int row = this.actionsFlexTable.getRowCount();
        this.newSymbolTextBox.setText("");
        // Add the stock to the table.
        this.actionsFlexTable.setText(row, 0, symbol);
        this.actionsFlexTable.setText(row, 1, price.toString());
        this.actionsFlexTable.setWidget(row, 2, number);
        this.actionsFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
        this.actionsFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
        this.actionsFlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
        this.actionsFlexTable.setWidget(row, 3, purchaseButton);
    }
    
    public void addActionToWallet(String symbol, Double price, Integer number, Button sellButton, Button detailsButton){
        this.newSymbolTextBox.setFocus(true);
        this.newSymbolTextBox.setText("");
        int row = this.buyedActionsFlexTable.getRowCount();
        this.buyedActionsFlexTable.setText(row, 0, symbol);
        this.buyedActionsFlexTable.setText(row, 1, price.toString());
        this.buyedActionsFlexTable.setText(row, 2, number.toString());
        this.buyedActionsFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
        this.buyedActionsFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
        this.buyedActionsFlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
        this.buyedActionsFlexTable.setWidget(row, 3, sellButton);
        this.buyedActionsFlexTable.setWidget(row, 4, detailsButton);
        
    }

    public void removeWalletAction( int row ){
        this.buyedActionsFlexTable.removeRow(row);
    }

    public void removeStockRow( int row ){
        this.actionsFlexTable.removeRow(row);
    }

    public void removeStockRows(){
        this.actionsFlexTable.removeAllRows();
    }
    
    public void resetActionSearch(){
        this.searchedSymbolTextBox.setText("");
    }
    
    public void refreshActionDetail(String symbol, String price, Integer number){
        symbolDetail.setText("Symbol: "+ symbol);
        priceDetail.setText("Bought price: "+ price +"");
        nbDetail.setText("Number: "+ number.toString() + "");
    }
    
    public void displayError(String message){
        errorMsgLabel.setText("Error: " + message);
        errorMsgLabel.setVisible(true);
        errorMessageTimer.schedule(ERROR_INTERVAL);
    }
    
    public void displayError(Throwable caught){
        // display the error message above the watch list
        String details = caught.getMessage();
        this.displayError(details);
    }
    
    public void displayMessage(String message){
        this.infoMsgLabel.setText("Error: " + message);
        this.infoMsgLabel.setVisible(true);
        this.infoMessageTimer.schedule(INFO_INTERVAL);
    }

    public void alert(String message){
        com.google.gwt.user.client.Window.alert(message);
        
    }
}
