package domain;

import java.util.HashSet;
import java.util.Set;

public class WinningNumbers {

    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_DUPLICATE_NUMBER = "로또 번호는 중복되지 않은 6자리 숫자여야 합니다.";
    private static final String ERROR_DUPLICATE_BONUS = "보너스 번호가 당첨 번호와 중복됩니다.";

    private final Set<Number> winningNumbers;
    private Number bonus;

    public WinningNumbers(Set<Integer> numbers) {
        checkRightSize(numbers);
        winningNumbers = new HashSet<>();
        for (Integer number : numbers) {
            winningNumbers.add(new Number(number));
        }
    }

    private void checkRightSize(final Set<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    public void addBonusNumber(final int number) {
        final Number bonusNumber = new Number(number);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
        bonus = bonusNumber;
    }

    public Set<Number> getWinningNumbers() {
        return winningNumbers;
    }

    public Number getBonus() {
        return bonus;
    }

}
