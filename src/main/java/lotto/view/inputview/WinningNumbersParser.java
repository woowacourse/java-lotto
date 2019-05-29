package lotto.view.inputview;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbersParser {
    private static final String ERROR_NULL_OR_NO_INPUT = "입력이 없습니다.";

    public static List<Integer> getWinningNumbers(String input) {

        if (input == null || input.isEmpty()) {
            throw new NullPointerException(ERROR_NULL_OR_NO_INPUT);
        }

        return Arrays.stream(input.trim().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
