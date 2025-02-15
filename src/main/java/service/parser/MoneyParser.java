package service.parser;

import constant.LottoConstants;
import validator.Validator;

public class MoneyParser {

    public static int parseLottoCount(String input) {
        Validator.validateEmptyInput(input);
        Validator.checkInvalidNumberForm(input);
        int money = Integer.parseInt(input);

        validateMoneyOutOfRange(money);
        validateMoneyIsDivideLottoPrice(money);
        return money / LottoConstants.LOTTO_PRICE;
    }

    private static void validateMoneyIsDivideLottoPrice(int money) {
        if (money % LottoConstants.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("천원 단위로 입력하세요.");
        }
    }

    private static void validateMoneyOutOfRange(int money) {
        if (money < LottoConstants.LOTTO_PRICE || money > LottoConstants.LOTTO_MAX_PRICE) {
            throw new IllegalArgumentException(String.format("돈은 %,d원과 %,d원 사이여야 합니다.", LottoConstants.LOTTO_PRICE, LottoConstants.LOTTO_MAX_PRICE));
        }
    }
}
