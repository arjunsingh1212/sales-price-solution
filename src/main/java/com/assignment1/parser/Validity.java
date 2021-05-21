package com.assignment1.parser;

import com.assignment1.exceptions.RuntimeExceptionCustom;

public class Validity implements Valid {

  transient private final RuntimeExceptionCustom customException = new RuntimeExceptionCustom("Invalid Arguments Exception");

  public boolean checkFormat(final String option, final String str) {

    boolean result = false;

    if ("-price".equals(option)) {
      result = str.indexOf('.') == str.lastIndexOf('.') && str.matches("[0-9.]*") && str.length() <= 10;
    }
    if ("-quantity".equals(option)) {
      result = str.matches("[0-9]*") && Integer.parseInt(str) > 0 && str.length() <= 5;
    }
    if ("-type".equals(option)) {
      result = "manufactured".equals(str) || "raw".equals(str) || "imported".equals(str);
    }
    if ("-name".equals(option)) {
      result = str.matches("^[ A-Za-z]+$") && str.length() <= 20;
    }
    return result;
  }

  public boolean isValidOption(final String option, final String currentString, final String nextString, final boolean alreadyOccurred) {
    return option.equals(currentString) && nextString.charAt(0) != '-' && !alreadyOccurred;
  }

  public boolean checkOption(final String option, final String currentString, final String nextString, final boolean alreadyOccurred) throws RuntimeExceptionCustom {
    boolean result = false;
    if (isValidOption(option, currentString, nextString, alreadyOccurred)) {
      if (checkFormat(option, nextString)) { //If price format wrong then throw Exception
        result = true;
      } else {
        throw customException;
      }
    }
    return result;
  }

  public int checkName(final String... args) throws RuntimeExceptionCustom {
    int numWords = 0;
    if ("-name".equals(args[0])) { //First argument mandatory to be name
      numWords += 1; //mark that this argument is significant
      int ptr = 1; //pointer to check for how many words name is extended to.
      while (ptr < args.length && args[ptr].charAt(0) != '-') { //loop until we get option with first character '-'
        if (!checkFormat("-name", args[ptr])) { //If Name format wrong, then throw Exception
          throw customException;
        }
        numWords += 1;
        ptr++;
      }
    } else {
      throw customException;
    }
    return numWords;
  }

  @Override
  public boolean validate(final String[] args) throws RuntimeExceptionCustom {
    final int len = args.length;
    int countvalidArgs = 0;

    countvalidArgs += checkName(args);
//    final boolean namePresent = true; //Because checkName(args) did not throw exception
 //    if (countvalidArguments != 0) {
//      namePresent = true;
//    }

    boolean typePresent = false;
    boolean quantityPresent = false;
    boolean pricePresent = false;
    for (int i = 0; i < len - 1; i++) { //loop to find other options and values
      //pricePresent to check duplicate
      if (checkOption("-price", args[i], args[i + 1], pricePresent)) {
        countvalidArgs += 2;
        pricePresent = true;
      }

      if (checkOption("-quantity", args[i], args[i + 1], quantityPresent)) {
        countvalidArgs += 2;
        quantityPresent = true;
      }

      if (checkOption("-type", args[i], args[i + 1], typePresent)) {
        countvalidArgs += 2;
        typePresent = true;
      }
    }

    if (!typePresent || countvalidArgs != len) {  //these two options were mandatory
      throw customException;
    }

    return true;
  }
}

