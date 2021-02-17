package lotto.domain.rank;

public class Ranking {

    int rank;
    int winnings;
    long count;
    int matchingCount;
    boolean bonus;

    public Ranking(int rank, int winnings, long count, int matchingCount, boolean bonus) {
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
