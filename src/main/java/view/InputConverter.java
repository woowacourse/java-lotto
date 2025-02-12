package view;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputConverter {

    private static final String WINNING_NUMBER_REGEX = "([0-9]+)(,[0-9]+)*";
    private static final String WINNING_NUMBER_DELIMITER = ",";

    public static List<String> convertWinningNumbers(String input) {
        Matcher matcher = Pattern.compile(WINNING_NUMBER_REGEX).matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("당첨 번호 입력 양식이 올바르지 않습니다.");
        }

        return Arrays.stream(input.split(WINNING_NUMBER_DELIMITER)).toList();
    }
}
