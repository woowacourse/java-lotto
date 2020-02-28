package lotto.domain;

public class LottoManager {
    private static final int MIN_WIN_COUNT = 3;

    private final Lottos lottos;
    private WinLotto winLotto;
    private LottoResult lottoResult;

    public LottoManager(Lottos lottos, WinLotto winLotto) {
        this.lottos = lottos;
        this.winLotto = winLotto;
        this.lottoResult = new LottoResult();
    }

    public void checkLotto() {
        for (Lotto lotto : lottos.getLottos()) {
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
