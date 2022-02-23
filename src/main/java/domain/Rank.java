package domain;

public enum Rank {
    FIRST(6, false),
    SECOND(5, true),
    THIRD(5, false),
    FOURTH(4, false),
    FIFTH(3, false),
    OTHER(0,false);

    private final int count;
    private final boolean bonus;

    Rank(int count, boolean bonus) {
        this.count = count;
        this.bonus = bonus;
    }

    public static Rank value(int count, boolean bonus) {
        for (Rank rank : Rank.values()) {
            if (rank.count == count && rank.bonus == bonus) {
                return rank;
            }
        }
        return OTHER;
    }
}
