package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Lotto lotto;

    public WinningLotto(Lotto lotto) {
        this.lotto = lotto;
    }

    public int countMatchNum(Lotto lotto) {
        List<Number> winningLotto = this.lotto.getLotto();
        winningLotto.retainAll(lotto.getLotto());
        return winningLotto.size();
    }


}
