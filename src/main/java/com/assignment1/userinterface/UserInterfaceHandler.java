package com.assignment1.userinterface;

import com.assignment1.exceptions.RuntimeExceptionCustom;
import com.assignment1.item.ItemEntity;
import java.util.List;

/** Interface to declare methods. */
public interface UserInterfaceHandler {

  /** Method to take input. */
  ItemEntity takeInput() throws RuntimeExceptionCustom;

  /** Method to show message. */
  boolean showUsageMessage();

  /** Method to give output. */
  boolean giveOutput(List<ItemEntity> itemArray);
}
