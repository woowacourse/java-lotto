package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, BonusNumber.DEFAULT, new Money(2_000_000_000)),
    SECOND(5, BonusNumber.TRUE, new Money(30_000_000)),
    THIRD(5, BonusNumber.FALSE, new Money(1_500_000)),
    FOURTH(4, BonusNumber.DEFAULT, new Money(50_000)),
    FIFTH(3, BonusNumber.DEFAULT, new Money(5_000)),
    NO_MATCH(0, BonusNumber.DEFAULT, new Money(0));

    private final int matchNumber;
    private final BonusNumber bonusNumber;
    private final Money winningMoney;

    Rank(int matchNumber, BonusNumber bonusNumber, Money winningMoney) {
        this.matchNumber = matchNumber;
        this.bonusNumber = bonusNumber;
        this.winningMoney = winningMoney;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public Money calculateWinningMoney() {
        return winningMoney;
    }

    public static Rank valueOf(int matchNumber, boolean matchBonusNumber) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.isSameMatchNumber(matchNumber))
            .filter(rank -> rank.bonusNumber.match(matchBonusNumber))
            .findFirst()
            .orElse(NO_MATCH);
    }

    private boolean isSameMatchNumber(int matchNumber) {
        return this.matchNumber == matchNumber;
    }
}
