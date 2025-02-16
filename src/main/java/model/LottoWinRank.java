package model;

import java.util.Arrays;

public enum LottoWinRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(2, false, 0);

    private final Integer matchNumberCount;
    private final Boolean matchBonusNumber;
    private final Integer price;

    LottoWinRank(Integer matchNumberCount, Boolean matchBonusNumber, Integer price) {
        this.matchNumberCount = matchNumberCount;
        this.matchBonusNumber = matchBonusNumber;
        this.price = price;
    }

    public static LottoWinRank calculateLottoWinRank(LottoNumbers purchasedLotto, WinLotto winLotto) {
        Integer matchNumberCount = winLotto.countMatchNumber(purchasedLotto);
        Boolean bonusMatch = winLotto.bonusMatch(purchasedLotto);
        return Arrays.stream(LottoWinRank.values())
                .filter(lottoWinRank -> lottoWinRank.isMatched(matchNumberCount, bonusMatch))
                .findFirst()
                .orElse(NONE);
    }

    public Integer getMatchNumberCount() {
        return matchNumberCount;
    }

    public Integer getPrice() {
        return price;
    }

    public boolean matchBonusNumber() {
        return matchBonusNumber;
    }

    private boolean isMatched(int matchNumberCount, boolean bonusNumberMatch) {
        return this.matchNumberCount.equals(matchNumberCount) && this.matchBonusNumber.equals(bonusNumberMatch);
    }
}
