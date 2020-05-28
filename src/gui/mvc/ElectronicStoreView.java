package gui.mvc;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Arrays;

// Remove local variable warning
@SuppressWarnings("FieldCanBeLocal")

public class ElectronicStoreView extends Pane {

    // Model
    private ElectronicStore model;

    // Columns
    private HBox mainBox;
    private VBox firstCol;
    private VBox secondCol;
    private VBox thirdCol;

    // Store's summary section
    private VBox summaryBox;
    private HBox saleNumBox;
    private HBox revBox;
    private HBox saleValBox;
    private Label summaryTitle;
    private Label saleNumLabel;
    private TextField saleNumText;
    private Label revLabel;
    private TextField revText;
    private Label saleValLabel;
    private TextField saleValText;

    // Most popular item's section
    private VBox popularItemBox;
    private Label popularItemLabel;
    private ListView<String> popularItemList;
    private ArrayList<String> popularItemStringList;
    private int[] sortList;

    // Reset Button
    private Button resetBut;

    // Store's Stock
    private Label stockLabel;
    private ListView<String> stockList;
    private ArrayList<String> stockStringList;
    private ArrayList<Product> stockProductList;

    // Add cart button
    private Button addCartBut;

    // Cart
    private Label cartLabel;
    private ListView<String> cartList;
    private ArrayList<String> cartStringList;

    // Remove Cart, Complete Sale buttons
    private HBox cartButBox;
    private Button removeCartBut;
    private Button completeSaleBut;

    public ElectronicStoreView(ElectronicStore model) {
        // Model
        this.model = model;

        // Create main box
        mainBox = new HBox();
        mainBox.setStyle("-fx-font-size: 14px;");
        mainBox.setPadding(new Insets(18,0,0,20));
        mainBox.setSpacing(20);

        // Summary's Section
        summaryBox = new VBox();
        summaryBox.setPadding(new Insets(0,0,12,0));

        summaryTitle = new Label("Store Summary:");

        saleNumBox = new HBox();
        saleNumBox.setSpacing(13.5);
        saleNumBox.setPadding(new Insets(10,0,0,0));
        saleNumLabel = new Label("# Sales:");
        saleNumLabel.setPadding(new Insets(2,0,0,0));
        saleNumText = new TextField();
        saleNumText.setPrefSize(100,10);
        saleNumText.setEditable(false);
        saleNumBox.getChildren().addAll(saleNumLabel, saleNumText);

        revBox = new HBox();
        revBox.setSpacing(5);
        revBox.setPadding(new Insets(5,0,0,0));
        revLabel = new Label("Revenue:");
        revLabel.setPadding(new Insets(2,0,0,0));
        revText = new TextField();
        revText.setPrefSize(100,10);
        revText.setEditable(false);
        revBox.getChildren().addAll(revLabel, revText);

        saleValBox = new HBox();
        saleValBox.setSpacing(11);
        saleValBox.setPadding(new Insets(5,0,0,0));
        saleValLabel = new Label("$ / Sale:");
        saleValLabel.setPadding(new Insets(2,0,0,0));
        saleValText = new TextField();
        saleValText.setPrefSize(100,10);
        saleValText.setEditable(false);
        saleValBox.getChildren().addAll(saleValLabel, saleValText);

        summaryBox.getChildren().addAll(summaryTitle, saleNumBox, revBox, saleValBox);

        // Most popular item's section
        popularItemBox = new VBox();

        popularItemLabel = new Label("Most Popular Items:");
        popularItemLabel.setPadding(new Insets(0,0,5,18));
        popularItemList = new ListView<String>();
        popularItemList.setPrefSize(160,120);

        popularItemBox.getChildren().addAll(popularItemLabel, popularItemList);

        resetBut = new Button("Reset Store");
        resetBut.setPrefSize(160, 50);

        firstCol = new VBox();
        firstCol.setSpacing(6);
        firstCol.getChildren().addAll(summaryBox, popularItemBox, resetBut);

        // Stock section
        secondCol = new VBox();
        secondCol.setSpacing(6);

        stockLabel = new Label("Store Stock:");
        stockLabel.setPadding(new Insets(0,0,4,101));
        stockList = new ListView<String>();
        stockList.setPrefSize(278,264);

        addCartBut = new Button("Add to Cart");
        addCartBut.setPrefSize(278,50);

        secondCol.getChildren().addAll(stockLabel, stockList, addCartBut);

        // Cart section
        thirdCol = new VBox();
        thirdCol.setSpacing(6);

        cartLabel = new Label("Current Cart:");
        cartLabel.setPadding(new Insets(0,0,4,80));
        cartList = new ListView<String>();
        cartList.setPrefSize(278,264);

        cartButBox = new HBox();
        cartButBox.setSpacing(8);

        removeCartBut = new Button("Remove from Cart");
        removeCartBut.setPrefSize(141, 50);
        completeSaleBut = new Button("Complete Sale");
        completeSaleBut.setPrefSize(129,50);

        cartButBox.getChildren().addAll(removeCartBut, completeSaleBut);

        thirdCol.getChildren().addAll(cartLabel, cartList, cartButBox);

        mainBox.getChildren().addAll(firstCol, secondCol, thirdCol);
        mainBox.requestFocus();
        getChildren().add(mainBox);
    }

