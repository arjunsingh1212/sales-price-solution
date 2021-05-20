package com.assignment1.userinterface;

import static org.junit.jupiter.api.Assertions.*;

import com.assignment1.item.Item;
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
  @DisplayName("Show Usage Message")
  void showUsageMessage() {
    assertTrue(userInterfaceObj.showUsageMessage());
  }

  @Test
  @DisplayName("Display details of items as output")
  void giveOutputTest() {
    Item item1 = new Item();
    Item item2 = new Item();
    Item[] items = {item1, item2};
    assertTrue(userInterfaceObj.giveOutput(items));
  }
}
