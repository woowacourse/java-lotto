package lotto.view.inputview;

import java.util.List;

public class WinningNumbersParser {
    private static final String ERROR_NULL_OR_NO_INPUT = "입력이 없습니다.";

    public static int getWinningNumbers(String input) {

        if (input == null || input.isEmpty()) {
            throw new NullPointerException(ERROR_NULL_OR_NO_INPUT);
        }

        return 0;
    }
}
