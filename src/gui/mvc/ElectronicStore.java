//Class representing an electronic store
//Has an array of products that represent the items the store can sell
package gui.mvc;

import java.util.Scanner;
import java.util.ArrayList;

public class ElectronicStore{
  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private int curProducts;
  String name;
  Product[] stock; //Array to hold all products
  double revenue;
  private double prevRevenue;
  private int saleNum;
  ArrayList<Product> cart;
  private double cartValue;

  public ElectronicStore(String initName){
    revenue = 0.0;
    name = initName;
    stock = new Product[MAX_PRODUCTS];
    curProducts = 0;
    saleNum = 0;
    cart = new ArrayList<Product>();
    cartValue = 0.00;
  }
  
  public String getName(){
    return name;
  }
  
  //Adds a product and returns true if there is space in the array
  //Returns false otherwise
  public boolean addProduct(Product newProduct){
    if(curProducts < MAX_PRODUCTS){
     stock[curProducts] = newProduct;
     curProducts++;
     return true;
    }
    return false;
  }

  public void addToCart(Product newProduct) {
    cart.add(newProduct);
    cartValue += newProduct.getPrice();
    newProduct.setStockQuantity(newProduct.getStockQuantity() - 1);
  }

  public ArrayList<Product> getCart() {
    return cart;
  }

  public void removeFromCart(Product newProduct) {
    cart.remove(newProduct);
    cartValue -= newProduct.getPrice();
    newProduct.setStockQuantity(newProduct.getStockQuantity() + 1);
  }

  public void completeSale(ArrayList<Product> completeCart) {
    prevRevenue = revenue;
    for (int i = 0; i < completeCart.size(); i++) {
      if ((completeCart.get(i) != null) && (completeCart.size() > 0)) {
        revenue += completeCart.get(i).sellUnits(1);
        if (completeCart.get(i).getStockQuantity() == 0) {
          revenue += completeCart.get(i).getPrice() * 1;
          completeCart.get(i).setSoldQuantity(completeCart.get(i).getSoldQuantity() + 1);
        }
      }
    }
    if (prevRevenue != revenue) {
      saleNum += 1;
    }

    completeCart.clear();
    cartValue = 0.00;
  }

  public void reset() {
    // Reset sale numbers
    saleNum = 0;
    // Reset revenue
    revenue = 0.0;
    // Reset cart list
    if (cart.size() > 0) {
      for (int i = 0; i < cart.size(); i++) {
        if (cart.get(i) != null) {
          removeFromCart(cart.get(i));
        }
      }
    }
    cart.clear();
    // Reset Cart Value
    cartValue = 0.00;
  }

  //Sells 'amount' of the product at 'index' in the stock array
  //Updates the revenue of the store
  //If no sale occurs, the revenue is not modified
  //If the index is invalid, the revenue is not modified
  //Record the number of sales made
  public void sellProducts(int index, int amount){
    if(index >= 0 && index < curProducts){
      revenue += stock[index].sellUnits(amount);
    }
  }

  public int getSaleNum() {
    return saleNum;
  }

  public double getCartValue() {
    return cartValue;
  }

  public double getRevenue(){
    return revenue;
  }

  public Product[] getStock() {
    return stock;
  }
  
  //Prints the stock of the store
  public void printStock(){
    for(int i = 0; i < curProducts; i++){
      System.out.println(i + ". " + stock[i] + " (" + stock[i].getPrice() + " dollars each, " + stock[i].getStockQuantity() + " in stock, " + stock[i].getSoldQuantity() + " sold)");
    }
  }
  
  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }
} 