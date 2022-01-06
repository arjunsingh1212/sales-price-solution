package com.assignment1.parser;

import com.assignment1.exceptions.GenericApplicationException;
import com.assignment1.item.ItemDTO;

/**
 * Interface for validation methods.
 */
public interface Valid {

  /**
   * method to validate the string.
   */
  boolean validate(ItemDTO item) throws GenericApplicationException;
}
