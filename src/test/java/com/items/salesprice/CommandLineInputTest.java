package com.items.salesprice;

import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineInputTest {
  private ParserValidator parseValidateObj;

  @BeforeEach
  void createObj() {
    parseValidateObj = new CommandLineInput();
  }

  @Nested
  class Parser {

    @Test
    @DisplayName("Parsing String Command Line input to array of strings (Correct Input)")
    void testParse() {
      String[] expected = {"-name", "book", "-price", "50", "-quantity", "3", "-type", "manufactured"};
      String[] actual = parseValidateObj.parse("-name Book -price 50 -quantity 3 -type manufactured");
      assertEquals(Arrays.toString(expected),Arrays.toString(actual));
    }

    @Test
    @DisplayName("Parsing String Command Line input to array of strings (Random Input)")
    void testStringParsingMultipleWordsName() {
      String[] expected = {"Hi", "Hello", "Hey"};
      String[] actual = parseValidateObj.parse("Hi Hello Hey");
      assertEquals(Arrays.toString(expected),Arrays.toString(actual));
    }

  }

  @Nested
  class CorrectInput {

    @Test
    @DisplayName("Simple Correct Input Test")
    void testValiditySimpleInput() {
      String[] parsedInput = {"-name", "Book", "-price", "50", "-quantity", "3", "-type", "manufactured"};
      assertTrue(parseValidateObj.validate(parsedInput), "Should return true");
    }

    @Test
    @DisplayName("Simple Correct Input Test With Multiple Words in name")
    void testValiditySimpleInputWithMultipleWordsName() {
      String[] parsedInput = {"-name", "Note", "Book", "-price", "50", "-quantity", "3", "-type", "manufactured"};
      assertTrue(parseValidateObj.validate(parsedInput), "Should return true");
    }


    @Test
    @DisplayName("Simple Correct Input Test With Change In Order")
    void testValiditySimpleInputWithChangeInOrder() {
      String[] parsedInput = {"-name", "Note Book", "-quantity", "3", "-price", "50", "-type", "manufactured"};
      assertTrue(parseValidateObj.validate(parsedInput), "Should return true");
    }

    @Test
    @DisplayName("Correct Input when Price Option not given. Price=0")
    void testValidityInputPriceNotGiven() {
      String[] parsedInput = {"-name", "Book", "-quantity", "3", "-type", "manufactured"};
      assertTrue(parseValidateObj.validate(parsedInput), "Should return true");
    }
  }
}