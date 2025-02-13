package service.parser;

import validator.ErrorMessages;
import validator.Validator;

public class MoneyParser {
    public static final String NUMBER_ONLY_REGEX = "^[0-9]+$"; // 숫자만

    public static int parseMoney(String input) {
        Validator.validateEmptyInput(input);
        Validator.checkInvalidForm(input, NUMBER_ONLY_REGEX, ErrorMessages.NOT_NUMBER.getMessage());
        int value = Integer.parseInt(input);

        if (value % 1000 != 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_MONEY_INPUT.getMessage());
        }
        return value;
    }
}