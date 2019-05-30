package lotto.domain.rank;

public enum RankType {
    FIRST(6, false), SECOND(5, true), THIRD(5, false),
    FOURTH(4, false), FIFTH(3, false);

    private final int matchNumber;
    private final boolean bonus;

    RankType(int matchNumber, boolean bonus) {
        this.matchNumber = matchNumber;
        this.bonus = bonus;
    }

    public static RankType valueOf(int matchNumber) {
        for (RankType rank : values()) {
            if (rank.matchNumber == matchNumber) {
                return rank;
            }
        }
        throw new IllegalArgumentException("당첨 조건을 찾을 수 없습니다.");
    }
}
