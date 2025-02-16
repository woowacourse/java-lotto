package lotto.domain;

import lotto.constant.ErrorMessage;
import lotto.constant.LottoConstants;

public class LottoNumber {
    private final int number;

    public LottoNumber(final int number) {
        validateRange(number);
        this.number = number;
    }

    public LottoNumber(final String number) {
        int parsedNumber = validateNumber(number);
        validateRange(parsedNumber);
        this.number = parsedNumber;
    }

    public int getNumber() {
        return number;
    }

    private int validateNumber(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_FORMAT_ERROR.getMessage());
        }
    }

    private void validateRange(final int number) {
        if (number < LottoConstants.LOTTO_MINIMUM_NUMBER.getNumber() || number > LottoConstants.LOTTO_MAXIMUM_NUMBER.getNumber()) {
            throw new IllegalArgumentException(ErrorMessage.RANGE_ERROR.getMessage());
        }
    }

}
