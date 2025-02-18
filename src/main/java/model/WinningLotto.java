package model;

import java.util.HashSet;
import java.util.List;

public class WinningLotto {
    private static final String BONUS_INPUT_ERROR_MESSAGE = "보너스볼은 당첨 번호와 중복되지 않는 1 이상 45 이하의 정수여야합니다.\n";

    private final Lotto winningNumbers;
    private final LottoNumber bonus;

    public WinningLotto(List<Integer> winningNumbers, int bonus) {
        this.winningNumbers = new Lotto(new HashSet<>(winningNumbers));
        LottoNumber bonusNumber = new LottoNumber(bonus);
        validateBonus(bonusNumber);
        this.bonus = bonusNumber;
    }

    public boolean isBonusMatch(Lotto lotto) {
        return lotto.contains(bonus);
    }

    private void validateBonus(LottoNumber bonus) {
        if (isDuplicateWithWinningNumbers(bonus)) {
            throw new IllegalArgumentException(BONUS_INPUT_ERROR_MESSAGE);
        }
    }

    private boolean isDuplicateWithWinningNumbers(LottoNumber bonus) {
        return winningNumbers.contains(bonus);
    }

    public Lotto getWinningNumbers() {
        return winningNumbers;
    }
}
