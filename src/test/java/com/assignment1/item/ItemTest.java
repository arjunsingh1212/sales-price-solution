package com.assignment1.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {

  private Item item;

  @BeforeEach
  void createObj() {
    item = new Item();
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
    item.setType("raw");
    assertEquals("raw", item.getType());
  }

  @Test
  void calculateTaxRaw() {
    Item item = new Item("Book",50,3,"raw");
    assertEquals(6.25,item.calculateTax());
  }

  @Test
  void calculateTaxManufactured() {
    Item item = new Item("Book",50,3,"manufactured");
    assertEquals(7.375,item.calculateTax());
  }

  @Test
  void calculateTaxImportedBranch1() {
    Item item = new Item("Book",50,3,"imported");
    assertEquals(10,item.calculateTax());
  }

  @Test
  void calculateTaxImportedBranch2() {
    Item item = new Item("Book",100,3,"imported");
    assertEquals(20,item.calculateTax());
  }

  @Test
  void calculateTaxImportedBranch3() {
    Item item = new Item("Book",200,3,"imported");
    assertEquals(31,item.calculateTax());
  }

}