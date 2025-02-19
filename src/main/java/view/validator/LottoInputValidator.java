package view.validator;

import exception.LottoException;
import utility.StringUtility;

public class LottoInputValidator {

  private static final String INVALID_MONEY = "유효하지 않은 금액입니다.";
  private static final String INVALID_WINNING_NUMBER = "유효하지 않은 번호입니다.";

  public static void validateMoney(String moneyInput) {
    validateNumeric(moneyInput);
  }

  public static void validateNumber(String lottoNumber) {
    validateIsEmpty(lottoNumber);
    validateNumeric(lottoNumber);
  }

  private static void validateNumeric(String input) {
    if (input == null || !StringUtility.isNumber(input)) {
      throw new LottoException(INVALID_MONEY);
    }
  }

  private static void validateIsEmpty(String inputWinningNumber) {
    if (inputWinningNumber == null) {
      throw new LottoException(INVALID_WINNING_NUMBER);
    }
  }

}
