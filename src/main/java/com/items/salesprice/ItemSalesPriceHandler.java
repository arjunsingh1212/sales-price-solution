package com.items.salesprice;

public interface ItemSalesPriceHandler {
  void extractOptionsValues(String clientInput, Item item); //It will extract all values of options and set the Item object;
  void calculateTax(Item item);
  void displayDetails(Item item);
}
