package com.assignment1.parser;

import com.assignment1.exceptions.RuntimeExceptionCustom;
import com.assignment1.item.ItemEntity;

public class CommandLineParser implements Parsable {
  private transient final Valid validObj;

  public CommandLineParser() {
    validObj = new Validity();
  }

  public CommandLineParser(final Valid validObj) {
    this.validObj = validObj;
  }

  @Override
  public ItemEntity parse(final String input) throws RuntimeExceptionCustom {
    final ItemEntity item = new ItemEntity();
    final String[] args = input.split("\\s+");  //Regex: One or more than one space
    final boolean isValid = validObj.validate(args);
    if (isValid) {
      String nameValue; //We'll extract Name from the args in below section
      double priceValue = 0; //default price 0
      int quantityValue = 1; //default quantity 1
      String typeValue = ""; //We'll extract Type from the args in below section

      int nameEndPointer = 0; //To remember where name value ends

      final char optionSymbol = '-';

      final StringBuilder nameValueSB = new StringBuilder();

      //Segregating the name words
      for (int i = 1; i < args.length; i++) {
        if (args[i].charAt(0) == optionSymbol) {
          nameEndPointer = i;
          break;
        } else {
          if (args[i + 1].charAt(0) == optionSymbol) {
            nameValueSB.append(args[i]);
          } else {
            nameValueSB.append(args[i]).append(" ");
          }
        }
      }
      nameValue = nameValueSB.toString();

      //Extracting the price, quantity and type
      for (int i = nameEndPointer; i < args.length; i++) {
        if ("-price".equals(args[i])) {
          priceValue = Double.parseDouble(args[i + 1]);
        }
        if ("-quantity".equals(args[i])) {
          quantityValue = Integer.parseInt(args[i + 1]);
        }
        if ("-type".equals(args[i])) {
          typeValue = args[i + 1];
        }
      }

      item.setName(nameValue);
      item.setPrice(priceValue);
      item.setQuantity(quantityValue);
      item.setType(typeValue);

    }
    return item;
  }
}
