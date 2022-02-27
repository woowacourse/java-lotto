package domain;

import java.util.List;

public class LottoGame {

    private final Lottos lottos;

    public LottoGame(LottoGameMoney money) {
        this.lottos = new Lottos(money.purchasableLottoCount(), new RandomLottoNumberGenerator());
    }

    public WinningStatistics calculateWinningStatistics(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = lottos.calculateLottoReward(winningLotto);
        return new WinningStatistics(lottoRewards);
    }

    public Lottos getLottos() {
        return lottos;
    }
}
