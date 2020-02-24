package lotto.domain.result;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.IllegalRankException;
import lotto.domain.PurchaseMoney;

public class GameResult {
	public static final int PERCENTAGE = 100;

	private List<RankCount> result = new ArrayList<>();

	public GameResult() {
		result.add(new RankCount(Statistic.NOTHING));
		result.add(new RankCount(Statistic.THREE));
		result.add(new RankCount(Statistic.FOUR));
		result.add(new RankCount(Statistic.FIVE));
		result.add(new RankCount(Statistic.BONUS));
		result.add(new RankCount(Statistic.SIX));
	}

	public RankCount of(Statistic statistic) {
		return result.stream()
			.filter(s -> s.getStatistic() == statistic)
			.findFirst()
			.orElseThrow(() -> new IllegalRankException("프로그램 오류가 발생하였습니다."));
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
