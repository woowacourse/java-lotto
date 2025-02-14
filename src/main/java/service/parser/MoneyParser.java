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

        validateMoneyOutOfRange(money);
        validateMoneyIsDivideLottoPrice(money);
        return money / LottoConstants.LOTTO_PRICE;
    }

    private static void validateMoneyIsDivideLottoPrice(int money) {
        if (money % LottoConstants.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_MONEY_INPUT.getMessage());
        }
    }

    private static void validateMoneyOutOfRange(int money) {
        if (money < LottoConstants.LOTTO_PRICE || money > LottoConstants.LOTTO_MAX_PRICE) {
            throw new IllegalArgumentException(ErrorMessages.MONEY_OUT_OF_RANGE.getMessage());
        }
    }
}
