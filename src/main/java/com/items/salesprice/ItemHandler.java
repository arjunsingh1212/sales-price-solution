package com.items.salesprice;

public interface ItemHandler {

  String getName();

  void setName(String name);

  String getType();

  void setType(String type);

  double getPrice();

  void setPrice(double price);

  int getQuantity();

  void setQuantity(int quantity);

  double getTax();

  void setTax(double tax);
}
