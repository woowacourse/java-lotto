package dto;

import domain.Prize;

public class PrizeDto {
    private final int matching;
    private final boolean bonusMatching;
    private final long money;
    private final int winningNumber;

    private PrizeDto(final int matching, final boolean bonusMatching, final long money, final int winningNumber) {
        this.matching = matching;
        this.bonusMatching = bonusMatching;
        this.money = money;
        this.winningNumber = winningNumber;
    }

    public static PrizeDto of(final Prize prize, final int winningNumber) {
        return new PrizeDto(prize.getMatching(), prize.isBonusMatching(), prize.getMoney(), winningNumber);
    }

    public int getMatching() {
        return matching;
    }

    public boolean isBonusMatching() {
        return bonusMatching;
    }

    public long getMoney() {
        return money;
    }

    public int getWinningNumber() {
        return winningNumber;
    }
}
