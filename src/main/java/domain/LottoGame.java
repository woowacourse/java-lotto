package domain;

import java.util.List;

public class LottoGame {

    private final Lottos lottos;

    public LottoGame(Lottos lottos) {
        this.lottos = lottos;
    }

    public WinningStatistics calculateWinningStatistics(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = lottos.match(winningLotto);

        return new WinningStatistics(lottoRewards);
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
