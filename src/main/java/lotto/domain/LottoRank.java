package lotto.domain;

public enum LottoRank {
    RANK_FIRST(2000000000),
    RANK_SECOND(30000000),
    RANK_THIRD(1500000),
    RANK_FOURTH(50000),
    RANK_FIFTH(5000);


    private final int prizeAmount;

    LottoRank(final int prizeAmount) {
        this.prizeAmount = prizeAmount;
    }
}
