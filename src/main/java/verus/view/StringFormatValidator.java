package verus.view;

import verus.exception.InvalidFormatException;

public class StringFormatValidator {

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
}
