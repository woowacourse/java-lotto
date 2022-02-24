package lotto.view;

public abstract class Parser<T> {

    public T parse(String text) {
        if (!text.matches(regex())) {
            throw new IllegalArgumentException(errorMessage());
        }

        return convert(text);
    }

    protected static String numberWithSpacesRegex() {
        return "\\s*" + numberRegex() + "\\s*";
    }

    protected static String numberRegex() {
        return "[1-9][0-9]*";
    }

    protected abstract T convert(String text);

    protected abstract String regex();

    protected abstract String errorMessage();
}
