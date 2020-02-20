package lotto.domain;

public enum Rank {
    FIRST(6),
    SECOND(5),
    THIRD(5),
    FOURTH(4),
    FIFTH(3);

    private int matchNumber;

    Rank(int matchNumbers) {
        matchNumber = matchNumbers;
    }

    int getMatchNumber() {
        return matchNumber;
    }

    public static Rank valueOf(int matchNumber, boolean matchBonusBall) {
        for (Rank rank : Rank.values()) {
            if (matchNumber == SECOND.matchNumber && matchBonusBall) {
                return SECOND;
            }

            if (rank.equals(Rank.SECOND) && !matchBonusBall) {
                continue;
            }

            if (rank.matchNumber == matchNumber) {
                return rank;
            }
        }

        throw new IllegalArgumentException("당첨된 갯수에 해당하는 순위가 없습니다.");
    }
}
