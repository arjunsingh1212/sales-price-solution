package com.assignment1.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.assignment1.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ItemTest {

  private ItemEntity item;

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
    item.setTax(BigDecimal.valueOf(5.0));
    assertEquals(BigDecimal.valueOf(5.0), item.getTax());
  }

  @Test
  void quantityTest() {
    item.setQuantity(5);
    assertEquals(5, item.getQuantity());
  }

  @Test
  void priceTest() {
    item.setPrice(BigDecimal.valueOf(50.50));
    assertEquals(BigDecimal.valueOf(50.50), item.getPrice());
  }

  @Test
  void typeTest() {
    item.setType(Type.RAW);
    assertEquals(Type.RAW, item.getType());
  }

  @Test
  void calculateTaxRaw() {
    item = new ItemEntity("Book", BigDecimal.valueOf(50), 3, Type.RAW);
    item.calculateTax();
    assertEquals(6.25,item.getTax().doubleValue());
  }

  @Test
  void calculateTaxManufactured() {
    item = new ItemEntity("Book", BigDecimal.valueOf(50), 3, Type.MANUFACTURED);
    item.calculateTax();
    assertEquals(7.375,item.getTax().doubleValue());
  }

  @Test
  void calculateTaxImportedBranch1() {
    item = new ItemEntity("Book", BigDecimal.valueOf(50), 3, Type.IMPORTED);
    item.calculateTax();
    assertEquals(10,item.getTax().doubleValue());
  }

  @Test
  void calculateTaxImportedBranch2() {
    item = new ItemEntity("Book", BigDecimal.valueOf(100), 3, Type.IMPORTED);
    item.calculateTax();
    assertEquals(20,item.getTax().doubleValue());
  }

  @Test
  void calculateTaxImportedBranch3() {
    item = new ItemEntity("Book", BigDecimal.valueOf(200), 3, Type.IMPORTED);
    item.calculateTax();
    assertEquals(31,item.getTax().doubleValue());
  }

}