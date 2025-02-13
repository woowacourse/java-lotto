package service.parser;

import static constant.NumberConstants.LOTTO_MAX_PRICE;
import static constant.NumberConstants.LOTTO_PRICE;
import static constant.RegexConstants.NUMBER_ONLY_REGEX;

import validator.ErrorMessages;
import validator.Validator;

public class BudgetParser {

    public static int parseLottoCount(String input) {
        Validator.validateEmptyInput(input);
        Validator.validateInvalidForm(input, NUMBER_ONLY_REGEX, ErrorMessages.NOT_NUMBER.getMessage());
        int money = Integer.parseInt(input);

        validateMoneyOutOfRange(money);
        validateMoneyIsDivideLottoPrice(money);
        return money / LOTTO_PRICE;
    }

    private static void validateMoneyIsDivideLottoPrice(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_MONEY_INPUT.getMessage());
        }
    }

    private static void validateMoneyOutOfRange(int money) {
        if (money < LOTTO_PRICE || money > LOTTO_MAX_PRICE) {
            throw new IllegalArgumentException(ErrorMessages.MONEY_OUT_OF_RANGE.getMessage());
        }
    }
}