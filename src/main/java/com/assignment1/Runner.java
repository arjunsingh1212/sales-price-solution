package com.assignment1;

import com.assignment1.exceptions.GenericApplicationException;
import com.assignment1.item.ItemEntity;
import com.assignment1.parser.CommandLineParser;
import com.assignment1.parser.Parsable;
import com.assignment1.parser.Valid;
import com.assignment1.parser.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Runner class having main function to run the application.
 * (Acting as controller)
 **/
@SuppressWarnings({"PMD.SystemPrintln"})
public final class Runner {

  /** validator Object having implementation for validation. */
  final static Valid validator = new Validator();

  /** parser object to parse the input data. */
  final static Parsable parser = new CommandLineParser(validator);

  /**
   * ArrayList to store the Items in order to display later.
   */
  private static final List<ItemEntity> ITEM_ARRAY = new ArrayList<>();

  /**
   * YES Constant for input as 'y'
   */
  private static final String RESPONSE_YES = "y";
  /**
   * NO Constant for input as 'n'
   */
  private static final String RESPONSE_NO = "n";

  /**
   * Constant for Usage message
   */
  private static final String USAGE = "\n\nEntered format is not correct.\n\n"
          + "Showing Usage:\n\nThe correct options and values are as follows "
          + "and type option can be any of the three values from "
          + "[raw, manufactured, imported]."
          + "And also, name and type are mandatory options"
          + "-name <first item name>\n"
          + "-price <price of first item>\n"
          + "-quantity <quantity of first item>\n"
          + "-type <type of first item>\n";

  /** Constant of Logger type (Using Apache log4j framework */
  private static final Logger logger = LogManager.getLogger(Runner.class);

  //Private Constructor so that it can't be instantiated.
  private Runner() {
  }

  //Method to process the input given on starting application
  private static void processCommandLineArgs(final Parsable parser,
                                             final String... args) {
    ItemEntity item;
    if (args.length > 0) {
      try {
        item = parser.parse(args); //Parse the initial Command Line Arguments
        ITEM_ARRAY.add(item);  //Add item to our list of items
      } catch (GenericApplicationException e) {
        // Show Exception Context
        // Show customer how to use the application and format to give input in CLI
        logger.error(e.getMessage());
        System.out.println(USAGE);
      }
    }
  }

  //Method to process the input given while running application
  private static void processUserInterfaceArgs(final Parsable parser) {
    ItemEntity item = new ItemEntity();
    try {
      item = parser.parse(item.read().toArray()); //Parsing the string form of DTO (item.read())
      ITEM_ARRAY.add(item);
    } catch (GenericApplicationException | IOException e) {
      // Show Exception Context
      // Show customer how to use the application and format to give input in CLI
      logger.error(e.getMessage());
      System.out.println(USAGE);
    }
  }

  /**
   * main function as starting point while running application.
   */
  public static void main(final String[] args) {

    processCommandLineArgs(parser, args);

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

        processUserInterfaceArgs(parser);

      }
    } catch (IOException except) {
      System.out.println(except.getMessage());
    }

    System.out.println("\nThank You.. Your Item details are as follows.");
    System.out.println("ItemName, ItemPrice, SalesTax, ItemQuantity, FinalPrice");
    for (final ItemEntity item : ITEM_ARRAY) {
      item.write();
    }
  }
}
