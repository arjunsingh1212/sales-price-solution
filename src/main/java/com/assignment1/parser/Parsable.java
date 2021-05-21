package com.assignment1.parser;

import com.assignment1.exceptions.RuntimeExceptionCustom;
import com.assignment1.item.ItemEntity;

public interface Parsable {
  ItemEntity parse(String input) throws RuntimeExceptionCustom;
}
