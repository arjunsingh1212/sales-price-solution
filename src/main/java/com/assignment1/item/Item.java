package com.assignment1.item;

public class Item {
  private String name;
  private String type;
  private int quantity;
  private double price;
  private double tax;

  public Item() {
    name = "";
    type = "";
    quantity = 1;
    price = 0.0;
    tax = 0.0;
  }

  public Item(String name, double price, int quantity, String type) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.type = type;
    this.tax = 0;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getTax() {
    return tax;
  }

  public void setTax(double tax) {
    this.tax = tax;
  }

  public String toString() {
    return name + price + quantity + type;
  }

  public double calculateTax() {
    switch (type) {
      case "raw":
        tax = 0.125 * price; //12.5% of price
        break;
      case "manufactured":
        tax = 0.125 * price + 0.02 * (price + 0.125 * price);  //12.5% + 2% of the result
        break;
      case "imported":
        double importDuty = 0.1 * price;
        double finalCost = price + importDuty; //Further additional surcharge to be added according to final cost
        if (finalCost <= 100) {
          tax = finalCost + 5 - price;  //subtract price to have only tax, not final price
        } else if (finalCost > 100 && finalCost <= 200) {
          tax = finalCost + 10 - price;
        } else if (finalCost > 200) {
          tax = finalCost + 0.05 * finalCost - price;
        }
        break;
      default:
        break;
    }
    return tax;
  }
}
