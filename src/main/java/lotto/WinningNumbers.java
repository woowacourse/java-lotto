package lotto;

import static lotto.Lotto.LOTTO_SIZE;

import java.util.HashSet;
import java.util.List;

public class WinningNumbers {
    private final List<Integer> winningNumbers;

    public WinningNumbers(final List<Integer> winningNumbers) {
        validateWinningNumbers(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers.stream()
                .toList();
    }

    public boolean contains(final int number) {
        return winningNumbers.contains(number);
    }

    private static void validateWinningNumbers(final List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE + "개의 고유한 번호를 입력해야 합니다.");
        }

        for (final int winningNumber : winningNumbers) {
            Lotto.validateLottoNumber(winningNumber);
        }
    }
}
