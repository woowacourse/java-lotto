package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningLotto {

    public static final String NUMBER_REGEX = "^[0-9]*[0-9]$";
    public static final String WINNING_NUMBERS_INPUT_ERROR_MESSAGE = "당첨 번호는 중복되지 않는 1 이상 45 이하의 정수여야합니다.";

    private final List<Integer> winningNumbers = new ArrayList<>();
    //private final int bonus;

    public WinningLotto(String rawWinningNumbers) {
        Arrays.stream(rawWinningNumbers.split(","))
                .map(this::getWinningNumber)
                .forEach(winningNumbers::add);
        validateDuplicateWinningNumbers();
    }

    private int getWinningNumber(String rawNumber) {
        rawNumber = rawNumber.trim();
        validateWinningNumber(rawNumber);
        return Integer.parseInt(rawNumber);
    }

    private void validateWinningNumber(String rawNumber) {
        if (!rawNumber.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_INPUT_ERROR_MESSAGE);
        }

        int number = Integer.parseInt(rawNumber);
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(WINNING_NUMBERS_INPUT_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateWinningNumbers() {
        Set<Integer> set = new HashSet<>(winningNumbers);
        if (set.size() != 6) {
            throw new IllegalArgumentException(WINNING_NUMBERS_INPUT_ERROR_MESSAGE);
        }

    }
}
