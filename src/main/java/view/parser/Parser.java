package view.parser;

public abstract class Parser<T> {
    protected static final String REGEX_BEGINNING = "^";
    protected static final String REGEX_END = "$";
    protected static final String REGEX_GROUP_BEGINNING = "(";
    protected static final String REGEX_GROUP_END = ")";
    protected static final String REGEX_COMMA = ",";
    protected static final String REGEX_SPACE = "\\s*";
    protected static final String REGEX_ONE_TO_NINE = "[1-9]";
    protected static final String REGEX_ZERO_TO_NINE = "[0-9]";
    protected static final String REGEX_ASTERISK = "*";

    protected static String numberWithSpacesRegex() {
        return REGEX_SPACE + numberRegex() + REGEX_SPACE;
    }

    protected static String numberRegex() {
        return "[1-9][0-9]*";
    }

    protected static String repeatRegex(int repeat) {
        return "{" + repeat + "}";
    }

    public T parse(String text) {
        if (!text.matches(regex())) {
            throw new IllegalArgumentException(errorMessage());
        }

        return convert(text);
    }

    protected abstract T convert(String text);

    protected abstract String regex();

    protected abstract String errorMessage();
}
