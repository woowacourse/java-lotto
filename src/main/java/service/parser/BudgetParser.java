package service.parser;

import static common.constant.NumberConstants.LOTTO_MAX_PRICE;
import static common.constant.NumberConstants.LOTTO_PRICE;
import static common.utils.ValidationUtils.checkOutOfRange;

import validator.ErrorMessages;
import validator.Validator;

public class BudgetParser {

    public static int parseBudget(String input) {
        Validator.validateEmptyInput(input);
        Validator.validateNumber(input);
        int money = Integer.parseInt(input);

        validateMoneyOutOfRange(money);
        validateMoneyIsDivideLottoPrice(money);
        return money;
    }

    private static void validateMoneyOutOfRange(int money) {
        checkOutOfRange(money, LOTTO_PRICE, LOTTO_MAX_PRICE, ErrorMessages.MONEY_OUT_OF_RANGE.getMessage());
    }

    private static void validateMoneyIsDivideLottoPrice(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_MONEY_INPUT.getMessage());
        }
    }
}