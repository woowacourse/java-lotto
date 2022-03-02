package domain;

import domain.generator.ManualLottoGenerator;

import java.util.HashSet;
import java.util.Set;

public class WinningNumbers {

    private static final String ERROR_DUPLICATE_BONUS = "보너스 번호가 당첨 번호와 중복됩니다.";
    private static final String ERROR_BALL_NON_INTEGER = "로또 번호는 숫자만 입력해줘야 합니다.";

    private final Set<Number> winningNumbers;
    private final Number bonus;

    public WinningNumbers(final String inputNumbers, final String bonusNumber) {
        Set<Integer> numbers = new ManualLottoGenerator(inputNumbers).generateNumbers();
        winningNumbers = new HashSet<>();
        for (Integer number : numbers) {
            winningNumbers.add(new Number(number));
        }
        bonus = validateBonusNumber(bonusNumber);
    }

    private static int checkNonInteger(final String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_BALL_NON_INTEGER);
        }
    }

    private Number validateBonusNumber(final String number) {
        final Number bonusNumber = new Number(checkNonInteger(number));
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
        return bonusNumber;
    }

    public Set<Number> getWinningNumbers() {
        return winningNumbers;
    }

    public Number getBonus() {
        return bonus;
    }

}
