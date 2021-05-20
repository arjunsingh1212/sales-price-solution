package com.assignment1.parser;

public class Validity implements Valid {

  //check if price is in decimal format. Should have digits and only one . (decimal symbol). Length<=10
  boolean checkFormatPrice(String str) {
    return str.indexOf('.') == str.lastIndexOf('.') && str.matches("[0-9.]*") && str.length() <= 10;
  }

  //check if quantity is in integer format. Should have digits and should be Natural number). Length<=5
  boolean checkFormatQuantity(String str) {
    return str.matches("[0-9]*") && Integer.parseInt(str) > 0 && str.length() <= 5;
  }

  //check type to be one of the three strings
  boolean checkFormatType(String str) {
    return str.equals("manufactured") || str.equals("raw") || str.equals("imported");
  }

  //check name to be having only alphabets and not empty. Length<=20
  boolean checkFormatName(String str) {
    return str.matches("^[ A-Za-z]+$") && str.length() <= 20;
  }

  @Override
  public boolean validate(String[] args) {
    int len = args.length;
    boolean[] markArray = new boolean[len]; //Boolean array to check how many of the args entities are significant
    boolean namePresent = false;
    boolean typePresent = false;
    boolean quantityPresent = false;
    boolean pricePresent = false;

    if (!args[0].equals("-name")) { //First argument mandatory to be name
      throw new RuntimeException("Invalid Arguments Exception");
    } else {
      markArray[0] = true; //mark that this argument is significant
      int ptr = 1; //pointer to check for how many words name is extended to.
      while (args[ptr].charAt(0) != '-') { //loop until we get option with first character '-'
        if (!checkFormatName(args[ptr])) { //If Name format wrong, then throw Exception
          throw new RuntimeException("Invalid Arguments Exception");
        }
        markArray[ptr] = true; //mark for significant argument
        namePresent = true;
        ptr++;
      }
    }

    for (int i = 0; i < len - 1; i++) { //loop to find other options and values
      if (args[i].equals("-price") && args[i + 1].charAt(0) != '-' && !pricePresent) {  //pricePresent to check duplicate
        if (!checkFormatPrice(args[i + 1])) { //If price format wrong then throw Exception
          throw new RuntimeException("Invalid Arguments Exception");
        } else {
          markArray[i] = true;
          markArray[i + 1] = true;
          pricePresent = true;
        }
      }

      if (args[i].equals("-quantity") && args[i + 1].charAt(0) != '-' && !quantityPresent) { //Similar reasons as that of price
        if (!checkFormatQuantity(args[i + 1])) {
          throw new RuntimeException("Invalid Arguments Exception");
        } else {
          markArray[i] = true;
          markArray[i + 1] = true;
          quantityPresent = true;
        }
      }

      if (args[i].equals("-type") && args[i + 1].charAt(0) != '-' && !typePresent) {
        if (!checkFormatType(args[i + 1])) {
          throw new RuntimeException("Invalid Arguments Exception");
        } else {
          markArray[i] = true;
          markArray[i + 1] = true;
          typePresent = true;
        }
      }
    }

    if (!namePresent || !typePresent) {  //these two options were mandatory
      throw new RuntimeException("Invalid Arguments Exception");

    }
    for (int itr = 0; itr < len; itr++) {
      if (!markArray[itr]) { //Check Extra insignificant entities in args
        throw new RuntimeException("Invalid Arguments Exception");
      }
    }
    return true;
  }
}
