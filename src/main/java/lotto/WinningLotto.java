package lotto;

import java.util.List;

public class WinningLotto {
    private Lotto lotto;
    private LottoNumber bonusNumber;


    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        if ( numbers.contains(bonusNumber)) {
            throw new IllegalNumberCombinationException();
        }
        lotto = new Lotto(numbers);
        this.bonusNumber = new LottoNumber(bonusNumber);

    }

    public Prize prizeOf(Lotto lotto) {
        int matchCount = lotto.countMatchLottoNumber(this.lotto);
        Prize prize = Prize.getPrizeRank(matchCount, lotto.containsNumber(bonusNumber));
        return prize;
    }
}
