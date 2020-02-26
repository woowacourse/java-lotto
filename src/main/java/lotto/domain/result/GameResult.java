package lotto.domain.result;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.PurchaseMoney;
import lotto.domain.WinningLotto;

public class GameResult {
	public static final int PERCENTAGE = 100;

	private Map<Statistic, RankCount> result = new LinkedHashMap<>();

	public GameResult(WinningLotto winningLotto, Lottos lottos) {
		for(Statistic statistic : Statistic.values()) {
			result.put(statistic, new RankCount(statistic));
		}
		for (Lotto lotto : lottos) {
			Statistic statistic = winningLotto.countMatch(lotto);
			result.get(statistic).plusCount();
		}
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
