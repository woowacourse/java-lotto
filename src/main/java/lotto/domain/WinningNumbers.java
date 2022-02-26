package lotto.domain;

import java.util.List;
import java.util.Optional;

public class WinningNumbers {

    private static final String DUPLICATE_NUMBER_MESSAGE = "중복입니다.";
    private static final String BONUS_ALREADY_EXIST = "이미 보너스 볼이 존재합니다.";

    private final Lotto winningLotto;
    private Number bonusNumber = null;

    public WinningNumbers(List<Integer> numbers) {
        this.winningLotto = new Lotto(numbers);
    }

    public void initBonusNumber(final int value) {
        if (bonusNumber != null) {
            throw new IllegalArgumentException(BONUS_ALREADY_EXIST);
        }

        final Number bonusNumber = Number.getInstance(value);
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_MESSAGE);
        }
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
