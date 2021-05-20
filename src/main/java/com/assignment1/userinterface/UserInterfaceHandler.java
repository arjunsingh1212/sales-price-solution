package com.assignment1.userinterface;

import com.assignment1.item.Item;
import java.util.ArrayList;

public interface UserInterfaceHandler {
  String takeInput();

  boolean showUsageMessage();

  boolean giveOutput(ArrayList<Item> itemArr);
}
