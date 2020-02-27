package lotto.domain.result;

import lotto.domain.money.MoneyForLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;

import java.util.HashMap;
import java.util.Map;

/**
 * 로또 결과를 Map 형태로 포함하는 객체
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class ResultStatistic {
	private static final Long DEFAULT_COUNT = 0L;
	private static final Long COUNT_UP_UNIT = 1L;
	private static final int RATE_UNIT = 100;

	private Map<Rank, Long> results;

	private ResultStatistic(final Map<Rank, Long> input) {
		this.results = input;
	}

	public static ResultStatistic calculate(final Lottos lottos, final WinningLotto winningLotto) {
		Map<Rank, Long> results = createInitialResult();

		for (Lotto lotto : lottos.getLottos()) {
			Rank rank = winningLotto.getRank(lotto);
			results.merge(rank, results.get(rank), (keyRank, count) -> count + COUNT_UP_UNIT);
		}

		return new ResultStatistic(results);
	}

	private static Map<Rank, Long> createInitialResult() {
		Map<Rank, Long> initialResult = new HashMap<>();

		for (Rank rank : Rank.values()) {
			initialResult.put(rank, DEFAULT_COUNT);
		}

		return initialResult;
	}

	public long calculateRevenueRate(MoneyForLotto moneyForLotto) {
		return (RATE_UNIT * getTotalRevenue() / moneyForLotto.getMoneyForLotto());
	}

	private long getTotalRevenue() {
		long totalRevenue = 0;

		for (Rank rank : Rank.values()) {
			totalRevenue += results.get(rank) * rank.getReward();
		}

		return totalRevenue;
	}

	public Map<Rank, Long> getResults() {
		return this.results;
	}
}
