package lotto.domain;

import java.util.Optional;

public class WinningNumbers {

    private static final String DUPLICATE_NUMBER_MESSAGE = "중복입니다.";

    private final Lotto winningLotto;
    private final Number bonusNumber;

    public WinningNumbers(Lotto winningLotto, Number bonusNumber) {
        validateBonusNumber(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto winningLotto, Number bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_MESSAGE);
        }
    }

    public Optional<WinningPrice> getWinningPrice(Lotto lotto) {
        int count = getCount(lotto);
        boolean containsBonus = false;

        if (count == WinningPrice.Five.getCount()) {
            containsBonus = lotto.contains(bonusNumber);
        }

        return WinningPrice.of(count, containsBonus);
    }

    private int getCount(Lotto lotto) {
        int count = 0;
        for (Number number : winningLotto.getNumbers()) {
            if (lotto.contains(number)) {
                count++;
            }
        }

        return count;
    }

    @Override
    public String toString() {
        return "WinningNumbers{" +
                "winningLotto=" + winningLotto +
                ", bonusNumber=" + bonusNumber +
                '}';
    }
}
