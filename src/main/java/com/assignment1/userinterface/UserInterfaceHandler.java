package com.assignment1.userinterface;

import com.assignment1.item.ItemEntity;

import java.util.ArrayList;

public interface UserInterfaceHandler {
  //Environment is the enum which is runtime enviroment for Application
  // : TEST or PROD
  String takeInput(Environment env);

  boolean showUsageMessage();

  boolean giveOutput(ArrayList<ItemEntity> itemArr);
}
