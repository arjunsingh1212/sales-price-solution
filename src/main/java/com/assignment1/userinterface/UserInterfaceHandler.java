package com.assignment1.userinterface;

import com.assignment1.item.Item;

public interface UserInterfaceHandler {
  String takeInput();
  boolean showUsageMessage();
  boolean giveOutput(Item[] itemArr);
}
