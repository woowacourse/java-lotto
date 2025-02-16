package model;

import static constant.message.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static constant.message.ExceptionMessage.INVALID_LOTTO_MIN_PURCHASE;
import static constant.message.ExceptionMessage.INVALID_LOTTO_PURCHASE_FORMAT;
import static constant.message.ExceptionMessage.INVALID_LOTTO_PURCHASE_UNIT;
import static constant.LottoConstant.LOTTO_PURCHASE_MIN_AMOUNT;

import util.Parser;
import util.Validator;

public class LottoPurchase {

    private final int amount;

    public static LottoPurchase of(final String input) {
        Validator.validateNullOrBlank(input, INVALID_INPUT_NULL_OR_BLANK.getMessage());
        Validator.validateInteger(input, INVALID_LOTTO_PURCHASE_FORMAT.getMessage());
        int amount = Parser.convertStringToInteger(input);

        return new LottoPurchase(amount);
    }

    private LottoPurchase(int amount) {
        Validator.validateMinimum(amount, LOTTO_PURCHASE_MIN_AMOUNT,
                INVALID_LOTTO_MIN_PURCHASE.getMessage(LOTTO_PURCHASE_MIN_AMOUNT));
        Validator.validateUnit(amount, LOTTO_PURCHASE_MIN_AMOUNT,
                INVALID_LOTTO_PURCHASE_UNIT.getMessage(LOTTO_PURCHASE_MIN_AMOUNT));

        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PURCHASE_MIN_AMOUNT;
    }
}
