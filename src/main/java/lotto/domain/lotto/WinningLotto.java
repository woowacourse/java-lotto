package lotto.domain.lotto;

import lotto.domain.lotto.exception.IllegalNumberCombinationException;
import lotto.domain.money.Prize;

import java.util.List;

public class WinningLotto {
    private Lotto winningLotto;
    private LottoNumber bonusNumber;

    private WinningLotto(List<Integer> numbers, int bonusNumber) {
        this.winningLotto = new Lotto(numbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public static WinningLotto create(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalNumberCombinationException("보너스 숫자가 로또 숫자들과 중복됩니다.");
        }
        return new WinningLotto(numbers, bonusNumber);
    }

    public Prize prizeOf(Lotto lotto) {
        int matchCount = lotto.countMatchLottoNumber(this.winningLotto);
        Prize prize = Prize.getPrizeRank(matchCount, lotto.containsNumber(bonusNumber));
        return prize;
    }
}
