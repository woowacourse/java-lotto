package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum LottoRank {
    FIRST_PLACE(6, 2000000000),
    SECOND_PLACE(5, 30000000, true),
    THIRD_PLACE(5, 1500000),
    FOURTH_PLACE(4, 50000),
    FIFTH_PLACE(3, 5000),
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
                .filter(rank -> rank.getMatches() == matchCount)
                .filter(rank -> !rank.equals(SECOND_PLACE) || bonusMatch)
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