package lotto.model.lotto.result;

public enum Rank {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    BONUS(5, 30000000),
    SIX(6, 2000000000);

    private final int matchNumber;
    private final long value;

    Rank(int matchNumber, long value) {
        this.matchNumber = matchNumber;
        this.value = value;
   }

   public boolean isMatchNumber(long number) {
        return matchNumber == number;
   }

    public int getMatchNumber() {
        return matchNumber;
    }

    public long getValue() {
        return value;
    }
}
