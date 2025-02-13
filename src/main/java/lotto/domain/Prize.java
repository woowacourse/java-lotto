package lotto.domain;

public class Prize {

    private int matchCount;
    private boolean matchBonus;

    public Prize(int matchCount, boolean matchBonus) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
    }

    public Rank matchRank() {
        return Rank.matchRank(matchCount, matchBonus);
    }
}
