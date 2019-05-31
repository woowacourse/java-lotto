package lotto.domain;

import java.util.List;

public enum Rank {
    FAIL(0, 0),

    FOURTH(3, 5000),

    THIRD(4, 50000),

    SECOND(5, 1500000),

    FIRST(6, 2000000000);

    private static final String RESULT_FORMAT = "%d개 일치 (%d원)- %d개";

    private final int count;
    private final int prize;

    Rank(int count, int prize) {
        this.count = count;
        this.prize = prize;
    }

    public static Rank valueOf(int count) {
        Rank selectedRank = null;

        for (Rank rank : Rank.values()) {
            selectedRank = getRank(count, selectedRank, rank);
        }

        return selectedRank;
    }

    private static Rank getRank(int count, Rank selectedRank, Rank rank) {
        if (rank.count == count) {
            selectedRank = rank;
        }
        return selectedRank;
    }

    public String getMatchString(List<Rank> ranks) {
        int matchCount = 0;

        for (Rank rank : ranks) {
            matchCount = plusCount(matchCount, rank);
        }

        return String.format(RESULT_FORMAT, this.count, this.prize, matchCount);
    }

    private int plusCount(int matchCount, Rank rank) {
        if (this.equals(rank)) {
            matchCount++;
        }
        return matchCount;
    }

    public int getPrize(List<Rank> ranks) {
        int sum = 0;

        for (Rank rank : ranks) {
            sum = getSum(sum, rank);
        }

        return sum;
    }

    private int getSum(int sum, Rank rank) {
        if (this.equals(rank)) {
            sum += rank.prize;
        }
        return sum;
    }
}