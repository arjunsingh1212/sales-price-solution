package com.assignment1.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.assignment1.Type;
import com.assignment1.exceptions.GenericApplicationException;
import com.assignment1.item.ItemEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class ParserTest {

  private Parsable parserObj;

  private String[] toArray(String str) {
    return str.split("\\s+");
  }

  @BeforeEach
  void createObj() {
    parserObj = new CommandLineParser();
  }

  @Nested
  class AcceptedTests {
    @Test
    @DisplayName("Simple input test")
    void parseTest1() throws GenericApplicationException {
      ItemEntity expectedItem = new ItemEntity("Book", BigDecimal.valueOf(50), 3, Type.IMPORTED);
      ItemEntity actual = parserObj.parse(toArray("-name Book -price 50 -quantity 3 -type IMPORTED"));
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }

    @Test
    @DisplayName("Input with 2 words in name")
    void parseTest2() throws GenericApplicationException {
      ItemEntity expectedItem = new ItemEntity("Note Book", BigDecimal.valueOf(50), 3, Type.IMPORTED);
      ItemEntity actual = parserObj.parse(toArray("-name Note Book -price 50 -quantity 3 -type IMPORTED"));
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }

    @Test
    @DisplayName("Input with multiple words in name")
    void parseTest3() throws GenericApplicationException {
      ItemEntity expectedItem = new ItemEntity("Rough Note Book", BigDecimal.valueOf(50), 3, Type.IMPORTED);
      ItemEntity actual = parserObj.parse(toArray("-name Rough Note Book "
              + "-price 50 -quantity 3 -type IMPORTED"));
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }

    @Test
    @DisplayName("Input with no explicit price option")
    void parseTest4() throws GenericApplicationException {
      ItemEntity expectedItem = new ItemEntity("Book", BigDecimal.valueOf(0.0), 3, Type.IMPORTED);
      ItemEntity actual = parserObj.parse(toArray("-name Book -quantity 3 -type IMPORTED"));
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }

    @Test
    @DisplayName("Input with no explicit quantity option")
    void parseTest5() throws GenericApplicationException {
      ItemEntity expectedItem = new ItemEntity("Book", BigDecimal.valueOf(50), 1, Type.IMPORTED);
      ItemEntity actual = parserObj.parse(toArray("-name Book -price 50 -type IMPORTED"));
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }

    @Test
    @DisplayName("Test with Validator object")
    void parseTest6() throws GenericApplicationException {
      Valid validObj = new Validator();
      Parsable parserObj = new CommandLineParser(validObj);
      ItemEntity expectedItem = new ItemEntity("Book", BigDecimal.valueOf(50), 1, Type.IMPORTED);
      ItemEntity actual = parserObj.parse(toArray("-name Book -price 50 -type IMPORTED"));
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }

    @Test
    @DisplayName("Input with price including commas")
    void parseTest7() throws GenericApplicationException {
      ItemEntity expectedItem = new ItemEntity("Book", BigDecimal.valueOf(5012300), 1, Type.IMPORTED);
      ItemEntity actual = parserObj.parse(toArray("-name Book -price 50,12,300 -type IMPORTED"));
      assertEquals(expectedItem.toString(), actual.toString(), "Should parse correctly");
    }
  }

  @Nested
  class ExceptionTests {
    @Test
    @DisplayName("name option missing")
    void parseTest1() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-price 50 -quantity 3 -type IMPORTED")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Input: -name option not present at first place"; //"MissingName";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("type option missing")
    void parseTest2() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name Book -price 50 -quantity 3")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Type Format"; //"MissingType";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("incorrect positioning of name option")
    void parseTest3() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-price 50 -name Book -quantity 3 -type IMPORTED")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Input: -name option not present at first place"; //"IncorrectPositioningNameException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("quantity zero")
    void parseTest4() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name Book -price 50 -quantity 0 -type IMPORTED")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Quantity Format"; //"ZeroQuantityException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("Negative price")
    void parseTest5() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name Book -price -50 -quantity 3 -type IMPORTED")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Price Format"; //"NegativePriceException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("Blank value given")
    void parseTest6() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name Book -price -quantity 3 -type IMPORTED")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Parsing Error (Illegal arguments given)"; //"BlankValueException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("Quantity datatype")
    void parseTest7() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name Book -price 50 -quantity 3.5 -type IMPORTED")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Quantity Format"; //"QuantityFormatException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("Price datatype")
    void parseTest8() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name Book -price Hundred -quantity 3 -type IMPORTED")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Price Format"; //"PriceFormatException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("type values wrong")
    void parseTest9() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name Book -price 50 -quantity 3 -type import")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Type Format"; //"TypeFormatException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("name value not correct format")
    void parseTest10() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name 1234 -price 50 -quantity 3 -type import")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Invalid Name Format"; //"NameFormatException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("options repeated")
    void parseTest11() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name Book -price 50 -price 50 -quantity 3 -type IMPORTED")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Parsing Error (Illegal arguments given)"; //"RepeatedOptionException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("unrecognized Options found")
    void parseTest12() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name Book -pricing 50 -quantity 3 -type IMPORTED")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Parsing Error (Illegal arguments given)"; //"UnrecognizedOptionException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("something is wrong with input format")
    void parseTest13() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name Book -price 50 -quantity 3 -type IMPORTED Hi Hello")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Parsing Error (Illegal arguments given)"; //"IncorrectInputFormatException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }

    @Test
    @DisplayName("unnecessary or extra options or values in input")
    void parseTest14() {
      Exception except = assertThrows(GenericApplicationException.class, () -> parserObj.parse(toArray(
              "-name Book -price 50 -quantity 3 -type IMPORTED -priority high")));
      String actualMessage = except.getMessage();
      String expectedMessage = "Parsing Error (Illegal arguments given)"; //"UnnecessaryOptionValueException";
      assertEquals(expectedMessage, actualMessage, "Exception should be of " + actualMessage);
    }
  }
}

