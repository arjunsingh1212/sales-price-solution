package com.assignment1.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.assignment1.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {

  private ItemEntity item;
  private Taxable itemTax;

  @BeforeEach
  void createObj() {
    item = new ItemEntity();
  }

  @Test
  void nameTest() {
    item.setName("Book");
    assertEquals("Book", item.getName());
  }

  @Test
  void taxTest() {
    item.setTax(5.0);
    assertEquals(5.0, item.getTax());
  }

  @Test
  void quantityTest() {
    item.setQuantity(5);
    assertEquals(5, item.getQuantity());
  }

  @Test
  void priceTest() {
    item.setPrice(50.5);
    assertEquals(50.5, item.getPrice());
  }

  @Test
  void typeTest() {
    item.setType(Type.RAW);
    assertEquals(Type.RAW, item.getType());
  }

  @Test
  void calculateTaxRaw() {
    item = new ItemEntity("Book", 50, 3, Type.RAW);
    itemTax = new TaxCalculator();
    assertEquals(6.25, itemTax.calculateTax(item));
  }

  @Test
  void calculateTaxManufactured() {
    item = new ItemEntity("Book", 50, 3, Type.MANUFACTURED);
    itemTax = new TaxCalculator();
    assertEquals(7.375, itemTax.calculateTax(item));
  }

  @Test
  void calculateTaxImportedBranch1() {
    item = new ItemEntity("Book", 50, 3, Type.IMPORTED);
    itemTax = new TaxCalculator();
    assertEquals(10, itemTax.calculateTax(item));
  }

  @Test
  void calculateTaxImportedBranch2() {
    item = new ItemEntity("Book", 100, 3, Type.IMPORTED);
    itemTax = new TaxCalculator();
    assertEquals(20, itemTax.calculateTax(item));
  }

  @Test
  void calculateTaxImportedBranch3() {
    item = new ItemEntity("Book", 200, 3, Type.IMPORTED);
    itemTax = new TaxCalculator();
    assertEquals(31, itemTax.calculateTax(item));
  }

}