package domain;

import domain.money.Money;

import java.util.Arrays;

public enum Rank {
    FIRST(6, Money.amountOf(2_000_000_000)),
    SECOND(5, Money.amountOf(30_000_000)),
    THIRD(5, Money.amountOf(1_500_000)),
    FOURTH(4, Money.amountOf(50_000)),
    FIFTH(3, Money.amountOf(5_000)),
    MISS(0, Money.amountOf(0));

    private int numberOfMatching;
    private Money winningMoney;

    Rank(int numberOfMatching, Money winningMoney) {
        this.numberOfMatching = numberOfMatching;
        this.winningMoney = winningMoney;
    }

    public static Rank of(int numberOfMatching, boolean BonusNumberMatch) {
        if (numberOfMatching == SECOND.numberOfMatching && BonusNumberMatch) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank != SECOND)
                .filter(rank -> rank.hasSame(numberOfMatching))
                .findFirst()
                .orElse(MISS);
    }

    private boolean hasSame(int numberOfMatching) {
        return this.numberOfMatching == numberOfMatching;
    }

    public Money getWinningMoney() {
        return winningMoney;
    }

    public int getNumberOfMatching() {
        return numberOfMatching;
    }
}
