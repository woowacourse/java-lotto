package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {
    private final LottoNumber bonusBall;

    public WinningLotto(List<Integer> numbers, int bonus) {
        super(numbers);
        if (numbers.contains(bonus)) {
            throw new InvalidBonusBallException("보너스 볼은 당첨 번호에 포함되면 안 됩니다.");
        }
        this.bonusBall = LottoNumber.get(bonus);
    }

    public Rank match(Lotto lotto) {
        return Rank.valueOf(countMatches(lotto), lotto.contains(bonusBall));
    }
}
