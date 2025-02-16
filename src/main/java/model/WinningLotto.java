package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningLotto {
    private static final String BONUS_INPUT_ERROR_MESSAGE = "보너스볼은 당첨 번호와 중복되지 않는 1 이상 45 이하의 정수여야합니다.\n";
    private static final int MAX_UNIQUE_NUMBERS = 12;

    private final Lotto winningNumbers;
    private final int bonus;

    public WinningLotto(List<Integer> winningNumbers, int bonus) {
        this.winningNumbers = new Lotto(new HashSet<>(winningNumbers));
        validateBonus(bonus);
        this.bonus = bonus;
    }

    public boolean isBonusMatch(Lotto lotto) {
        return lotto.contains(bonus);
    }

    public int calculateDuplicateNumber(Set<Integer> lotto) {
        Set<Integer> union = new HashSet<>(lotto);
        union.addAll(winningNumbers.toDto().lotto());
        return MAX_UNIQUE_NUMBERS - union.size();
    }

    private void validateBonus(int bonus) {
        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException(BONUS_INPUT_ERROR_MESSAGE);
        }

        if (isDuplicateWithWinningNumbers(bonus)) {
            throw new IllegalArgumentException(BONUS_INPUT_ERROR_MESSAGE);
        }
    }

    private boolean isDuplicateWithWinningNumbers(int bonus) {
        return winningNumbers.contains(bonus);
    }
}
