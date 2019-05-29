package lotto.view.inputview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningNumbersParser {
    private static final String ERROR_NULL_OR_NO_INPUT = "입력이 없습니다.";
    private static final String ERROR_INVALID_NUMBERS_COUNT = "유효한 번호의 개수가 6개가 아닙니다.";
    private static final String ERROR_HAS_DUPLICATED_NUMBER = "중복된 번호가 존재합니다.";
    private static final int LIMIT_MINIMUM_NUMBER = 1;
    private static final int LIMIT_MAXIMUM_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public static List<Integer> getWinningNumbers(String inputNumbers) {
        List<Integer> winningNumbers = getValidInputNumbers(inputNumbers);
        verifyValidWinningNumbers(winningNumbers);

        return winningNumbers;
    }

    private static List<Integer> getValidInputNumbers(String inputNumbers) {
        checkNullEmpty(inputNumbers);

        return getSeparatedNumbers(inputNumbers);
    }

    private static void checkNullEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new NullPointerException(ERROR_NULL_OR_NO_INPUT);
        }
    }

    private static List<Integer> getSeparatedNumbers(String input) {
        return Arrays.stream(input.trim().split(","))
                .map(Integer::parseInt)
                .filter(WinningNumbersParser::isValidRange)
                .collect(Collectors.toList());
    }

    private static boolean isValidRange(Integer number) {
        return LIMIT_MINIMUM_NUMBER <= number && number <= LIMIT_MAXIMUM_NUMBER;
    }

    private static void verifyValidWinningNumbers(List<Integer> winningNumbers) {
        checkNumbersCount(winningNumbers);
        checkDuplicateNumber(winningNumbers);
    }

    private static void checkNumbersCount(List<Integer> winningNumbers) {
        if (winningNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_NUMBERS_COUNT);
        }
    }

    private static void checkDuplicateNumber(List<Integer> winningNumbers) {
        Set<Integer> copyWinningNumbers = new HashSet<>(winningNumbers);

        if (winningNumbers.size() != copyWinningNumbers.size()) {
            throw new IllegalArgumentException(ERROR_HAS_DUPLICATED_NUMBER);
        }
    }
}
