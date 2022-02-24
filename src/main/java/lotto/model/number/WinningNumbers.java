package lotto.model.number;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {
    private static final String ERROR_SIZE = "[ERROR] 당첨 번호는 6개여야 합니다";
    private static final String ERROR_DUPLICATE = "[ERROR] 당첨 번호는 중복되면 안됩니다";
    private static final int WINNING_NUMBERS_SIZE = 6;

    private final List<WinningNumber> winningNumbers;

    private WinningNumbers(List<WinningNumber> winningNumbers) {
        validate(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validate(List<WinningNumber> winningNumbers) {
        checkSize(winningNumbers);
        checkDuplicate(winningNumbers);
    }

    private void checkSize(List<WinningNumber> winningNumbers) {
        if (winningNumbers.size() != WINNING_NUMBERS_SIZE) {
            throw new IllegalArgumentException(ERROR_SIZE);
        }
    }

    private void checkDuplicate(List<WinningNumber> winningNumbers) {
        if (getDistinctCount(winningNumbers) != winningNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    public static WinningNumbers from(String[] inputs) {
        List<WinningNumber> winningNumbers = Arrays.stream(inputs)
                .map(WinningNumber::from)
                .collect(Collectors.toList());
        return new WinningNumbers(winningNumbers);
    }

    private int getDistinctCount(List<WinningNumber> winningNumbers) {
        return (int) winningNumbers.stream()
                .distinct()
                .count();
    }

    public boolean match(int number) {
        return winningNumbers.stream().anyMatch(winningNumber -> winningNumber.match(number));
    }
}
