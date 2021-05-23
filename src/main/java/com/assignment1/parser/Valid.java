package com.assignment1.parser;

import com.assignment1.exceptions.RuntimeExceptionCustom;
import com.assignment1.item.ItemEntity;

/** Interface for validation methods. */
public interface Valid {

  /** method to validate the string. */
  ItemEntity validate(String... args) throws RuntimeExceptionCustom;
}
