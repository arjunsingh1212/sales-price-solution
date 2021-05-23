package com.assignment1;

import com.assignment1.exceptions.RuntimeExceptionCustom;
import com.assignment1.item.ItemEntity;
import com.assignment1.item.TaxCalculator;
import com.assignment1.item.Taxable;
import com.assignment1.parser.Valid;
import com.assignment1.parser.Validator;
import com.assignment1.userinterface.UserInterface;
import com.assignment1.userinterface.UserInterfaceHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Runner class having main function to run the application.
 * (Acting as controller)
 **/
@SuppressWarnings({"PMD.SystemPrintln"})
public final class Runner {

  /** ArrayList to store the Items in order to display later. */
  private static final List<ItemEntity> ITEM_ARRAY = new ArrayList<>();

  /** YES Constant for input as 'y' */
  private static final String RESPONSE_YES = "y";
  /** NO Constant for input as 'n' */
  private static final String RESPONSE_NO = "n";

  //Private Constructor so that it can't be instantiated.
  private Runner() {
  }

  //Method to process the input given on starting application
  private static void processCommandLineArgs(final Valid validObj,
                              final UserInterfaceHandler uiObj,
                              final Taxable taxObj,
                              final String... args) {
    ItemEntity item;
    double taxOutput;
    if (args.length > 0) {
      try {
        item = validObj.validate(args); //Parse the initial Command Line Arguments
        //Calculate Tax, which is the requirement according to problem statement
        taxOutput = taxObj.calculateTax(item);
        item.setTax(taxOutput);  //Populate the Item's tax property using setter
        ITEM_ARRAY.add(item);  //Add item to our list of items
      } catch (RuntimeExceptionCustom e) {
        //Show  customer how to use the application and format to give input in CLI
        uiObj.showUsageMessage();
      }
    }
  }

  //Method to process the input given while running application
  private static void processUserInterfaceArgs(final UserInterfaceHandler uiObj,
                                               final Taxable taxObj) {
    ItemEntity item;
    double taxOutput;
    try {
      item = uiObj.takeInput();
      taxOutput = taxObj.calculateTax(item);
      item.setTax(taxOutput);
      ITEM_ARRAY.add(item);
    } catch (InputMismatchException e) {
      System.out.println("Your input did not match the expected format");
    } catch (IllegalArgumentException e) {
      System.out.println("You input did not match the expected input");
    } catch (RuntimeExceptionCustom e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * main function as starting point while running application.
   */
  public static void main(final String[] args) {

    final Valid validObj = new Validator(); //Object having implementation for validation
    final UserInterfaceHandler uiObj = new UserInterface();  //Object to deal with User I/O
    final Taxable taxObj = new TaxCalculator(); //Object for tax calculator class

    processCommandLineArgs(validObj,uiObj,taxObj,args);

    String inputYesNo;

    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
      while (true) {
        //Loop to prompt user to give input until he preses n for No More Input
        while (true) {
          System.out.print("\nDo you want to enter details of any other item (y/n): ");
          inputYesNo = bufferedReader.readLine();
          if (RESPONSE_NO.equals(inputYesNo) || RESPONSE_YES.equals(inputYesNo)) {
            break;
          } else {
            System.out.println("Sorry, invalid input.");
          }
        }

        if (RESPONSE_NO.equals(inputYesNo)) {
          break;
        }

        processUserInterfaceArgs(uiObj,taxObj);
        
      }
    } catch (IOException except) {
      System.out.println(except.getMessage());
    }

    System.out.println("\nThank You.. Your Item details are as follows.");
    uiObj.giveOutput(ITEM_ARRAY);
  }
}
