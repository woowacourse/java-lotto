package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {

    private final Lottos lottos;

    public LottoGame(LottoGameMoney money) {
        int lottoCount = money.purchasableLottoCount();
        List<Lotto> lottos = getLottos(lottoCount);

        this.lottos = new Lottos(lottos);
    }

    private List<Lotto> getLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = LottoFactory.createLotto(new RandomLottoNumberGenerator());
            lottos.add(lotto);
        }

        return lottos;
    }

    public WinningStatistics calculateWinningStatistics(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = lottos.match(winningLotto);

        return new WinningStatistics(lottoRewards);
    }

    public Lottos getLottos() {
        return lottos;
    }
}
