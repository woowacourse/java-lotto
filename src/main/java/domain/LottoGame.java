package domain;

import java.util.List;

public class LottoGame {

    private final Lottos lottos;

    public LottoGame(final int lottoCounts, LottoNumberGenerator lottoNumberGenerator) {
        this.lottos = new Lottos(lottoCounts, lottoNumberGenerator);
    }

    public WinningStatistics calculateWinningStatistics(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = lottos.calculateLottoReward(winningLotto);
        return new WinningStatistics(lottoRewards);
    }

    public Lottos getLottos() {
        return lottos;
    }
}
