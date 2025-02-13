package service.parser;

import static common.constant.NumberConstants.LOTTO_MAX_PRICE;
import static common.constant.NumberConstants.LOTTO_PRICE;

import validator.ErrorMessages;
import validator.Validator;

public class BudgetParser {

    public static int parseLottoCount(String input) {
        Validator.validateEmptyInput(input);
        Validator.validateNumber(input);
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