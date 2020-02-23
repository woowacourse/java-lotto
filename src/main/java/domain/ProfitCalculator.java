package domain;

import java.util.List;

public class ProfitCalculator {
	private static final int HUNDRED = 100;

	public static Money getProfit(Money purchaseMoney, List<Rank> ranks) {
		return new Money(Rank.sumWinningMoney(ranks).getMoney() / purchaseMoney.getMoney() * HUNDRED);
	}
}
