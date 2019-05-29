package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.LottoRule.*;

public class LottoResult {

    private static final int RATE = 100;

    private final WinningLotto winningLotto;
    private final Lottos lottos;

    public LottoResult(WinningLotto winningLotto, Lottos lottos) {
        this.winningLotto = winningLotto;
        this.lottos = lottos;
    }

    public List<Rank> getRank() {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos.getList()) {
            ranks.add(winningLotto.getWinning(lotto));
        }
        return ranks;
    }

    public int getEarningsRate() {
        return getEarning() / (lottos.getSize() * MONEY_PER_LOTTO.get()) * RATE;
    }

    private int getEarning() {
        int earning = 0;
        List<Rank> ranks = getRank();
        for (Rank rank : ranks) {
            earning += rank.getWinningMoney();
        }
        return earning;
    }
}
