package com.assignment1.item;

import com.assignment1.Type;

/**
 * Class to implement Tax Calculation logic.
 */
@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public class TaxCalculator implements Taxable {

  /**
   * Implementation for calculateTax.
   **/
  @Override
  public double calculateTax(final ItemEntity item) {
    final Type type = item.getType();
    final double price = item.getPrice();
    double tax = 0;
    switch (type) {
      case RAW:
        tax = 0.125 * price; //12.5% of price
        break;
      case MANUFACTURED:
        tax = 0.125 * price + 0.02 * (price + 0.125 * price);  //12.5% + 2% of the result
        break;
      case IMPORTED:
        final int surcharge100 = 100;
        final int surcharge200 = 200;
        final double importDuty = 0.1 * price;
        //Further additional surcharge to be added according to final cost
        final double finalCost = price + importDuty;
        if (finalCost <= surcharge100) {
          tax = finalCost + 5;
        } else if (finalCost > surcharge100 && finalCost <= surcharge200) {
          tax = finalCost + 10;
        } else if (finalCost > surcharge200) {
          tax = finalCost + 0.05 * finalCost;
        }
        tax -= price; //subtract price to have only tax, not final price
        break;
      default:
        break;
    }
    return tax;
  }
}
