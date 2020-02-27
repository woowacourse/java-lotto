package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoManager {
    private static final int MIN_WIN_COUNT = 3;

    private final List<Lotto> lottos;
    private WinLotto winLotto;
    private LottoResult lottoResult;

    public LottoManager(List<Lotto> lottos, WinLotto winLotto) {
        this.lottos = new ArrayList<>(lottos);
        this.winLotto = winLotto;
        this.lottoResult = new LottoResult();
    }

    public void checkLotto() {
        for (Lotto lotto : lottos) {
            int hitCount = winLotto.calculateHitCount(lotto);
            boolean bonus = winLotto.isMatchBonus(lotto);
            updateLottoResult(hitCount, bonus);
        }
    }

    private void updateLottoResult(int hitCount, boolean bonus) {
        if (hitCount < MIN_WIN_COUNT) {
            return;
        }
        Rank rank = Rank.findRank(hitCount, bonus);
        lottoResult.updateResult(rank);
    }

    public String getResult() {
        return lottoResult.getResult();
    }

    public long calculateTotalReward() {
        return lottoResult.calculateTotalReward();
    }
}
