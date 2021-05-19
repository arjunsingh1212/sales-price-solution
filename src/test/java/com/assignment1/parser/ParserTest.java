package com.assignment1.parser;

import static org.junit.jupiter.api.Assertions.*;

import com.assignment1.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ParserTest {

  private Parser parserObj;

  @BeforeEach
  void createObj() {
    parserObj = new CommandLineParser();
  }

  @Test
  @DisplayName("Simple input test")
  void parseTest1() {
    Item expectedItem = new Item("Book",50,3,"imported");
    Item actual = parserObj.parse("-name Book -price 50 -quantity 3 -type imported");
    assertEquals(expectedItem.toString(),actual.toString(),"Should parse correctly");
  }

  @Test
  @DisplayName("Input with 2 words in name")
  void parseTest2() {
    Item expectedItem = new Item("Note Book",50,3,"imported");
    Item actual = parserObj.parse("-name Note Book -price 50 -quantity 3 -type imported");
    assertEquals(expectedItem.toString(),actual.toString(),"Should parse correctly");
  }

  @Test
  @DisplayName("Input with multiple words in name")
  void parseTest3() {
    Item expectedItem = new Item("Rough Note Book",50,3,"imported");
    Item actual = parserObj.parse("-name Rough Book -price 50 -quantity 3 -type imported");
    assertEquals(expectedItem.toString(),actual.toString(),"Should parse correctly");
  }

  @Test
  @DisplayName("Input with no explicit price option")
  void parseTest4() {
    Item expectedItem = new Item("Book",0,3,"imported");
    Item actual = parserObj.parse("-name Book -quantity 3 -type raw");
    assertEquals(expectedItem.toString(),actual.toString(),"Should parse correctly");
  }

  @Test
  @DisplayName("Input with no explicit quantity option")
  void parseTest() {
    Item expectedItem = new Item("Book",50,1,"imported");
    Item actual = parserObj.parse("-name Book -price 50 -type imported");
    assertEquals(expectedItem.toString(),actual.toString(),"Should parse correctly");
  }

}