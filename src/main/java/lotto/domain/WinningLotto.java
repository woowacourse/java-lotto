package lotto.domain;

import lotto.domain.exceptions.InvalidBonusBallException;

import java.util.List;

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

    @Override
    public String toString() {
        return lotto + " + " + bonusBall;
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonus() {
        return bonusBall;
    }
}
