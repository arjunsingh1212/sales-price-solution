package com.assignment1.item;

public class Item {
  private String name, type;
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

  public boolean comparison(Item obj) {
    return name.equals(obj.name) && price == obj.price && quantity == obj.quantity && type.equals(obj.type);
  }

  public double calculateTax() {
    switch (type) {
      case "raw":
        tax = 0.125 * price;
        break;
      case "manufactured":
        tax = 0.125 * price + 0.02 * (price + 0.125 * price);
        break;
      case "imported":
        double importDuty = 0.1 * price;
        if (price + importDuty <= 100) {
          tax = importDuty + 5;
        } else if (price + importDuty > 100 && price + importDuty <= 200) {
          tax = importDuty + 10;
        } else if (price + importDuty > 200) {
          tax = importDuty + 0.05 * (price + importDuty);
        }
        break;
    }
    return tax;
  }
}
