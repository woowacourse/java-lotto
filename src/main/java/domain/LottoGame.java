package domain;

import java.util.List;

public class LottoGame {

    private final Lottos lottos;

    public LottoGame(final int lottoCounts) {
        this.lottos = new Lottos(lottoCounts, new RandomLottoNumberGenerator());
    }

    public WinningStatistics calculateWinningStatistics(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = lottos.calculateLottoReward(winningLotto);
        return new WinningStatistics(lottoRewards);
    }

    public Lottos getLottos() {
        return lottos;
    }
}
