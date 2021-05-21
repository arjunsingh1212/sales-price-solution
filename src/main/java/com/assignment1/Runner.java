package com.assignment1;

import com.assignment1.exceptions.RuntimeExceptionCustom;
import com.assignment1.item.ItemEntity;
import com.assignment1.item.TaxCalculator;
import com.assignment1.item.Taxable;
import com.assignment1.parser.CommandLineParser;
import com.assignment1.parser.Parsable;
import com.assignment1.parser.Valid;
import com.assignment1.parser.Validity;
import com.assignment1.userinterface.Environment;
import com.assignment1.userinterface.UserInterface;
import com.assignment1.userinterface.UserInterfaceHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Runner class having main function to run the application
 * (Acting as controller)
 **/
public final class Runner {
  private Runner() {
  }

  /**
   * main function as starting point while running application
   */
  public static void main(final String[] args) throws IOException {
    final StringBuilder cliInputBuilder = new StringBuilder();
    for (final String arg : args) {
      cliInputBuilder.append(arg).append(" ");  //Generating string from the cmd args
    }
    String cliInput = cliInputBuilder.toString();

    final ArrayList<ItemEntity> itemArray = new ArrayList<>(); //ArrayList to store the Items in order to display later

    final Valid validObj = new Validity(); //Object having implementation for validation
    final Parsable parserObj = new CommandLineParser(validObj); //Parser with cli implementation
    final UserInterfaceHandler uiObj = new UserInterface();  //Object to deal with User I/O
    final Taxable taxObj = new TaxCalculator();

    ItemEntity item;
    double taxOutput;

    try {
      item = parserObj.parse(cliInput); //Parse the initial Command Line Arguments (string)
      taxOutput = taxObj.calculateTax(item); //Calculate Tax, which is the requirement according to problem statement
      item.setTax(taxOutput);  //Populate the Item's tax property using setter
      itemArray.add(item);  //Add item to our list of items
    } catch (RuntimeExceptionCustom e) {
      //Show  customer how to use the application and format to give input in CLI
      uiObj.showUsageMessage();
    }

    char inputYesNo = 'y';
    final BufferedReader buffread = new BufferedReader(new InputStreamReader(System.in));
    try {
      while (inputYesNo == 'y') {  //Loop to prompt user to give input until he preses n for No More Input
        cliInput = uiObj.takeInput(Environment.PROD); //input from user by prompting to enter values in PROD env
        try {  //Same logic as explained before while loop
          item = parserObj.parse(cliInput);
          taxOutput = taxObj.calculateTax(item);
          item.setTax(taxOutput);
          itemArray.add(item);
        } catch (RuntimeExceptionCustom e) {
          System.out.println(e.getMessage());
          uiObj.showUsageMessage();
        }
        while (true) {
          System.out.print("\nDo you want to enter details of any other item (y/n): ");
          inputYesNo = buffread.readLine().charAt(0); //Input a character response from user
          if (inputYesNo == 'n' || inputYesNo == 'y') {
            break;
          }
          else {
            System.out.println("Sorry, invalid input.");
          }
        }
      }
    }
    catch (IOException except) {
      System.out.println(except.getMessage());
    } finally {
      buffread.close();
    }

    System.out.println("\nThank You.. Your Item details are as follows.");
    uiObj.giveOutput(itemArray);
  }
}
