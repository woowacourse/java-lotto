package lotto.domain.result;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Profit;
import lotto.domain.PurchaseMoney;
import lotto.domain.WinningLotto;

public class GameResult {
	private Map<Statistic, RankCount> result = new LinkedHashMap<>();

	public GameResult(WinningLotto winningLotto, Lottos lottos) {
		for (Statistic statistic : Statistic.values()) {
			result.put(statistic, new RankCount(statistic));
		}
		for (Lotto lotto : lottos) {
			Statistic statistic = winningLotto.countMatch(lotto);
			result.get(statistic).plusCount();
		}
	}

	public double getEarningMoney(PurchaseMoney money) {
		Profit profit = new Profit();
		for (RankCount rankCount : result.values()) {
			profit.add(rankCount.getProfit());
		}
		return profit.of(money);
	}

	public Collection getResultCount() {
		return Collections.unmodifiableCollection(result.values());
	}
}
