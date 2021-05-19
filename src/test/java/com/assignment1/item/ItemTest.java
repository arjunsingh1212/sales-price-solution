package com.assignment1.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

}