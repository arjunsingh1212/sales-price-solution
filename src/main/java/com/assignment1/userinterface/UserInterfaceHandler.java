package com.assignment1.userinterface;

import com.assignment1.item.Item;
import java.util.ArrayList;

public interface UserInterfaceHandler {
  String takeInput(Environment env); //Environment is the enum which is runtime enviroment for Application : TEST or PROD

  boolean showUsageMessage();

  boolean giveOutput(ArrayList<Item> itemArr);
}
