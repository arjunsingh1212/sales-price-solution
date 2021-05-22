package com.assignment1.userinterface;

import com.assignment1.item.ItemEntity;
import java.util.List;
import java.util.Scanner;

/** Class to implement methods of user interface. */
@SuppressWarnings({"PMD.SystemPrintln", "UseStringBufferForStringAppends"})
public class UserInterface implements UserInterfaceHandler {

  /** Implement method to take input from user. */
  @Override
  public String takeInput(final Environment env) {
    final StringBuilder str = new StringBuilder();
    final String space = "";
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Enter Name: ");
      final String name = scanner.nextLine();
      if (!space.equals(name)) { //avoid adding option in the string if value blank
        str.append("-name ").append(name);
      }
      System.out.print("Enter Price: ");
      final String price = scanner.nextLine();
      if (!space.equals(price)) {
        str.append("-price ").append(price);
      }
      System.out.print("Enter Quantity: ");
      final String quantity = scanner.nextLine();
      if (!space.equals(quantity)) {
        str.append("-quantity ").append(quantity);
      }
      System.out.print("Enter Type: ");
      final String type = scanner.nextLine();
      if (!space.equals(type)) {
        str.append("-type ").append(type);
      }
    }
    return str.toString();
  }

  /** Implement method to show usage method. */
  @Override
  public boolean showUsageMessage() {
    System.out.println("\n\nEntered format is not correct.\n\n"
            + "Showing Usage:\n\nThe correct options and values are as follows "
            + "and type option can be any of the three values from [raw, manufactured, imported]."
            + "And also, name and type are mandatory options");
    System.out.println("-name <first item name>\n"
            + "-price <price of first item>\n"
            + "-quantity <quantity of first item>\n"
            + "-type <type of first item>\n");
    return true;
  }

  /** Implement method to give output. */
  @Override
  public boolean giveOutput(final List<ItemEntity> itemArr) {
    System.out.println("ItemName, ItemPrice, SalesTax, ItemQuantity, FinalPrice");
    for (final ItemEntity item : itemArr) {
      System.out.println(item.getName()
              + ", " + item.getPrice()
              + ", " + item.getTax()
              + ", " + item.getQuantity()
              + ", " + item.getQuantity() * (item.getPrice() + item.getTax()));
    }
    return true;
  }
}
