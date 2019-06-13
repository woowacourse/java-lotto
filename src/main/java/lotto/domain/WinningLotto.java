package lotto.domain;

import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private final Lotto winningLotto;
    private final Number bonusNum;

    public WinningLotto(Lotto lotto, Number number) {
        validate(lotto, number);
        this.winningLotto = lotto;
        this.bonusNum = number;
    }

    private void validate(Lotto lotto, Number number) {
        if (lotto.isContain(number)) {
            throw new IllegalArgumentException("로또 번호화 중복된 숫자입니다.");
        }
    }

    public WinningType matchLotto(Lotto lotto) {
        return WinningType.valueOf(countMatchNum(lotto), isMatchBonus(lotto));
    }

    private boolean isMatchBonus(Lotto lotto) {
        return lotto.isContain(bonusNum);
    }

    private int countMatchNum(Lotto lotto) {
        List<Number> winningLotto = this.winningLotto.getLotto();
        winningLotto.retainAll(lotto.getLotto());
        return winningLotto.size();
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public Number getBonusNum() {
        return bonusNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningLotto, that.winningLotto) &&
                Objects.equals(bonusNum, that.bonusNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto, bonusNum);
    }
}
