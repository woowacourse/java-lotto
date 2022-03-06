package lotto.utils;

import lotto.model.message.InputConverterExceptionMessage;

public class ConverterUtils {

    private ConverterUtils() {
    }

    public static int convertStringToInt(String number) {
        InputValidateUtils.inputBlank(number, InputConverterExceptionMessage.BLANK_ERROR.getMessage());
        InputValidateUtils.inputNumber(number, InputConverterExceptionMessage.NUMBER_ERROR.getMessage());
        return Integer.parseInt(number);
    }
}
