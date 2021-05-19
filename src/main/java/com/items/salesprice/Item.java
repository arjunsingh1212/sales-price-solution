package com.items.salesprice;

public class Item implements ItemHandler{
  private String name, type;
  private int quantity;
  private double price;
  private double tax;

  Item() {
    name = "";
    type = "";
    quantity = 1;
    price = 0.0;
    tax = 0.0;
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
}
