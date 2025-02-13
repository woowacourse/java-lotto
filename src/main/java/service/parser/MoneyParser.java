package service.parser;

import constant.LottoConstants;
import constant.RegexConstants;
import validator.ErrorMessages;
import validator.Validator;

public class MoneyParser {

    public static int parseLottoCount(String input) {
        Validator.validateEmptyInput(input);
        Validator.checkInvalidForm(input, RegexConstants.NUMBER_ONLY_REGEX, ErrorMessages.NOT_NUMBER.getMessage());
        int money = Integer.parseInt(input);

        validateMoneyIsDivideLottoPrice(money);
        return money / LottoConstants.LOTTO_PRICE;
    }

    private static void validateMoneyIsDivideLottoPrice(int money) {
        if (money % LottoConstants.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_MONEY_INPUT.getMessage());
        }
    }
}