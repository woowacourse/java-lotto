package domain;

public enum Rank {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    OTHER(0,false, 0);

    private final int count;
    private final boolean bonus;
    private final int amount;

    Rank(int count, boolean bonus, int amount) {
        this.count = count;
        this.bonus = bonus;
        this.amount = amount;
    }

    public static Rank value(int count, boolean bonus) {
        for (Rank rank : Rank.values()) {
            if (rank.count == count && rank.bonus == bonus) {
                return rank;
            }
        }
        return OTHER;
    }

    public int getAmount() {
        return amount;
    }
}
