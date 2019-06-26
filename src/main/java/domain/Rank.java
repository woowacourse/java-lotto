package domain;

import domain.money.Money;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Rank {
    FIRST(6, Money.amountOf(2_000_000_000), (umberOfMatching, bonus) -> umberOfMatching.equals(6)),
    SECOND(5, Money.amountOf(30_000_000), (umberOfMatching, bonus) -> umberOfMatching.equals(5)
                                                                                            && bonus.equals(true)),
    THIRD(5, Money.amountOf(1_500_000), (umberOfMatching, bonus) -> umberOfMatching.equals(5)),
    FOURTH(4, Money.amountOf(50_000), (umberOfMatching, bonus) -> umberOfMatching.equals(4)),
    FIFTH(3, Money.amountOf(5_000), (umberOfMatching, bonus) -> umberOfMatching.equals(3)),
    MISS(0, Money.amountOf(0), (umberOfMatching, bonus) -> umberOfMatching.equals(0));

    public final int numberOfMatching;
    private Money winningMoney;
    private BiFunction<Integer, Boolean, Boolean> matchingStrategy;

    Rank(int numberOfMatching, Money winningMoney, BiFunction<Integer, Boolean, Boolean> matchingStrategy) {
        this.numberOfMatching = numberOfMatching;
        this.winningMoney = winningMoney;
        this.matchingStrategy = matchingStrategy;
    }

    public static Rank of(int numberOfMatching, Boolean bonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchingStrategy.apply(numberOfMatching, bonus))
                .findFirst()
                .orElse(MISS);
    }

    public Money getWinningMoney() {
        return winningMoney;
    }

    public int getNumberOfMatching() {
        return numberOfMatching;
    }
}
