package com.assignment1.userinterface;

import com.assignment1.Type;
import com.assignment1.exceptions.RuntimeExceptionCustom;
import com.assignment1.item.ItemEntity;
import java.util.List;
import java.util.Scanner;

/**
 * Class to implement methods of user interface.
 */
@SuppressWarnings("PMD.SystemPrintln")
public class UserInterface implements UserInterfaceHandler {

  /**
   * Outputs the string after taking input iff it is valid name.
   */
  private String validName(final String name) throws RuntimeExceptionCustom {
    if (name.matches("^[ A-Za-z]+$") && name.length() <= 20) {
      return name;
    } else {
      throw new RuntimeExceptionCustom("Name format wrong");
    }
  }

  /**
   * Implement method to take input from user.
   */
  @Override
  public ItemEntity takeInput() throws RuntimeExceptionCustom {
    final ItemEntity item = new ItemEntity();
    final Scanner scanner = new Scanner(System.in);
    System.out.print("Enter Name: ");
    final String name = validName(scanner.nextLine());
    item.setName(name);
    System.out.print("Enter Price: ");
    final double price = scanner.nextDouble();
    item.setPrice(price);
    System.out.print("Enter Quantity: ");
    final int quantity = scanner.nextInt();
    item.setQuantity(quantity);
    System.out.print("Enter Type: ");
    final Type type = Type.valueOf(scanner.next());
    item.setType(type);
    return item;
  }

  /**
   * Implement method to show usage method.
   */
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

  /**
   * Implement method to give output.
   */
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
