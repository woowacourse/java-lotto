package domain;

import domain.generator.ManualLottoGenerator;

import java.util.Set;

public class WinningNumbers {

    private static final String ERROR_DUPLICATE_BONUS = "보너스 번호가 당첨 번호와 중복됩니다.";

    private final Lotto winningNumbers;
    private final Number bonus;

    public WinningNumbers(final int[] inputNumbers, final int bonus) {
        winningNumbers = new Lotto(new ManualLottoGenerator(inputNumbers).generateNumbers());
        this.bonus = checkBonusRangeAndDuplicate(bonus);
    }

    private Number checkBonusRangeAndDuplicate(final int number) {
        final Number bonus = new Number(number);
        if (winningNumbers.hasMatchedNumber(bonus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
        return bonus;
    }

    public Set<Number> getWinningNumbers() {
        return winningNumbers.getLottoNumbers();
    }

    public Number getBonus() {
        return bonus;
    }
}
