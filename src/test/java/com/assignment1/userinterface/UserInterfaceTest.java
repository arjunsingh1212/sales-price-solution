package com.assignment1.userinterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.assignment1.item.Item;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserInterfaceTest {
  private UserInterfaceHandler userInterfaceObj;

  @BeforeEach
  void createObj() {
    userInterfaceObj = new UserInterface();
  }

  @Test
  @DisplayName("Test the Input taking scenario using Environment enum")
  void takeInputTest() {
    Environment env = Environment.TEST;
    assertEquals("-name DummyName -price 100 -quantity 100 -type raw",userInterfaceObj.takeInput(env));
  }

  @Test
  @DisplayName("Show Usage Message")
  void showUsageMessage() {
    assertTrue(userInterfaceObj.showUsageMessage());
  }

  @Test
  @DisplayName("Display details of items as output")
  void giveOutputTest() {
    Item item1 = new Item();
    Item item2 = new Item();
    ArrayList<Item> items = new ArrayList<Item>();
    items.add(item1);
    items.add(item2);
    assertTrue(userInterfaceObj.giveOutput(items));
  }
}
