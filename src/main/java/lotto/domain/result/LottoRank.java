package lotto.domain.result;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;
    private static final String INVALID_COUNT_ERROR = "는 유효하지 않은 값입니다.";

    private final int rank;
    private final int money;

    LottoRank(int rank, int money) {
        this.rank = rank;
        this.money = money;
    }

    public static LottoRank valueOf(int count, boolean isBonusNum) {
        if (count < WINNING_MIN_COUNT) {
            return MISS;
        }

        if (SECOND.matchCount(count) && isBonusNum) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter((lottoRank) -> lottoRank.matchCount(count))
                .filter(lottoRank -> !lottoRank.equals(SECOND))
                .findAny()
                .orElseThrow(() -> new InvalidRankCountException(count + INVALID_COUNT_ERROR));
    }

    private boolean matchCount(int count) {
        return this.rank == count;
    }

    public int getRank() {
        return rank;
    }

    public int getMoney() {
        return money;
    }
}
