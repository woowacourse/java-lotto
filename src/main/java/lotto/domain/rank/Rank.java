package lotto.domain.rank;

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

    public boolean getBonus() {
        return bonus;
    }
}
