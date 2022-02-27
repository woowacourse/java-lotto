package lotto.view;

import verus.view.StringFormatValidator;

public class StringFormatValidatorFactory {

    static final String INVALID_LOTTO_NUMBER_FORMAT_MESSAGE = "당첨 번호의 형식이 잘못 되었습니다. 예) 1, 2, 3, 4, 5, 6";
    static final String INVALID_BONUS_NUMBER_FORMAT_MESSAGE = "보너스 번호의 형식이 잘못 되었습니다. 예) 35";
    static final String INVALID_MONEY_FORMAT_MESSAGE = "금액은 1000의 배수여야 합니다. 예) 10000";

    public static StringFormatValidator lottoValidator() {
        return new StringFormatValidator(
            "^" + numberWithSpacesRegex() + "(\\s*," + numberWithSpacesRegex() + "){5}$",
            INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
    }

    private static String numberWithSpacesRegex() {
        return "\\s*" + numberRegex() + "\\s*";
    }

    private static String numberRegex() {
        return "[1-9][0-9]*";
    }

    public static StringFormatValidator numberValidator() {
        return new StringFormatValidator(numberWithSpacesRegex(),
            INVALID_BONUS_NUMBER_FORMAT_MESSAGE);
    }

    public static StringFormatValidator moneyValidator() {
        return new StringFormatValidator("^\\s*[1-9][0-9]*0{3}\\s*$", INVALID_MONEY_FORMAT_MESSAGE);
    }

}
