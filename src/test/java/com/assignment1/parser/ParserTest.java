package com.assignment1.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.assignment1.exceptions.RuntimeExceptionCustom;
import com.assignment1.item.ItemEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class ParserTest {

  private Parsable parserObj;

  @BeforeEach
  void createObj() {
    parserObj = new CommandLineParser();
  }

  @Nested
  class AcceptedTests {
    @Test
    @DisplayName("Simple input test")
    void parseTest1() throws RuntimeExceptionCustom {
      ItemEntity expectedItem = new ItemEntity("Book", 50, 3, "imported");
      ItemEntity actual = parserObj.parse("-name Book -price 50 -quantity 3 -type imported");
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }

    @Test
    @DisplayName("Input with 2 words in name")
    void parseTest2() throws RuntimeExceptionCustom {
      ItemEntity expectedItem = new ItemEntity("Note Book", 50, 3, "imported");
      ItemEntity actual = parserObj.parse("-name Note Book -price 50 -quantity 3 -type imported");
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }

    @Test
    @DisplayName("Input with multiple words in name")
    void parseTest3() throws RuntimeExceptionCustom {
      ItemEntity expectedItem = new ItemEntity("Rough Note Book", 50, 3, "imported");
      ItemEntity actual = parserObj.parse("-name Rough Note Book "
              + "-price 50 -quantity 3 -type imported");
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }

    @Test
    @DisplayName("Input with no explicit price option")
    void parseTest4() throws RuntimeExceptionCustom {
      ItemEntity expectedItem = new ItemEntity("Book", 0, 3, "imported");
      ItemEntity actual = parserObj.parse("-name Book -quantity 3 -type imported");
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }

    @Test
    @DisplayName("Input with no explicit quantity option")
    void parseTest5() throws RuntimeExceptionCustom {
      ItemEntity expectedItem = new ItemEntity("Book", 50, 1, "imported");
      ItemEntity actual = parserObj.parse("-name Book -price 50 -type imported");
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }

    @Test
    @DisplayName("Test with Validator object")
    void parseTest6() throws RuntimeExceptionCustom {
      Valid validObj = new Validity();
      Parsable parserObj = new CommandLineParser(validObj);
      ItemEntity expectedItem = new ItemEntity("Book", 50, 1, "imported");
      ItemEntity actual = parserObj.parse("-name Book -price 50 -type imported");
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }
  }

  @Nested
  class ExceptionTests {
    @Test
    @DisplayName("name option missing")
    void parseTest1() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-price 50 -quantity 3 -type imported"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"MissingName";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("type option missing")
    void parseTest2() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name Book -price 50 -quantity 3"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"MissingType";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("incorrect positioning of name option")
    void parseTest3() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-price 50 -name Book -quantity 3 -type imported"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"IncorrectPositioningNameException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("quantity zero")
    void parseTest4() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name Book -price 50 -quantity 0 -type imported"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"ZeroQuantityException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("Negative price")
    void parseTest5() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name Book -price -50 -quantity 3 -type imported"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"NegativePriceException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("Blank value given")
    void parseTest6() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name Book -price -quantity 3 -type imported"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"BlankValueException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("Quantity datatype")
    void parseTest7() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name Book -price 50 -quantity 3.5 -type imported"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"QuantityFormatException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("Price datatype")
    void parseTest8() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name Book -price Hundred -quantity 3 -type imported"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"PriceFormatException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("type values wrong")
    void parseTest9() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name Book -price 50 -quantity 3 -type import"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"TypeFormatException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("name value not correct format")
    void parseTest10() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name 1234 -price 50 -quantity 3 -type import"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"NameFormatException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("options repeated")
    void parseTest11() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name Book -price 50 -price 50 -quantity 3 -type imported"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"RepeatedOptionException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("unrecognized Options found")
    void parseTest12() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name Book -pricing 50 -quantity 3 -type imported"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"UnrecognizedOptionException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("something is wrong with input format")
    void parseTest13() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name Book -price 50 -quantity 3 -type imported Hi Hello"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"IncorrectInputFormatException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("unnecessary or extra options or values in input")
    void parseTest14() {
      Exception except = assertThrows(RuntimeExceptionCustom.class, () -> parserObj.parse(
              "-name Book -price 50 -quantity 3 -type imported -priority high"));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Arguments Exception"; //"UnnecessaryOptionValueException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }
  }
}

