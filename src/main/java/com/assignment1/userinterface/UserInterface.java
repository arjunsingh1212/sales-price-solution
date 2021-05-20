package com.assignment1.userinterface;

import com.assignment1.item.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface implements UserInterfaceHandler {
  @Override
  public String takeInput() {
    String str = "";
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Name: ");
    String name = sc.nextLine();
    if (!name.equals("")) { //avoid adding option in the string if value blank
      str += "-name " + name;
    }
    System.out.print("Enter Price: ");
    String price = sc.nextLine();
    if (!price.equals("")) {
      str += " -price " + price;
    }
    System.out.print("Enter Quantity: ");
    String quantity = sc.nextLine();
    if (!quantity.equals("")) {
      str += " -quantity " + quantity;
    }
    System.out.print("Enter Type: ");
    String type = sc.nextLine();
    if (!type.equals("")) {
      str += " -type " + type;
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
  public boolean giveOutput(ArrayList<Item> itemArr) {
    System.out.println("ItemName, ItemPrice, SalesTax, ItemQuantity, FinalPrice");
    for (Item item : itemArr) {
      System.out.println(item.getName() +
              ", " + item.getPrice() +
              ", " + item.getTax() +
              ", " + item.getQuantity() +
              ", " + item.getQuantity() * (item.getPrice() + item.getTax()));
    }
    return true;
  }
}
