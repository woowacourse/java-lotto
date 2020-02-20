package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000);

    private int matchNumber;
    private int winningMoney;

    Rank(int matchNumber, int winningMoney) {
        this.matchNumber = matchNumber;
        this.winningMoney = winningMoney;
    }

    public static boolean isExists(int matchingNumber) {
        return Arrays.stream(Rank.values()).anyMatch(rank -> rank.getMatchNumber() == matchingNumber);
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public int calculateWinningMoney() {
        return winningMoney;
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
