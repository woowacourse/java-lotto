package lotto.model.result;

import lotto.model.message.LottoCountExceptionMessage;
import lotto.utils.ConverterUtils;
import lotto.utils.InputValidateUtils;

public class Money {
    private static final int NOTHING = 0;
    private static final int NON_REMAINDER = 0;
    private static final int UNIT = 1000;

    private final String number;

    public Money(String number) {
        InputValidateUtils.inputBlank(number, LottoCountExceptionMessage.BLANK_ERROR.getMassage());
        InputValidateUtils.inputNumber(number, LottoCountExceptionMessage.NUMBER_ERROR.getMassage());
        validateInputZero(number);
        validateThousandUnitInputMoney(number);
        this.number = number;
    }

    private void validateInputZero(String money) {
        if (ConverterUtils.convertStringToInt(money) == NOTHING) {
            throw new IllegalArgumentException(LottoCountExceptionMessage.LOWER_THAN_THOUSAND.getMassage());
        }
    }

    private void validateThousandUnitInputMoney(String money) {
        if (ConverterUtils.convertStringToInt(money) % UNIT != NON_REMAINDER) {
            throw new IllegalArgumentException(LottoCountExceptionMessage.UNIT_ERROR.getMassage());
        }
    }

    public String getNumber() {
        return number;
    }
}
