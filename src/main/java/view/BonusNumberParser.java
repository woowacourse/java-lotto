package view;

public class BonusNumberParser extends Parser<Integer> {

    private static final String REGEX_PREFIX = "^\\s*(";
    private static final String REGEX_SUFFIX = ")\\s*$";
    private static final String REGEX_OR = "|";
    private static final String REGEX_ONE_TO_NINE = "[1-9]";
    private static final String REGEX_TEN_TO_THIRTY_NINE = "[1-3][0-9]";
    private static final String REGEX_FORTY_TO_FORTY_FIVE = "4[0-5]";
    static final String INVALID_BONUS_NUMBER_FORMAT_MESSAGE = "보너스 번호는 반드시 두자리 양수여야 합니다.";

    public BonusNumberParser() {
        super(regex(), INVALID_BONUS_NUMBER_FORMAT_MESSAGE);
    }

    private static String regex() {
        return new StringBuilder(REGEX_PREFIX)
            .append(REGEX_ONE_TO_NINE).append(REGEX_OR)
            .append(REGEX_TEN_TO_THIRTY_NINE).append(REGEX_OR)
            .append(REGEX_FORTY_TO_FORTY_FIVE).append(REGEX_SUFFIX)
            .toString();
    }

    @Override
    protected Integer convert(String text) {
        return Integer.parseInt(text.trim());
    }
}
