package com.assignment1.parser;

import com.assignment1.Type;
import com.assignment1.exceptions.GenericApplicationException;
import com.assignment1.item.ItemDTO;
import com.assignment1.item.ItemEntity;
import java.math.BigDecimal;

/**
 * Class to implement Parser.
 */
public class CommandLineParser implements Parsable {

  /**
   * Object of Valid class to be used in Parse method.
   */
  private final transient Valid validator;

  /**
   * Constructor to hardcode the Valid object.
   */
  public CommandLineParser() {
    validator = new Validator();
  }

  /**
   * Constructor to initialize Valid object using parameter.
   */
  public CommandLineParser(final Valid validator) {
    this.validator = validator;
  }

  /**
   * Explicit check for name option because it contains multiple words.
   * Return count of words or throws exception if name not present (blank).
   */
  public int findNumOfWordsInName(final String... args) throws GenericApplicationException {
    int numWords = 0;
    if ("-name".equals(args[0])) { //First argument mandatory to be name
      int ptr = 1; //pointer to check for how many words name is extended to.
      while (ptr < args.length && args[ptr].charAt(0) != '-') {
        //loop until we get option with first character '-'
        numWords += 1; //Increase count for significant arguments
        ptr++;
      }
    } else {
      throw new GenericApplicationException("Invalid Input: -name option not present at first place");
    }
    return numWords;
  }

  /**
   * function to extract name.
   */
  private String extractName(final int numOfWordsInName, final String... args) {
    final StringBuilder stringBuilder = new StringBuilder();
    for (int i = 1; i <= numOfWordsInName - 1; i++) {  //Building up the full name of item
      stringBuilder.append(args[i]).append(" ");
    }
    stringBuilder.append(args[numOfWordsInName]); //Putting last word separately to avoid space
    return stringBuilder.toString();
  }

  /**
   * Building Item entity when everything is right and valid.
   */
  private ItemEntity buildUpItemEntity(final ItemDTO itemDTO) {
    final ItemEntity item = new ItemEntity();
    item.setName(itemDTO.getName());
    if ("".equals(itemDTO.getPrice())) {
      item.setPrice(BigDecimal.valueOf(0.0));
    } else {
      item.setPrice(
              new BigDecimal(itemDTO.getPrice().replace(",","")));
    }
    if ("".equals(itemDTO.getQuantity())) {
      item.setQuantity(1);
    } else {
      item.setQuantity(Integer.parseInt(itemDTO.getQuantity()));
    }
    item.setType(Type.valueOf(itemDTO.getType()));
    item.calculateTax(); //This populates the tax property of the Item
    return item;
  }

  /**
   * Parse Implementation.
   */
  @Override
  public ItemEntity parse(final String... args) throws GenericApplicationException {
    final ItemDTO itemDTO = new ItemDTO();  //Initially blank POJO
    int countValidArgs = 0;  //countValidArgs will hold count of valid args
    final int len = args.length;

    final int numOfWordsInName = findNumOfWordsInName(args);
    //Either exception will be thrown or integer returned
    countValidArgs += numOfWordsInName + 1; //Adding 1 for option "-name"

    itemDTO.setName(extractName(numOfWordsInName, args));  //populating name of Item DTO

    boolean typePresent = false;
    boolean quantityPresent = false;
    boolean pricePresent = false;

    for (int i = 0; i < len - 1; i++) { //loop to find other options and values
      if ("-price".equals(args[i]) && !pricePresent) {
        countValidArgs += 2; //Count increase by 2 for option and value
        pricePresent = true;
        itemDTO.setPrice(args[i + 1]);
      }

      if ("-quantity".equals(args[i]) && !quantityPresent) {
        countValidArgs += 2;
        quantityPresent = true;
        itemDTO.setQuantity(args[i + 1]);
      }

      if ("-type".equals(args[i]) && !typePresent) {
        countValidArgs += 2;
        typePresent = true;
        itemDTO.setType(args[i + 1]);
      }
    }

    if (countValidArgs != len) {    //This signifies that there are some illegal args
      throw new GenericApplicationException("Parsing Error (Illegal arguments given)");
    }

    ItemEntity item;
    if (validator.validate(itemDTO)) {
      item = buildUpItemEntity(itemDTO);
    } else {
      throw new GenericApplicationException("Parsing or Validation Error!");
    }

    return item;
  }
}
