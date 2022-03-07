package lotto.view;

public class StringFormatValidator {

    private static final String NUMBER = "(0|[1-9][0-9]*)";
    private static final String NUMBER_WITH_SPACE = "\\s*" + NUMBER + "\\s*";

    static final String INVALID_LOTTO_NUMBER_FORMAT_MESSAGE = "당첨 번호의 형식이 잘못 되었습니다. 예) 1, 2, 3, 4, 5, 6";
    static final String INVALID_BONUS_NUMBER_FORMAT_MESSAGE = "숫자의 형식이 잘못 되었습니다. 예) 35";
    static final String INVALID_MONEY_FORMAT_MESSAGE = "금액은 1000의 배수여야 합니다. 예) 10000";

    private final String regex;
    private final String errorMessage;

    public StringFormatValidator(String regex, String errorMessage) {
        this.regex = regex;
        this.errorMessage = errorMessage;
    }

    public void validate(String text) {
        if (!text.matches(regex)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static StringFormatValidator lottoValidator() {
        return new StringFormatValidator(
            "^" + NUMBER_WITH_SPACE + "(\\s*," + NUMBER_WITH_SPACE + "){5}$",
            INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
    }

    public static StringFormatValidator numberValidator() {
        return new StringFormatValidator(NUMBER_WITH_SPACE, INVALID_BONUS_NUMBER_FORMAT_MESSAGE);
    }

    public static StringFormatValidator moneyValidator() {
        return new StringFormatValidator("^\\s*[1-9][0-9]*0{3}\\s*$", INVALID_MONEY_FORMAT_MESSAGE);
    }
}
