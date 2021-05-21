package com.assignment1.userinterface;

import com.assignment1.item.ItemEntity;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface implements UserInterfaceHandler {
  @Override
  public String takeInput(final Environment env) {
    String str = "";
    final String space = "";
    final Scanner scanner = new Scanner(System.in);
    try {
      System.out.print("Enter Name: ");
      final String name = scanner.nextLine();
      if (!space.equals(name)) { //avoid adding option in the string if value blank
        str += "-name " + name;
      }
      System.out.print("Enter Price: ");
      final String price = scanner.nextLine();  //Dummy value 100 for Testing
      if (!space.equals(price)) {
        str += " -price " + price;
      }
      System.out.print("Enter Quantity: ");
      final String quantity = scanner.nextLine();
      if (!space.equals(quantity)) {
        str += " -quantity " + quantity;
      }
      System.out.print("Enter Type: ");
      final String type = scanner.nextLine();
      if (!space.equals(type)) {
        str += " -type " + type;
      }
    } finally {
      scanner.close();
    }
    return str;
  }

  @Override
  public boolean showUsageMessage() {
    System.out.println("\n\nEntered format is not correct.\n\n" +
            "Showing Usage:\n\nThe correct options and values are as follows " +
            "and type option can be any of the three values from [raw, manufactured, imported]." +
            "And also, name and type are mandatory options");
    System.out.println("-name <first item name>\n" +
            "-price <price of first item>\n" +
            "-quantity <quantity of first item>\n" +
            "-type <type of first item>\n");
    return true;
  }

  @Override
  public boolean giveOutput(final ArrayList<ItemEntity> itemArr) {
    System.out.println("ItemName, ItemPrice, SalesTax, ItemQuantity, FinalPrice");
    for (final ItemEntity item : itemArr) {
      System.out.println(item.getName() +
              ", " + item.getPrice() +
              ", " + item.getTax() +
              ", " + item.getQuantity() +
              ", " + item.getQuantity() * (item.getPrice() + item.getTax()));
    }
    return true;
  }
}
