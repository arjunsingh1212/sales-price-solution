package com.assignment1.exceptions;

/** Class for custom exception */
public class RuntimeExceptionCustom extends Exception{
  public static final long serialVersionUID = 43287433;

  /** Construction taking custom message for custom exception */
  public RuntimeExceptionCustom(final String message) {
    super(message);
  }
}
