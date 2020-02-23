package lotto.domain.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lotto.domain.PurchaseMoney;

public class GameResult {
	public static final int PERCENTAGE = 100;
	private List<RankCount> result = new ArrayList<>();

	public GameResult() {
		result.add(new RankCount(Statistic.THREE));
		result.add(new RankCount(Statistic.FOUR));
		result.add(new RankCount(Statistic.FIVE));
		result.add(new RankCount(Statistic.BONUS));
		result.add(new RankCount(Statistic.SIX));
	}

	public Optional<RankCount> of(Optional<Statistic> statistic) {
		Optional<RankCount> rank = result.stream()
			.filter(s -> s.getStatistic() == statistic.get())
			.findFirst();
		return rank;
	}

	public double getEarningMoney(PurchaseMoney money) {
		double profit = 0;
		for (RankCount rankCount : result) {
			profit += rankCount.getProfit();
		}
		return (profit / money.getPurchaseMoney()) * PERCENTAGE;
	}

	public List<RankCount> getResult() {
		return result;
	}
}
