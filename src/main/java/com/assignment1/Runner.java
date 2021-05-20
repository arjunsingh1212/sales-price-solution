package com.assignment1;

import com.assignment1.item.Item;
import com.assignment1.parser.CommandLineParser;
import com.assignment1.parser.Parsable;
import com.assignment1.parser.Valid;
import com.assignment1.parser.Validity;
import com.assignment1.userinterface.UserInterface;
import com.assignment1.userinterface.UserInterfaceHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
  public static void main(String[] args) throws RuntimeException {
    String cliInput = "";
    for (String arg : args) {
      cliInput += arg + " ";  //Generating string from the cmd args
    }

    ArrayList<Item> itemArray = new ArrayList<Item>(); //ArrayList to store the Items in order to display later

    Valid validObj = new Validity(); //Object having implementation for validation
    Parsable parserObj = new CommandLineParser(validObj); //Parser with cli implementation
    UserInterfaceHandler uiObj = new UserInterface();  //Object to deal with User I/O

    try {
      Item item = parserObj.parse(cliInput); //Parse the initial Command Line Arguments (string)
      double taxOutput = item.calculateTax(); //Calculate Tax, which is the requirement according to problem statement
      item.setTax(taxOutput);  //Populate the Item's tax property using setter
      itemArray.add(item);  //Add item to our list of items
    } catch (RuntimeException e) {
      uiObj.showUsageMessage(); //Show message to customer how to use the application and format to give input in CLI
    }

    while (true) {  //Loop to prompt user to give input until he preses n for No More Input
      System.out.print("\nDo you want to enter details of any other item (y/n): ");
      Scanner sc = new Scanner(System.in);
      char inputYesNo = sc.next().charAt(0); //Input a character response from user
      switch (inputYesNo) {
        case 'y':
          cliInput = uiObj.takeInput(); //Takes input from user by prompting to enter values
          try {  //Same logic as explained before while loop
            Item item = parserObj.parse(cliInput);
            double taxOutput = item.calculateTax();
            item.setTax(taxOutput);
            itemArray.add(item);
          } catch (Exception e) {
            uiObj.showUsageMessage();
          }
          break;
        case 'n':
          System.out.println("\nThank You.. Your Item details are as follows.");
          uiObj.giveOutput(itemArray);
          System.exit(0);
          break;
        default:
          System.out.println("Sorry, invalid input.");
      }
    }
  }
}
