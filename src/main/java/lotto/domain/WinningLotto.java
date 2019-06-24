package lotto.domain;

import lotto.domain.exceptions.InvalidBonusBallException;

import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(List<Integer> numbers, int bonus) {
        this.lotto = Lotto.create(new ManualLottoNumbersGenerator(numbers));
        if (numbers.contains(bonus)) {
            throw new InvalidBonusBallException("보너스 볼은 당첨 번호에 포함되면 안 됩니다.");
        }
        this.bonusBall = LottoNumber.valueOf(bonus);
    }

    public Rank match(Lotto lotto) {
        return Rank.valueOf(this.lotto.countMatches(lotto), lotto.contains(bonusBall));
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonus() {
        return bonusBall;
    }

    @Override
    public String toString() {
        return lotto + " + " + bonusBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(lotto, that.lotto) &&
                Objects.equals(bonusBall, that.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto, bonusBall);
    }
}
