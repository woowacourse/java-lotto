package lotto.domain.rating;

public enum Rating {
    FIRST(2_000_000_000, new LottoResult(6, false)),
    SECOND(30_000_000, new LottoResult(5, true)),
    THIRD(1_500_000, new LottoResult(5, false)),
    FOURTH(50_000, new LottoResult(4, false)),
    FIFTH(5_000, new LottoResult(3, false)),
    MISS(0, new LottoResult(0,false));

    private final LottoResult lottoResult;
    private final int reward;

    Rating(final int reward, final LottoResult lottoResult) {
        this.lottoResult = lottoResult;
        this.reward = reward;
    }

    public static Rating getRating(final int matchCount, final boolean containBonusBall) {
        LottoResult lottoResult = new LottoResult(matchCount,containBonusBall);
        for (Rating rating: values()) {
            if (rating.lottoResult.equals(lottoResult)){
                return rating;
            }
        }
        return MISS;
    }

    public LottoResult getLottoResult() {
        return lottoResult;
    }

    public int getReward() {
        return reward;
    }

}
