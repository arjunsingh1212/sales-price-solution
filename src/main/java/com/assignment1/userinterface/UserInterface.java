package com.assignment1.userinterface;

import com.assignment1.item.Item;

import java.util.Scanner;

public class UserInterface implements UserInterfaceHandler {
  @Override
  public String takeInput() {
    String str = "";
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Name: ");
    String name = sc.nextLine();
    if (!name.equals("")) str += "-name " + name;
    System.out.print("Enter Price: ");
    String price = sc.nextLine();
    if (!price.equals("")) str += "-price " + price;
    System.out.print("Enter Quantity: ");
    String quantity = sc.nextLine();
    if (!quantity.equals("")) str += "-quantity " + quantity;
    String type = sc.nextLine();
    if (!type.equals("")) str += "-type " + type;

    return str;
  }

  @Override
  public boolean showUsageMessage() {
    System.out.println("-name <first item name>\n" +
            "-price <price of first item>\n" +
            "-quantity <quantity of first item>\n" +
            "-type <type of first item>");
    return true;
  }

  @Override
  public boolean giveOutput(Item[] itemArr) {
    System.out.println("item name\t item prize\t sales tax liability per item\t final prize (sales tax + item prize)");
    for (Item item : itemArr) {
      System.out.println(item.getName() + "\t" + item.getPrice() + "\t" + item.getTax() + "\t" + item.getQuantity() * (item.getPrice() + item.getTax()));
    }
    return true;
  }
}
