package util.parser;

import java.util.ArrayList;
import java.util.List;
import util.validator.Validator;

public class InputParser {

  public static int parseStringToInteger(String input) {
    Validator.inputValidatorParseInt(input);
    return Integer.parseInt(input);
  }

  public static List<Integer> parseStringToList(String input) {
    List<Integer> winningNumbers = new ArrayList<>();
    String[] tokens = input.split(",");
    for (String token : tokens) {
      Validator.inputValidatorParseInt(token);
      winningNumbers.add(Integer.parseInt(token.trim()));
    }
    return winningNumbers;
  }
}
