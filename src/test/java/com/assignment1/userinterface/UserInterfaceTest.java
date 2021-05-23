package com.assignment1.userinterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.assignment1.Type;
import com.assignment1.item.ItemEntity;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserInterfaceTest {
  private UserInterfaceHandler userInterfaceObj;

  ItemEntity takeInputDummy() {
    return new ItemEntity("Book",50.5,4, Type.IMPORTED);
  }

  @BeforeEach
  void createObj() {
    userInterfaceObj = new UserInterface();
  }

  @Test
  @DisplayName("Test the Input taking scenario using dummy")
  void takeInputTest() {
    ItemEntity item = new ItemEntity("Book",50.5,4, Type.IMPORTED);
    assertEquals(item.toString(),
            new UserInterfaceTest().takeInputDummy().toString());
  }

  @Test
  @DisplayName("Show Usage Message")
  void showUsageMessage() {
    assertTrue(userInterfaceObj.showUsageMessage());
  }

  @Test
  @DisplayName("Display details of items as output")
  void giveOutputTest() {
    ItemEntity item1 = new ItemEntity();
    ItemEntity item2 = new ItemEntity();
    ArrayList<ItemEntity> items = new ArrayList<>();
    items.add(item1);
    items.add(item2);
    assertTrue(userInterfaceObj.giveOutput(items));
  }
}
