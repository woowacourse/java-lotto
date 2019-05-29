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

        List<Integer> winningNumbers = Arrays.stream(input.trim().split(","))
                .map(Integer::parseInt)
                .filter(number -> 1 <= number && number <= 45)
                .collect(Collectors.toList());

        if(winningNumbers.size() != 6){
            throw new IllegalArgumentException("유효한 번호의 개수가 6개가 아닙니다.");
        }

        return winningNumbers;
    }
}
