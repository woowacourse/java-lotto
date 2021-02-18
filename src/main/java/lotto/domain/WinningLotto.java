package lotto.domain;

import java.util.List;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    private WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(List<Integer> numbers, int bonusNumber) {
        return new WinningLotto(Lotto.fromNumbers(numbers), LottoNumber.from(bonusNumber));
    }

    public Lotto getLotto() {
        return lotto;
    }

    public Rank match(Lotto lotto) {
        long matchCount = this.lotto.findMatchingCount(lotto);

        boolean requiredBonus = lotto.contains(bonusNumber);
        if (requiredBonus) {
            matchCount++;
        }

        return Rank.getRankByMatchCount(matchCount, requiredBonus);
    }
}
