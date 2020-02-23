package domain;

public class ProfitCalculator {
	private static final int HUNDRED = 100;

	public static Money getProfit(Money purchaseMoney, GameResult ranks) {
		return new Money(ranks.getResultMoney().getMoney() / purchaseMoney.getMoney() * HUNDRED);
	}
}
