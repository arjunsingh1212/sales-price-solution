package com.assignment1.parser;

import com.assignment1.item.Item;

public class CommandLineParser implements Parsable {
  private final Valid validObj;

  public CommandLineParser() {
    validObj = new Validity();
  }

  public CommandLineParser(Valid validObj) {
    this.validObj = validObj;
  }

  @Override
  public Item parse(String input) throws RuntimeException {
    Item item = new Item();
    String[] args = input.split("\\s");
    boolean isValid = validObj.validate(args);
    if (isValid) {
      String nameValue = "";
      double priceValue = 0;
      int quantityValue = 1;
      String typeValue = "";
      int nameEndPointer = 0;
      for (int i = 1; i < args.length; i++) {
        if (args[i].charAt(0) != '-') {
          if (args[i + 1].charAt(0) != '-')
            nameValue += args[i] + " ";
          else
            nameValue += args[i];
        } else {
          nameEndPointer = i;
          break;
        }
      }

      for (int i = nameEndPointer; i < args.length; i++) {
        if (args[i].equals("-price")) {
          priceValue = Double.parseDouble(args[i + 1]);
        }
        if (args[i].equals("-quantity")) {
          quantityValue = Integer.parseInt(args[i + 1]);
        }
        if (args[i].equals("-type")) {
          typeValue = args[i + 1];
        }
      }

      item.setName(nameValue);
      item.setPrice(priceValue);
      item.setQuantity(quantityValue);
      item.setType(typeValue);

      return item;
    } else {
      return null;
    }
  }
}
