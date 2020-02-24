package lotto.domain.result;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import lotto.domain.PurchaseMoney;

public class GameResult {
	public static final int PERCENTAGE = 100;

	private Map<Statistic, RankCount> result = new LinkedHashMap<>();

	public GameResult() {
		result.put(Statistic.NOTHING, new RankCount(Statistic.NOTHING));
		result.put(Statistic.THREE, new RankCount(Statistic.THREE));
		result.put(Statistic.FOUR, new RankCount(Statistic.FOUR));
		result.put(Statistic.FIVE, new RankCount(Statistic.FIVE));
		result.put(Statistic.BONUS, new RankCount(Statistic.BONUS));
		result.put(Statistic.SIX, new RankCount(Statistic.SIX));
	}

	public RankCount of(Statistic statistic) {
		return result.get(statistic);
	}

	public double getEarningMoney(PurchaseMoney money) {
		double profit = 0;
		for (RankCount rankCount : result.values()) {
			profit += rankCount.getProfit();
		}
		return (profit / money.getPurchaseMoney()) * PERCENTAGE;
	}

	public Collection<RankCount> getResult() {
		return result.values();
	}
}
