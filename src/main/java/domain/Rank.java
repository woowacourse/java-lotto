package domain;

public enum Rank {
    NONE(0, false, 0, ""),
    FIFTH(3, false, 5000, "3개 일치 (5,000원)"),
    FOURTH(4, false, 50000, "4개 일치 (50,000원)"),
    THIRD(5, false, 1500000, "5개 일치 (1,500,000원)"),
    SECOND(5, true, 30000000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    FIRST(6, false, 2000000000, "6개 일치 (2,000,000,000원)");

    private final int matchCount;
    private final boolean matchBonus;
    private final int prize;
    private final String description;

    Rank(int matchCount, boolean matchBonus, int prize, String description) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
        this.description = description;
    }

    public static Rank findRank(int matchCount, boolean isMatchBonusNumber) {
        if (matchCount == 3) {
            return FIFTH;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 5) {
            if (isMatchBonusNumber) {
                return SECOND;
            }
            return THIRD;
        }
        if (matchCount == 6) {
            return FIRST;
        }
        return NONE;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }

    public int getPrize() {
        return prize;
    }

    public String getDescription() {
        return description;
    }
}
