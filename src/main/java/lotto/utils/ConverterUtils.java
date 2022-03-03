package lotto.utils;

import lotto.model.message.LottoCountExceptionMessage;

public class ConverterUtils {

    private ConverterUtils() {
    }

    public static int convertStringToInt(String number) {
        InputValidateUtils.inputBlank(number, LottoCountExceptionMessage.BLANK_ERROR.getMassage());
        InputValidateUtils.inputNumber(number, LottoCountExceptionMessage.NUMBER_ERROR.getMassage());
        return Integer.parseInt(number);
    }
}
