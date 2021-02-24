package lottogame.domain.lotto;

import lottogame.domain.stats.LottoResults;

import java.util.List;

public class LottoGame {
    private Lottos lottos;

    public LottoGame(List<Lotto> lottos) {
        this.lottos = new Lottos(lottos);
    }

    public LottoResults Results(WinningLotto winningLotto) {
        return lottos.matchLottos(winningLotto);
    }

    public Lottos lottos() {
        return lottos;
    }
}
