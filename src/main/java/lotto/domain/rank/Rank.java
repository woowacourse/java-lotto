package lotto.domain.rank;

import java.util.Objects;

public class Rank {
    private final int rank;
    private final int winnings;
    private final long count;
    private final int matchingCount;
    private final boolean bonus;

    public Rank(int rank, int winnings, long count, int matchingCount, boolean bonus) {
        this.rank = rank;
        this.winnings = winnings;
        this.count = count;
        this.matchingCount = matchingCount;
        this.bonus = bonus;
    }

    public int getRank() {
        return rank;
    }

    public int getWinnings() {
        return winnings;
    }

    public long getCount() {
        return count;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public boolean hasBonus() {
        return bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank1 = (Rank) o;
        return rank == rank1.rank &&
                winnings == rank1.winnings &&
                count == rank1.count &&
                matchingCount == rank1.matchingCount &&
                bonus == rank1.bonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, winnings, count, matchingCount, bonus);
    }
}
