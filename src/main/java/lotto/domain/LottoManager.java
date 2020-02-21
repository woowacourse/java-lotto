package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoManager {
    public static final int MIN_WIN_COUNT = 3;

    private final List<Lotto> lotteries;
    private WinLotto winLotto;

    public LottoManager(List<Lotto> lotteries, WinLotto winLotto) {
        this.lotteries = new ArrayList<>(lotteries);
        this.winLotto = winLotto;
    }

    public void findHitLotto() {
        for (Lotto lotto : lotteries) {
            int hitCount = winLotto.compare(lotto);
            boolean bonus = winLotto.isMatchBonus(lotto);
            lottoResultUpdate(hitCount, bonus);
        }
    }

    private void lottoResultUpdate(int hitCount, boolean isBonus) {
        if (hitCount < MIN_WIN_COUNT) {
            return;
        }
        LottoResult lottoRank = LottoResult.findRank(hitCount);
        if (lottoRank.isSecondRank(isBonus)) {
            lottoRank = LottoResult.SECOND;
        }
        lottoRank.TicketCountPlus();
    }
}
