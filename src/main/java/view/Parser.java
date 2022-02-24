package view;

public abstract class Parser<T> {
    protected static final String REGEX_BEGINNING = "^";
    protected static final String REGEX_END = "$";
    protected static final String REGEX_GROUP_BEGINNING = "(";
    protected static final String REGEX_GROUP_END = ")";
    protected static final String REGEX_COMMA = ",";
    protected static final String REGEX_SPACE = "\\s*";
    protected static final String REGEX_OR = "|";
    protected static final String REGEX_ONE_TO_NINE = "[1-9]";
    protected static final String REGEX_TEN_TO_THIRTY_NINE = "[1-3][0-9]";
    protected static final String REGEX_FORTY_TO_FORTY_FIVE = "4[0-5]";
    protected static final String REGEX_ZERO_TO_NINE = "[0-9]";
    protected static final String REGEX_ASTERISK = "*";

    private final String regex;
    private final String errorMessage;

    public Parser(String regex, String errorMessage) {
        this.regex = regex;
        this.errorMessage = errorMessage;
    }

    public T parse(String text) {
        if (!text.matches(regex)) {
            throw new IllegalArgumentException(errorMessage);
        }

        return convert(text);
    }

    protected static String lottoNumberWithSpacesRegex() {
        return new StringBuilder(REGEX_SPACE)
            .append(lottoNumberRegex())
            .append(REGEX_SPACE)
            .toString();
    }

    protected static String lottoNumberRegex() {
        return new StringBuilder(REGEX_GROUP_BEGINNING)
            .append(REGEX_ONE_TO_NINE).append(REGEX_OR)
            .append(REGEX_TEN_TO_THIRTY_NINE).append(REGEX_OR)
            .append(REGEX_FORTY_TO_FORTY_FIVE)
            .append(REGEX_GROUP_END).toString();
    }

    protected static String repeatRegex(int repeat) {
        return "{" + repeat + "}";
    }

    protected abstract T convert(String text);
}
