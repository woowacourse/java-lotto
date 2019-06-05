package domain;

import exception.BonusBallDuplicationException;

import java.util.Objects;

/**
 * 당첨 번호를 담당하는 객체
 */
public class WinningLotto {
    private final Lotto lotto;
    private final Number bonusNumber;

    private WinningLotto(Lotto lotto, Number bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new BonusBallDuplicationException("보너스 볼은 로또에 있는 번호들과 겹쳐선 안됩니다.");
        }
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(Lotto lotto, Number bonusNumber) {
        return new WinningLotto(lotto, bonusNumber);
    }

    public Rank match(Lotto userLotto) {
        int countOfMatch = lotto.countEqualNumbers(userLotto);
        return Rank.valueOf(countOfMatch, userLotto.contains(bonusNumber));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(lotto, that.lotto) &&
                Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto, bonusNumber);
    }

    @Override
    public String toString() {
        return lotto.toString() + " + " + bonusNumber.toString();
    }
}
