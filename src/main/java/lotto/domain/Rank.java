package lotto.domain;

import java.util.Arrays;

import lotto.exception.InvalidRankException;

public enum Rank {
    FIRST(6, new Money(2_000_000_000)),
    SECOND(5, new Money(30_000_000)),
    THIRD(5, new Money(1_500_000)),
    FOURTH(4, new Money(50_000)),
    FIFTH(3, new Money(5_000)),
    NO_MATCH(0, new Money(0));

    private final int matchNumber;
    private final Money winningMoney;

    Rank(int matchNumber, Money winningMoney) {
        this.matchNumber = matchNumber;
        this.winningMoney = winningMoney;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public Money calculateWinningMoney() {
        return winningMoney;
    }

    public static Rank valueOf(int matchNumber, boolean matchBonusBall) {
        if (SECOND.isSameMatchNumber(matchNumber) && matchBonusBall) {
            return SECOND;
        }

        if (matchNumber < FIFTH.matchNumber) {
            return NO_MATCH;
        }

        return Arrays.stream(Rank.values())
            .filter(rank -> !SECOND.equals(rank))
            .filter(rank -> rank.isSameMatchNumber(matchNumber))
            .findAny()
            .orElseThrow(() -> new InvalidRankException("당첨된 갯수에 해당하는 순위가 없습니다."));
    }

    private boolean isSameMatchNumber(int matchNumber) {
        return this.matchNumber == matchNumber;
    }
}
