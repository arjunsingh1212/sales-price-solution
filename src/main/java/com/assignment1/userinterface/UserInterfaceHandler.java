package com.assignment1.userinterface;

import com.assignment1.item.ItemEntity;
import java.util.List;

/** Interface to declare methods. */
public interface UserInterfaceHandler {
  //Environment is the enum which is runtime environment for Application
  //TEST or PROD
  /** Method to take input. */
  String takeInput(Environment env);

  /** Method to show message. */
  boolean showUsageMessage();

  /** Method to give output. */
  boolean giveOutput(List<ItemEntity> itemArray);
}
