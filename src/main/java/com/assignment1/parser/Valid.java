package com.assignment1.parser;

import com.assignment1.exceptions.RuntimeExceptionCustom;

/** Interface for validation methods. */
public interface Valid {

  /** method to validate the string. */
  boolean validate(String... args) throws RuntimeExceptionCustom;
}
