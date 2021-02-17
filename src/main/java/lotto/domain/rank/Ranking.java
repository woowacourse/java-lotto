package lotto.domain.rank;

public class Ranking {

    int rank;
    int winnings;
    long count;

    public Ranking(int rank, int winnings, long count) {
        this.rank = rank;
        this.winnings = winnings;
        this.count = count;
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
}
