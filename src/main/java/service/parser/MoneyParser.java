package service.parser;

import validator.Validator;

public class MoneyParser {
    public static final String NUMBER_ONLY_REGEX = "^[0-9]+$"; // 숫자만

    public static int parseMoney(String input) {
        Validator.validateEmptyInput(input);
        Validator.checkInvalidForm(input, NUMBER_ONLY_REGEX, "ERROR 입력된 문자가 숫자가 아닙니다.");
        int value = Integer.parseInt(input);

        if (value % 1000 != 0) {
            throw new IllegalArgumentException("ERROR 천원 단위로 입력하세요.");
        }
        return value;
    }
}