package domain;

import java.util.List;

public class Profit extends Money {
	private static final int HUNDRED = 100;

	public Profit(Money purchaseMoney, List<Rank> ranks) {
		super((int)((double)Rank.sumWinningMoney(ranks).getMoney() / purchaseMoney.getMoney() * HUNDRED));
	}
}
