package com.assignment1.item;

public class TaxCalculator implements Taxable {
  @Override
  public double calculateTax(Item item) {
    String type = item.getType();
    double tax = 0;
    double price = item.getPrice();
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
