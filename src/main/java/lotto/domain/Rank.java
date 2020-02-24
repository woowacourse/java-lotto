package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, new Money(2_000_000_000)),
    SECOND(5, new Money(30_000_000)),
    THIRD(5, new Money(1_500_000)),
    FOURTH(4, new Money(50_000)),
    FIFTH(3, new Money(5_000));

    private int matchNumber;
    private Money winningMoney;

    Rank(int matchNumber, Money winningMoney) {
        this.matchNumber = matchNumber;
        this.winningMoney = winningMoney;
    }

    static boolean isValid(int matchingNumber) {
        return Arrays.stream(Rank.values()).anyMatch(rank -> rank.isSameMatchNumber(matchingNumber));
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

        return Arrays.stream(Rank.values())
            .filter(rank -> !SECOND.equals(rank))
            .filter(rank -> rank.isSameMatchNumber(matchNumber))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("당첨된 갯수에 해당하는 순위가 없습니다."));
    }

    private boolean isSameMatchNumber(int matchNumber) {
        return this.matchNumber == matchNumber;
    }
}
