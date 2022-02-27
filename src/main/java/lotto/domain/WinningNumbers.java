package lotto.domain;

import java.util.Optional;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final Number bonusNumber;

    public WinningNumbers(Lotto winningLotto, Number bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
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
