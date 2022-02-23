package view;

public abstract class Parser<T> {

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

    protected abstract T convert(String text);
}
