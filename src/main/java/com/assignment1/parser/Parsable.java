package com.assignment1.parser;

import com.assignment1.exceptions.GenericApplicationException;
import com.assignment1.item.ItemDTO;
import com.assignment1.item.ItemEntity;

/**
 * Interface for Parser methods.
 */
public interface Parsable {

  /**
   * Method to parse the string.
   */
  ItemEntity parse(ItemDTO itemDTO) throws GenericApplicationException;
}
