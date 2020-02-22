package lotto.domain.result;

import lotto.exception.LottoMismatchException;

import java.util.Arrays;

public enum Statistic {
    THREE(3, 5_000, false),
    FOUR(4, 50_000, false),
    FIVE(5, 1_500_000, false),
    BONUS(5, 30_000_000, true),
    SIX(6, 2_000_000_000, false);

    private final int matchingNumbers;
    private final int prize;
    private final boolean bonusMatching;
    private int count = 0;

    Statistic(int matchingNumbers, int prize, boolean bonusMatching) {
        this.matchingNumbers = matchingNumbers;
        this.prize = prize;
        this.bonusMatching = bonusMatching;
    }

    public static Statistic getRank(int numberOfMatch, boolean isBonus) {

        return Arrays.stream(values())
                .filter(statistic -> statistic.matchingNumbers == numberOfMatch)
                .findFirst()
                .orElseThrow(LottoMismatchException::new);
        // TODO: 2020/02/21 isBonus 관리방법, count 객체로 빼기, Exception 관리
    }

    public void count() {
        this.count++;
    }

    public double getProfit() {
        return this.prize * this.count;
    }

    public int getMatchingNumbers() {
        return matchingNumbers;
    }

    public int getPrize() {
        return prize;
    }

    public int getCount() {
        return count;
    }

    public boolean getBonusMatching(){
    	return bonusMatching;
	}
}
