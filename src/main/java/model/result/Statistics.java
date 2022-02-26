package model.result;

public enum Statistics {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    BONUS(5, 30000000),
    SIX(6, 2000000000);

    private final int matchNumber;
    private final int value;
    private int count;

    Statistics(int matchNumber, int value) {
        this.matchNumber = matchNumber;
        this.value = value;
        this.count = 0;
   }

    public void addCount() {
        this.count++;
    }

    public int getCount() {
        return count;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public int getValue() {
        return value;
    }
}
