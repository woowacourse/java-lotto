package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoManager {
    public static final int MIN_WIN_COUNT = 3;

    private final List<Lotto> lotteries;
    private WinLotto winLotto;

    public LottoManager(List<Lotto> lotteris, WinLotto winLotto) {
        this.lotteries = new ArrayList<>(lotteris);
        this.winLotto = winLotto;
    }

    public void compareLotto() {
        for (Lotto lotto : lotteries) {
            int hitCount = winLotto.compare(lotto);
            boolean isBonusHit = winLotto.isMatchBonus(lotto);
            lottoResultUpdate(hitCount, isBonusHit);
        }
    }

    private void lottoResultUpdate(int hitCount, boolean isBonus) {
        if (hitCount < MIN_WIN_COUNT) {
            return;
        }
        LottoResult lottoRank = LottoResult.findRank(hitCount);
        if (isSecondRank(lottoRank, isBonus)) {
            lottoRank = LottoResult.SECOND;
        }
        lottoRank.TicketCountPlus();
    }

    private boolean isSecondRank(LottoResult lottoResult, boolean isBonus) {
        return lottoResult == LottoResult.THIRD && isBonus;
    }
}
