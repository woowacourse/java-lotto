package domain;

import java.util.List;

import domain.strategy.LottoGeneratorStrategy;

public class LottoGame {

    private final Lottos lottos;

    public LottoGame(final int lottoCounts, LottoGeneratorStrategy lottoGeneratorStrategy) {
        this.lottos = new Lottos(lottoCounts, lottoGeneratorStrategy);
    }

    public WinningStatistics calculateWinningStatistics(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = lottos.calculateLottoReward(winningLotto);
        return new WinningStatistics(lottoRewards);
    }

    public Lottos getLottos() {
        return lottos;
    }
}
