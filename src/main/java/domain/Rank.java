package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000L),
    SECOND(5, 30_000_000L),
    THIRD(5, 1_500_000L),
    FOURTH(4, 50_000L),
    FIFTH(3, 5_000L),
    NO_MATCH(0, 0L);

    private static final int SAME_NUMBER_COUNT_OF_SECOND_AND_THIRD = 5;
    private final int sameNumberCount;
    private final long prize;

    Rank(int sameNumberCount, long prize) {
        this.sameNumberCount = sameNumberCount;
        this.prize = prize;
    }

    public static Rank createByLottoAndWinningLotto(Lotto lotto, WinningLotto winningLotto) {
        int sameNumberCount = lotto.getSameNumberCount(winningLotto.getLotto());
        boolean hasBonusNumber = lotto.containsLottoNumber(winningLotto.getBonusNumber());

        if (sameNumberCount != SAME_NUMBER_COUNT_OF_SECOND_AND_THIRD) {
            return findRankBySameNumberCount(sameNumberCount);
        }

        if (hasBonusNumber) {
            return SECOND;
        }

        return THIRD;
    }

    private static Rank findRankBySameNumberCount(int sameNumberCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.sameNumberCount == sameNumberCount)
                .findFirst()
                .orElse(NO_MATCH);
    }

    public static Rank of(int sameNumberCount) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isSameNumberCount(sameNumberCount))
                .findAny()
                .orElse(NO_MATCH);
    }

    public boolean isSameNumberCount(int count) {
        return sameNumberCount == count;
    }

    public long getPrize() {
        return prize;
    }

    public int getSameNumberCount() {
        return sameNumberCount;
    }
}
