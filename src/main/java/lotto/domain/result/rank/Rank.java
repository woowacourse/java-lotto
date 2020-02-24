package lotto.domain.result.rank;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    SIXTH(0, 0);

    private final int numberOfMatch;       // 우승 로또 번호와 일치하는 번호 갯수
    private final int prize;     // 등수 별 상금

    Rank(int numberOfMatch, int prize) {
        this.numberOfMatch = numberOfMatch;
        this.prize = prize;
    }

    public static Rank findRankByMatchInfo(int numberOfMatch, boolean bonusMatch) {
        Rank rank = Arrays.stream(Rank.values())
                .filter(aRank -> aRank.numberOfMatch == numberOfMatch)
                .findFirst()
                .orElse(SIXTH);

        if (rank.isThird(bonusMatch)) {
            return THIRD;
        }
        return rank;
    }

    private boolean isThird(boolean bonusMatch) {
        return (this == SECOND || this == THIRD) && !bonusMatch;
    }

    public int getPrize() {
        return prize;
    }

    public int getNumberOfMatch() {
        return numberOfMatch;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "numberOfMatch=" + numberOfMatch +
                ", prize=" + prize +
                '}';
    }
}
