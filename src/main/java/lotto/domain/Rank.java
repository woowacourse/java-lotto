package lotto.domain;

public enum Rank {

    FIRST_PRIDE(2000000000),
    SECOND_PRIDE(30000000),
    THIRD_PRIDE(1500000),
    FOURTH_PRIDE(50000),
    FIFTH_PRIDE(5000),
    BOOM(0);

    public final int prize;

    Rank(int prize) {
        this.prize = prize;
    }

    public Rank checkPrize(int matchCount, boolean matchBonus) {
        if (matchCount == 6) {
            return FIRST_PRIDE;
        }
        if (matchCount == 5 && matchBonus) {
            return SECOND_PRIDE;
        }
        if (matchCount == 5) {
            return THIRD_PRIDE;
        }
        if (matchCount == 4) {
            return FOURTH_PRIDE;
        }
        if (matchCount == 3) {
            return FIFTH_PRIDE;
        }
        return BOOM;
    }
}
