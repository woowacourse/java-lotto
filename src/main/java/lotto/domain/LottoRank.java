package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum LottoRank {
    FIRST_PLACE(6, 2_000_000_000),
    SECOND_PLACE(5, 30_000_000, true),
    THIRD_PLACE(5, 1_500_000),
    FOURTH_PLACE(4, 50_000),
    FIFTH_PLACE(3, 5_000),
    SIXTH_PLACE(0, 0);

    private int matches;
    private int reward;
    private boolean bonusMatch;

    LottoRank(int matches, int reward) {
        this.matches = matches;
        this.reward = reward;
        this.bonusMatch = false;
    }

    LottoRank(int matches, int reward, boolean bonusMatch) {
        this.matches = matches;
        this.reward = reward;
        this.bonusMatch = bonusMatch;
    }

    public static LottoRank matchLottoRank(int matchCount, boolean bonusMatch) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.matches == matchCount)
                .filter(rank -> rank.bonusMatch == bonusMatch)
                .findFirst()
                .orElse(SIXTH_PLACE);
    }

    public static Comparator<LottoRank> matchCountComparator = Comparator.comparingInt(LottoRank::getReward);

    public int getMatches() {
        return matches;
    }

    public int getReward() {
        return reward;
    }
}