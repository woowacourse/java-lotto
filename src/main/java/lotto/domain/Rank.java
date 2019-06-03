package lotto.domain;

public enum Rank {
    FIRST(6),
    THIRD(5),
    FOURTH(4),
    FIFTH(3),
    MISS(0);
    private int numOfMatching;

    Rank(int numOfMatching) {
        this.numOfMatching = numOfMatching;
    }

    public static Rank valueOf(int numOfMatching) {
        for (Rank value : values()) {
            if (value.numOfMatching == numOfMatching) {
                return value;
            }
        }
        return MISS;
    }
}