    // Getter methods
    public Button getAddCartBut() {
        return addCartBut;
    }

    public Button getResetBut() {
        return resetBut;
    }

    public Button getRemoveCartBut() {
        return removeCartBut;
    }

    public Button getCompleteSaleBut() {
        return completeSaleBut;
    }

    public ListView getStockList() {
        return stockList;
    }

    public ArrayList<Product> getStockProductList() {
        return stockProductList;
    }

    public ListView getCartList() {
        return cartList;
    }

    public ArrayList<String> getCartStringList() {
        return cartStringList;
    }

    public TextField getSaleNumText() {
        return saleNumText;
    }

    public TextField getRevText() {
        return revText;
    }

    public TextField getSaleValText() {
        return saleValText;
    }

    public ListView<String> getPopularItemList() {
        return popularItemList;
    }

    public void setModel(ElectronicStore model) {
        this.model = model;
    }

    // Update method
    public void update() {

        // Update the number of sales made
        saleNumText.setText(Integer.toString(model.getSaleNum()));

        // Update the revenue
        revText.setText(String.format("%.2f", model.getRevenue()));

        // Update the sale's value
        if (Double.toString(model.getRevenue() / model.getSaleNum()).equals("NaN")) {
            saleValText.setText("N/A");
        }

        else {
            saleValText.setText(String.format("%.2f", (model.getRevenue() / model.getSaleNum())));
        }

        // Update the store's stock
        stockStringList = new ArrayList<String>();
        stockProductList = new ArrayList<Product>();

        for (int i = 0; i < model.getStock().length; i++) {
            if ((model.getStock()[i] != null) && (model.getStock()[i].getStockQuantity() > 0)) {
                stockStringList.add(model.getStock()[i].toString());
                stockProductList.add(model.getStock()[i]);
            }
        }
        stockList.setItems(FXCollections.observableArrayList(stockStringList));

        // Update most popular item list
        popularItemStringList = new ArrayList<String>();
        sortList = new int[model.getStock().length];

        int counter = 0;

        for (int i = 0; i < model.getStock().length; i++) {
            if (model.getStock()[i] != null) {
                sortList[counter] = model.getStock()[i].getSoldQuantity();
                counter += 1;
            }
        }
        Arrays.sort(sortList);

        int first = sortList[sortList.length-1];
        int second = sortList[sortList.length-2];
        int third = sortList[sortList.length-3];

        for (int i = 0; i < model.getStock().length; i++) {
            if ((model.getStock()[i] != null) && (model.getStock()[i].getSoldQuantity() == first)) {
                if (!popularItemStringList.contains(model.getStock()[i].toString())) {
                    popularItemStringList.add(model.getStock()[i].toString());
                    break;
                }
            }
        }

        for (int i = 0; i < model.getStock().length; i++) {
            if ((model.getStock()[i] != null) && (model.getStock()[i].getSoldQuantity() == second)) {
                if (!popularItemStringList.contains(model.getStock()[i].toString())) {
                    popularItemStringList.add(model.getStock()[i].toString());
                    break;
                }
            }
        }

        for (int i = 0; i < model.getStock().length; i++) {
            if ((model.getStock()[i] != null) && (model.getStock()[i].getSoldQuantity() == third)) {
                if (!popularItemStringList.contains(model.getStock()[i].toString())) {
                    popularItemStringList.add(model.getStock()[i].toString());
                    break;
                }
            }
        }

        popularItemList.setItems(FXCollections.observableArrayList(popularItemStringList));

        // Update Cart list
        cartStringList = new ArrayList<String>();

        for (int i = 0; i < model.getCart().size(); i++) {
            if (model.getCart().get(i) != null) {
                cartStringList.add(model.getCart().get(i).toString());
            }
        }
        cartList.setItems(FXCollections.observableArrayList(cartStringList));

        // Update Cart value
        cartLabel.setText(String.format("Current Cart ($%.2f):", model.getCartValue()));

        // Remove focus from GUI
        mainBox.requestFocus();

        // Disable buttons appropriately
        if (model.getCart().size() > 0) {
            completeSaleBut.setDisable(false);
        }

        else {
            completeSaleBut.setDisable(true);;
        }

        removeCartBut.setDisable(true);
        addCartBut.setDisable(true);
    }

}
