package lotto.view;

public class StringFormatValidator {

    private static final String EMPTY_MESSAGE = "";
    public static final StringFormatValidator NOT_WORKING_VALIDATOR = new StringFormatValidator(
        ".*", EMPTY_MESSAGE);

    static final String INVALID_LOTTO_NUMBER_FORMAT_MESSAGE = "당첨 번호의 형식이 잘못 되었습니다. 예) 1, 2, 3, 4, 5, 6";
    static final String INVALID_BONUS_NUMBER_FORMAT_MESSAGE = "보너스 번호의 형식이 잘못 되었습니다. 예) 35";
    static final String INVALID_MONEY_FORMAT_MESSAGE = "금액은 1000의 배수여야 합니다. 예) 10000";

    private final String regex;
    private final String errorMessage;

    public StringFormatValidator(String regex, String errorMessage) {
        this.regex = regex;
        this.errorMessage = errorMessage;
    }

    public void validate(String text) {
        if (!text.matches(regex)) {
            throw new InvalidFormatException(errorMessage);
        }
    }

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
        return new StringFormatValidator("^[1-9][0-9]*0{3}$", INVALID_MONEY_FORMAT_MESSAGE);
    }
}
