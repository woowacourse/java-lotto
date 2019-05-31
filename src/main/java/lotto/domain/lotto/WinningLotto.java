package lotto.domain.lotto;

import lotto.domain.money.Prize;

import java.util.List;

public class WinningLotto {
    private Lotto lotto;
    private LottoNumber bonusNumber;

    private WinningLotto(List<Integer> numbers, int bonusNumber) {
        this.lotto = new Lotto(numbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public static WinningLotto create(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalNumberCombinationException();
        }
        return new WinningLotto(numbers, bonusNumber);
    }

    public Prize prizeOf(Lotto lotto) {
        int matchCount = lotto.countMatchLottoNumber(this.lotto);
        Prize prize = Prize.getPrizeRank(matchCount, lotto.containsNumber(bonusNumber));
        return prize;
    }
}
