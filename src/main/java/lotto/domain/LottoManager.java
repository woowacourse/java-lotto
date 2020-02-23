package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoManager {
    public static final int MIN_WIN_COUNT = 3;

    private final List<Lotto> lotteries;
    private WinLotto winLotto;
    private LottoResult lottoResult;

    public LottoManager(List<Lotto> lotteries, WinLotto winLotto) {
        this.lotteries = new ArrayList<>(lotteries);
        this.winLotto = winLotto;
        this.lottoResult = new LottoResult();
    }

    public void checkLotto() {
        for (Lotto lotto : lotteries) {
            int hitCount = winLotto.compare(lotto);
            boolean bonus = winLotto.isMatchBonus(lotto);
            lottoResultUpdate(hitCount, bonus);
        }
    }

    private void lottoResultUpdate(int hitCount, boolean bonus) {
        if (hitCount < MIN_WIN_COUNT) {
            return;
        }
        Rank lottoRank = Rank.findRank(hitCount);
        lottoRank = lottoRank.isSecondRank(bonus);
        lottoResult.plusTicketCount(lottoRank);
    }

    public String getResult() {
        return lottoResult.getResult();
    }

    public long calculateTotalReward() {
        return lottoResult.calculateTotalReward();
    }
}
