package view;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputConverter {

    private static final String WINNING_NUMBER_REGEX = "([0-9]+)(,[0-9]+)*";
    private static final String BONUS_NUMBER_REGEX = "[0-9]+";
    private static final String WINNING_NUMBER_DELIMITER = ",";

    public static List<Integer> convertWinningNumbers(String input) {
        Matcher matcher = Pattern.compile(WINNING_NUMBER_REGEX).matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("당첨 번호 입력 양식이 올바르지 않습니다.");
        }

        return Arrays.stream(input.split(WINNING_NUMBER_DELIMITER))
                .map(Integer::parseInt)
                .peek(InputConverter::validateNumberRange)
                .toList();
    }

    public static int convertBonusNumber(String input) {
        Matcher matcher = Pattern.compile(BONUS_NUMBER_REGEX).matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("당첨 번호 입력 양식이 올바르지 않습니다.");
        }

        int bonusNumber = Integer.parseInt(input);
        validateNumberRange(bonusNumber);

        return bonusNumber;
    }

    private static void validateNumberRange(int value) {
        if (value < 1 || value > 45) {
            throw new IllegalArgumentException("당첨 번호는 1 ~ 45만 가능합니다.");
        }
    }
}
