package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {
    private static final String ERROR_NOT_MATCH_WINNING_NUMBER_SIZE = "지난 주 당첨 번호 개수는 6개로 입력해주세요.";
    private static final String ERROR_OUT_OF_RANGE_WINNING_NUMBERS = "당첨 번호의 범위는 1 ~ 45 사이로 입력해주세요.";
    private static final String ERROR_DUPLICATION_WINNING_NUMBERS = "당첨 번호에 중복이 존재합니다.";

    private final List<Integer> winningNumbers;

    public WinningNumbers(List<Integer> winningNumbers) {
        validateNumberOfWinningNumbers(winningNumbers);
        validateDuplicationWinningNumbers(winningNumbers);
        validateRangeWinningNumbers(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validateNumberOfWinningNumbers(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_NOT_MATCH_WINNING_NUMBER_SIZE);
        }
    }

    private void validateRangeWinningNumbers(List<Integer> winningNumbers) {
        winningNumbers.forEach(number -> {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(ERROR_OUT_OF_RANGE_WINNING_NUMBERS);
            }
        });
    }

    private void validateDuplicationWinningNumbers(List<Integer> winningNumbers) {
        Set<Integer> distinct = new HashSet<>(winningNumbers);
        if (distinct.size() != winningNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_WINNING_NUMBERS);
        }
    }

    public boolean contains(Object number) {
        return winningNumbers.contains(number);
    }
}
