package lottogame.domain.lotto;

import lottogame.domain.stats.LottoResults;

public class LottoGame {
    private Lottos lottos;
    private WinningLotto winningLotto;

    public LottoGame(Lottos lottos, WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public LottoResults results() {
        return lottos.matchLottos(winningLotto);
    }
}
