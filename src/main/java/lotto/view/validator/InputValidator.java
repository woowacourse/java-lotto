package lotto.view.validator;

import java.util.List;
import lotto.exception_message.ExceptionMessage;

public class InputValidator {

    public void validateBlank(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getContent());
        }
    }

    public void validateNumberFormat(String numericContent) {
        try {
            Integer.parseInt(numericContent);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NUMBER_FORMAT.getContent());
        }
    }

    public void validateNumberFormat(List<String> numericContents) {
        try {
            List<Integer> numbers = numericContents.stream().map(Integer::parseInt).toList();
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getContent());
        }
    }
}
