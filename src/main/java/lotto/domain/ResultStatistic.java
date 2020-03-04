package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import lotto.util.Money;

/**
 * ResultStatistic 클래스
 *
 * @author 히히, 토니
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class ResultStatistic {
	private static final int DEFAULT_COUNT = 0;

	private Map<Rank, Integer> results;

	private ResultStatistic(final Map<Rank, Integer> input) {
		results = input;
	}

	public static ResultStatistic calculate(
			final Lottos lottos,
			final WinningInformation winningInformation
	) {
		Map<Rank, Integer> results = createInitialResult();

		for (Lotto lotto : lottos.getLottos()) {
			Rank rank = winningInformation.calculateRank(lotto);
			int count = results.get(rank) + 1;
			results.put(rank, count);
		}

		return new ResultStatistic(results);
	}

	private static Map<Rank, Integer> createInitialResult() {
		Map<Rank, Integer> initialResult = new HashMap<>();

		for (Rank rank : Rank.values()) {
			initialResult.put(rank, DEFAULT_COUNT);
		}

		return initialResult;
	}

	public Map<Rank, Integer> getResults() {
		return this.results;
	}

	public long calculateRevenueRate(Money money) {
		return (100 * getTotalRevenue() / money.getMoney());
	}

	private long getTotalRevenue() {
		int totalRevenue = 0;

		for (Rank rank : Rank.values()) {
			totalRevenue += results.get(rank) * rank.getReward();
		}
		return totalRevenue;
	}
}
