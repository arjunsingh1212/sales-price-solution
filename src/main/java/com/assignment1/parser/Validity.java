package com.assignment1.parser;

public class Validity implements Valid {

  boolean checkFormatPrice(String str) {
    return str.indexOf('.') == str.lastIndexOf('.') && str.matches("[0-9.]*");
  }

  boolean checkFormatQuantity(String str) {
    return str.matches("[0-9]*") && Integer.parseInt(str) > 0;
  }

  boolean checkFormatType(String str) {
    return str.equals("manufactured") || str.equals("raw") || str.equals("imported");
  }

  boolean checkFormatName(String str) {
    return str.matches("^[ A-Za-z]+$");
  }

  @Override
  public boolean validate(String[] args) {
    int len = args.length;
    boolean[] markArray = new boolean[len];
    boolean namePresent = false;
    boolean typePresent = false;

    if (!args[0].equals("-name")) {
      throw new RuntimeException("Invalid Arguments Exception");
    } else {
      markArray[0] = true;
      int ptr = 1;
      while (args[ptr].charAt(0) != '-') {
        if (!checkFormatName(args[ptr]))
          throw new RuntimeException("Invalid Arguments Exception");

        markArray[ptr] = true;
        namePresent = true;
        ptr++;
      }

    }

    for (int i = 0; i < len - 1; i++) {
      if (args[i].equals("-price") && args[i + 1].charAt(0) != '-') {
        if (!checkFormatPrice(args[i + 1])) {
          throw new RuntimeException("Invalid Arguments Exception");
        } else {
          markArray[i] = true;
          markArray[i + 1] = true;
        }
      }

      if (args[i].equals("-quantity") && args[i + 1].charAt(0) != '-') {
        if (!checkFormatQuantity(args[i + 1])) {
          throw new RuntimeException("Invalid Arguments Exception");
        } else {
          markArray[i] = true;
          markArray[i + 1] = true;
        }
      }

      if (args[i].equals("-type") && args[i + 1].charAt(0) != '-') {
        if (!checkFormatType(args[i + 1])) {
          throw new RuntimeException("Invalid Arguments Exception");
        } else {
          typePresent = true;
          markArray[i] = true;
          markArray[i + 1] = true;
        }
      }


    }
    if (!namePresent || !typePresent) {
      throw new RuntimeException("Invalid Arguments Exception");

    }
    for (int itr = 0; itr < len; itr++) {
      if (!markArray[itr])
        throw new RuntimeException("Invalid Arguments Exception");
    }
    return true;
  }
}
