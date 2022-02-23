package domain;

public enum Rank {
    FIRST(6, 0),
    SECOND(5, 1),
    THIRD(5, 0),
    FOURTH(4, 0),
    FIFTH(3, 0),
    OTHER(0,0);

    private int count;
    private int bonus;

    Rank(int count, int bonus) {
        this.count = count;
        this.bonus = bonus;
    }

    public static Rank value(int count, int bonus) {
        for (Rank rank : Rank.values()) {
            if (rank.count == count && rank.bonus == bonus) {
                return rank;
            }
        }
        return OTHER;
    }
}
