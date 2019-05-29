package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoResult {

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
        return getEarning() / (lottos.getSize() * 1000) * 100;
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
