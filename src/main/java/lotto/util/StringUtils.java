package lotto.util;

import lotto.validator.InputValidator;

public class StringUtils {
    private static final String LOTTO_DELIMITER = "\n";
    private static final String NUMBER_DELIMITER = ",";

    public static String[] splitNumber(String inputWinNumber) {
        return inputWinNumber.split(NUMBER_DELIMITER);
    }

    public static String[] splitLotto(String inputWinNumber) {
        return inputWinNumber.split(LOTTO_DELIMITER);
    }

    public static int ToInt(String inputUserLottoCount) {
        InputValidator.validateNumber(inputUserLottoCount);
        return Integer.parseInt(inputUserLottoCount);
    }
}
