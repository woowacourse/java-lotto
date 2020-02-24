package domain;

public class ProfitCalculator {
	private static final int HUNDRED = 100;

	public static double getProfit(Money purchaseMoney, GameResult ranks) {
		return ranks.getResultMoney().getMoney() / purchaseMoney.getMoney() * HUNDRED;
	}
}
