package com.assignment1.item;

/** Class to implement TaxCalculation logic */
public class TaxCalculator implements Taxable {

  /** Implementation for calculateTax **/
  @Override
  public double calculateTax(final ItemEntity item) {
    final String type = item.getType();
    double tax = 0;
    final double price = item.getPrice();
    switch (type) {
      case "raw":
        tax = 0.125 * price; //12.5% of price
        break;
      case "manufactured":
        tax = 0.125 * price + 0.02 * (price + 0.125 * price);  //12.5% + 2% of the result
        break;
      case "imported":
        final int surcharge100 = 100;
        final int surcharge200 = 200;
        final double importDuty = 0.1 * price;
        final double finalCost = price + importDuty; //Further additional surcharge to be added according to final cost
        if (finalCost <= surcharge100) {
          tax = finalCost + 5 - price;  //subtract price to have only tax, not final price
        } else if (finalCost > surcharge100 && finalCost <= surcharge200) {
          tax = finalCost + 10 - price;
        } else if (finalCost > surcharge200) {
          tax = finalCost + 0.05 * finalCost - price;
        }
        break;
      default:
        break;
    }
    return tax;
  }
}
