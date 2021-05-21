package com.assignment1.parser;

import com.assignment1.exceptions.RuntimeExceptionCustom;

public interface Valid {
  boolean validate(String... args) throws RuntimeExceptionCustom;
}
