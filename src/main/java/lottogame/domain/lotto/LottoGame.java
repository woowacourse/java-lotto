package lottogame.domain.lotto;

import lottogame.domain.stats.LottoResults;

import java.util.List;

public class LottoGame {
    private Lottos lottos;
    private WinningLotto winningLotto;

    public LottoGame(List<Lotto> lottos, WinningLotto winningLotto) {
        this.lottos = new Lottos(lottos);
        this.winningLotto = winningLotto;
    }

    public LottoResults results() {
        return lottos.matchLottos(winningLotto);
    }
}
