package domain;

import java.util.List;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;

    private final Lottos lottos;

    public LottoGame(Money money) {
        int lottoCount = money.getAmount() / LOTTO_PRICE;
        this.lottos = new Lottos(lottoCount, new RandomLottoNumberGenerator());
    }

    public WinningStatistics calculateWinningStatistics(WinningLotto winningLotto, LottoNumber bonusNumber) {
        List<LottoReward> lottoRewards = lottos.calculateLottoReward(winningLotto, bonusNumber);
        return new WinningStatistics(lottoRewards);
    }

    public Lottos getLottos() {
        return lottos;
    }
}
