package lottogame.domain;

import java.util.Arrays;

public enum Rank {
    MISS(0, 0),
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    FIRST(6, 2000000000);

    private static final int MIN_MATCH_NUMBER = 0;
    private static final int MAX_MATCH_NUMBER = 6;

    private int numberOfMatch;
    private int prize;

    Rank(int numberOfMatch, int prize) {
        this.numberOfMatch = numberOfMatch;
        this.prize = prize;
    }

    public static Rank valueOf(int numberOfMatch) {
        if (numberOfMatch < MIN_MATCH_NUMBER || numberOfMatch > MAX_MATCH_NUMBER) {
            throw new IllegalArgumentException("올바른 매칭 개수가 아닙니다.");
        }

        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isMatch(numberOfMatch))
                .findAny()
                .orElse(MISS);
    }

    private boolean isMatch(int numberOfMatch) {
        return this.numberOfMatch == numberOfMatch;
    }

    @Override
    public String toString() {
        return numberOfMatch + "개 일치 (" + prize + "원) - ";
    }

    public long sumPrizeOf(Integer numberOfMatch) {
        return prize * numberOfMatch;
    }
}
