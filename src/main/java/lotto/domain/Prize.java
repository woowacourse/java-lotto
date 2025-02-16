package lotto.domain;

public class Prize {

    private final int matchCount;
    private final boolean matchBonus;

    public Prize(final int matchCount, final boolean matchBonus) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
    }

    public Rank getMatchRank() {
        return Rank.checkRank(matchCount, matchBonus);
    }
}
