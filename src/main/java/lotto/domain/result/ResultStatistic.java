package lotto.domain.result;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lotto.domain.money.MoneyForLotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;

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
	private static final int RATE_UNIT = 100;

	private Map<Rank, Long> results;

	private ResultStatistic(final Map<Rank, Long> input) {
		this.results = input;
	}

	public static ResultStatistic calculate(final Lottos lottos, final WinningLotto winningLotto) {
		return lottos.getLottos().stream()
				.map(winningLotto::getRank)
				.collect(Collectors.collectingAndThen(
						Collectors.groupingBy(Function.identity(), Collectors.counting()), ResultStatistic::new)
				);
	}

	public long calculateRevenueRate(MoneyForLotto moneyForLotto) {
		return (RATE_UNIT * getTotalRevenue() / moneyForLotto.getMoneyForLotto());
	}

	private long getTotalRevenue() {
		long totalRevenue = 0;

		for (Rank rank : Rank.values()) {
			totalRevenue += results.getOrDefault(rank, DEFAULT_COUNT) * rank.getReward();
		}

		return totalRevenue;
	}

	public Map<Rank, Long> getResult() {
		return this.results;
	}

	public long getResultCount(Rank rank) {
		return this.results.getOrDefault(rank, DEFAULT_COUNT);
	}
}
