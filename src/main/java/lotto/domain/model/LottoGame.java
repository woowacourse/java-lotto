package lotto.domain.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.PurchaseMoney;
import lotto.domain.WinningLotto;
import lotto.domain.result.GameResult;
import lotto.domain.result.Rank;

public class LottoGame {
    private final Lottos lottos;
    private final WinningLotto winningLotto;
    private final PurchaseMoney money;

    public LottoGame(Lottos lottos, WinningLotto winningLotto, PurchaseMoney money) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
        this.money = money;
    }

    public GameResult getResult() {
        List<Rank> ranks = Arrays.asList(Rank.values());
        GameResult gameResult = new GameResult();

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.isWinningLotto(lotto);
            gameResult.count(rank);
        }
        return gameResult;
    }
}