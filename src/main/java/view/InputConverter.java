package view;

import static exception.ErrorMessage.INPUT_FORMAT_ERROR;
import static exception.ErrorMessage.NUMBER_TYPE_ERROR;

import exception.UserInputException;
import java.util.Arrays;
import java.util.List;

public class InputConverter {
    private static final String DELIMITER = ", ";

    public static Integer convertNumber(String input) {
        Validator.validateNumberFormat(input.trim());
        return Integer.parseInt(input.trim());
    }

    public static List<Integer> convertWinningLotto(String originalInput) {
        String input = originalInput.trim();
        Validator.validateWinningLottoPattern(originalInput);
        return Arrays.stream(input.split(DELIMITER)).map(Integer::parseInt).toList();
    }

    static private class Validator {
        private static final String INPUT_FORMAT_PATTERN = "^\\d+(, \\d+){5}$";
        private static final String NUMBER_TYPE_PATTERN = "^\\d+$";

        private static void validateWinningLottoPattern(String originalInput) {
            String input = originalInput.trim();
            if (!input.matches(INPUT_FORMAT_PATTERN)) {
                throw UserInputException.from(INPUT_FORMAT_ERROR);
            }
        }

        private static void validateNumberFormat(String input) {
            if (!input.matches(NUMBER_TYPE_PATTERN)) {
                throw UserInputException.from(NUMBER_TYPE_ERROR);
            }
        }
    }
}
