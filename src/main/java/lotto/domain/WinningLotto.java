package lotto.domain;

public class WinningLotto {
    public static final double SECOND_RANK_MATCH_COUNT = 5.5;

    private final Lotto winningLotto;
    private final BonusBall bonusBall;

    public WinningLotto(Lotto winningLotto, BonusBall bonusBall) {
        this.winningLotto = winningLotto;
        this.bonusBall = bonusBall;
    }

    public LottoRank getLottoResult(Lotto lotto) {
        double count = getMatchCount(lotto);
        return LottoRank.getRank(count);
    }

    private double getMatchCount(Lotto lotto) {
        long count = getCount(lotto);

        if (isSecondRank(count, lotto)) {
            return SECOND_RANK_MATCH_COUNT;
        }

        return count;
    }

    private boolean isSecondRank(long count, Lotto lotto) {
        return count == LottoGenerator.LOTTO_POSSESSION_NUMBER && lotto.isContainNumber(bonusBall.getBonusNumber());
    }

    private long getCount(Lotto lotto) {
        long matchCount = winningLotto.compareOtherLottoMatchCount(lotto);

        if (lotto.isContainNumber(bonusBall.getBonusNumber())) {
            matchCount++;
        }

        return matchCount;
    }
}