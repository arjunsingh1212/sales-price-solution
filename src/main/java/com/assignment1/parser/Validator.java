package com.assignment1.parser;

import com.assignment1.Type;
import com.assignment1.exceptions.RuntimeExceptionCustom;
import com.assignment1.item.ItemEntity;

/**
 * Class to implement Valid Interface for validation methods.
 */
@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public class Validator implements Valid {

  /**
   * Custom Exception class object.
   */
  private final transient RuntimeExceptionCustom customException =
          new RuntimeExceptionCustom("Invalid Arguments Exception");

  /**
   * Checks the format according to option(name, price, quantity, type.
   */
  public boolean checkFormat(final String option, final String str) {

    boolean result = false;

    //Price can have one decimal point and only digits, no minus symbol (negative)
    //Price value length <= 10
    if ("-price".equals(option)) {
      result = str.indexOf('.') == str.lastIndexOf('.')
              && str.matches("[0-9.]*") && str.length() <= 10;
    }

    //Quantity can have digits and should be positive, greater than 0.
    //Quantity value length <= 5
    if ("-quantity".equals(option)) {
      result = str.matches("[0-9]*")
              && Integer.parseInt(str) > 0 && str.length() <= 5;
    }

    //Type can be either of the three values
    if ("-type".equals(option)) {
      result = "MANUFACTURED".equals(str)
              || "RAW".equals(str)
              || "IMPORTED".equals(str);
    }

    //Name can have only Alphabets and length should be <= 20
    if ("-name".equals(option)) {
      result = str.matches("^[ A-Za-z]+$") && str.length() <= 20;
    }

    return result;
  }

  /**
   * Check if values for option are present and options not already occurred.
   */
  public boolean isValidOption(final String option, final String currentString,
                               final String nextString, final boolean alreadyOccurred) {
    return option.equals(currentString)
            && nextString.charAt(0) != '-' && !alreadyOccurred;
  }

  /**
   * Check if option is can be considered correct or not.
   * It should be valid and of right format to be correct.
   */
  public boolean checkOption(final String option,
                             final String currentString,
                             final String nextString,
                             final boolean alreadyOccurred) throws RuntimeExceptionCustom {
    boolean result = false;
    if (isValidOption(option, currentString, nextString, alreadyOccurred)) {
      if (checkFormat(option, nextString)) {
        result = true;
      } else {
        throw customException;
      }
    }
    return result;
  }

  /**
   * Explicit check for name option because it contains multiple words.
   * Return count of words or throws exception if name not present blank.
   */
  public int checkName(final String... args) throws RuntimeExceptionCustom {
    int numWords = 0;
    if ("-name".equals(args[0])) { //First argument mandatory to be name
      numWords += 1; //count this as significant argument
      int ptr = 1; //pointer to check for how many words name is extended to.
      while (ptr < args.length && args[ptr].charAt(0) != '-') {
        //loop until we get option with first character '-'
        if (!checkFormat("-name", args[ptr])) {
          //If Name format wrong, then throw Exception
          throw customException;
        }
        numWords += 1; //Increase count for significant arguments
        ptr++;
      }
    } else {
      throw customException;
    }
    return numWords;
  }

  /**
   * Implementation for Validate method.
   */
  @Override
  public ItemEntity validate(final String[] args) throws RuntimeExceptionCustom {
    final int len = args.length;
    int countValidArgs = 0;
    final ItemEntity item = new ItemEntity();

    countValidArgs += checkName(args); //Either exception will be thrown or integer returned

    final StringBuilder stringBuilder = new StringBuilder();
    for (int i = 1; i < countValidArgs - 1; i++) {  //Building up the full name of item
      stringBuilder.append(args[i]).append(" ");
    }
    stringBuilder.append(args[countValidArgs - 1]); //Putting last word separately to avoid space
    item.setName(stringBuilder.toString());

    boolean typePresent = false;
    boolean quantityPresent = false;
    boolean pricePresent = false;

    for (int i = 0; i < len - 1; i++) { //loop to find other options and values
      if (checkOption("-price", args[i], args[i + 1], pricePresent)) {
        countValidArgs += 2; //Count increase by 2 for option and value
        pricePresent = true;
        item.setPrice(Double.parseDouble(args[i + 1]));
      }

      if (checkOption("-quantity", args[i], args[i + 1], quantityPresent)) {
        countValidArgs += 2;
        quantityPresent = true;
        item.setQuantity(Integer.parseInt(args[i + 1]));
      }

      if (checkOption("-type", args[i], args[i + 1], typePresent)) {
        countValidArgs += 2;
        typePresent = true;
        item.setType(Type.valueOf(args[i + 1]));
      }
    }

    //type option mandatory and all args significant(meaning no extra arg)
    if (!typePresent || countValidArgs != len) {
      throw customException;
    }

    return item;
  }
}

