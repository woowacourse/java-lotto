package domain;

import java.util.List;

public class LottoGame {

    private final Lottos lottos;

    public LottoGame(Lottos lottos) {
        validateNull(lottos);
        this.lottos = lottos;
    }

    private void validateNull(Lottos lottos) {
        if (lottos == null) {
            throw new NullPointerException("null 로 LottoGame 을 생성할 수 없습니다.");
        }
    }

    public WinningStatistics calculateWinningStatistics(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = lottos.match(winningLotto);

        return new WinningStatistics(lottoRewards);
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
