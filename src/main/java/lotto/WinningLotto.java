package lotto;

import java.util.List;

public class WinningLotto {
    private Lotto lotto;

    public WinningLotto(List<Integer> numbers) {
        lotto = new Lotto(numbers);
    }

    public Prize prizeOf(Lotto lotto) {
        int matchCount = lotto.countMatchLottoNumber(this.lotto);
        Prize prize = Prize.getPrizeRank(matchCount);
        return prize;
    }
}
