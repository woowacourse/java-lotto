package domain;

import java.util.List;

public class LottoGame {

    private final LottoGameMoney purchaseMoney;
    private final Lottos lottos;

    public LottoGame(LottoGameMoney purchaseMoney, Lottos lottos) {
        validateNull(purchaseMoney, lottos);
        this.purchaseMoney = purchaseMoney;
        this.lottos = lottos;
    }

    private void validateNull(LottoGameMoney purchaseMoney, Lottos lottos) {
        if (purchaseMoney == null) {
            throw new NullPointerException("LottoGame 생성시 구매 금액이 null 일 수 없습니다.");
        }
        if (lottos == null) {
            throw new NullPointerException("LottoGame 생성시 로또들이 null 일 수 없습니다.");
        }
    }

    public WinningStatistics calculateWinningStatistics(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = lottos.match(winningLotto);
        return new WinningStatistics(purchaseMoney, lottoRewards);
    }
}
