package lotto.domain;

import java.util.List;

public class WinLotto {

    private final List<Integer> winNumbers;
    private final int bonusNumber;

    public WinLotto(final List<Integer> winNumbers, final int bonusNumber) {
        checkDuplicateWinNumbers(winNumbers);
        checkDuplicateBonusNumber(winNumbers, bonusNumber);
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicateWinNumbers(final List<Integer> winNumbers) {
        if (winNumbers.size() != calculateDistinctSize(winNumbers)) {
            throw new IllegalArgumentException("[ERROR] 중복된 당첨 번호가 존재합니다.");
        }
    }

    private long calculateDistinctSize(final List<Integer> winNumbers) {
        return winNumbers.stream()
                .distinct()
                .count();
    }

    private void checkDuplicateBonusNumber(final List<Integer> winNumbers, final int bonusNumber) {
        if (winNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 볼이 당첨 번호와 중복됩니다.");
        }
    }
}
