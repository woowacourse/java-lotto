package domain;

/**
 * Rank.java
 * 이윤을 구하고 담는 Wrapper 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon스
 * @author Daeun Lee, github.com/da-nyee
 */
public class Profit {
    private final double profit;

    public Profit(Money budget, Money reward) {
        this.profit = reward.toLong() / (double) budget.toLong();
    }

    public double toDouble() {
        return this.profit;
    }
}
