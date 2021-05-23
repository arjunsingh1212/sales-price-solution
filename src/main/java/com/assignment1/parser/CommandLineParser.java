package com.assignment1.parser;

import com.assignment1.exceptions.RuntimeExceptionCustom;
import com.assignment1.item.ItemEntity;

/** Class to implement Parser. */
public class CommandLineParser implements Parsable {

  /** Object of Valid class to be used in Parse method. */
  private final transient Valid validObj;

  /** Constructor to hardcode the Valid object. */
  public CommandLineParser() {
    validObj = new Validator();
  }

  /** Constructor to initialize Valid object using parameter. */
  public CommandLineParser(final Valid validObj) {
    this.validObj = validObj;
  }

  /** Parse Implementation. */
  @Override
  public ItemEntity parse(final String input) throws RuntimeExceptionCustom {
    final String[] args = input.split("\\s+");  //Regex: One or more than one space
    return validObj.validate(args);
  }
}
