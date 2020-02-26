package lotto.domain.result;

import lotto.domain.lottonumber.BonusLottoNumber;
import lotto.domain.money.MoneyForLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.PaidLotto;
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
	private static final int ONE = 1;
	private static final int DEFAULT_COUNT = 0;

	private Map<Rank, Integer> results;

	private ResultStatistic(final Map<Rank, Integer> input) {
		this.results = input;
	}

	public static ResultStatistic calculate(
			final Lottos lottos,
			final WinningLotto winningLotto,
			final BonusLottoNumber bonus
	) {
		Map<Rank, Integer> results = createInitialResult();

		for (Lotto lotto : lottos.getLottos()) {
			PaidLotto paidLotto = (PaidLotto) lotto;
			Rank rank = paidLotto.getRank(winningLotto, bonus);
			int count = results.get(rank) + ONE;
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

	public long calculateRevenueRate(MoneyForLotto moneyForLotto) {
		return (100 * getTotalRevenue() / moneyForLotto.getMoneyForLotto());
	}

	private long getTotalRevenue() {
		long totalRevenue = 0;

		for (Rank rank : Rank.values()) {
			totalRevenue += results.get(rank) * rank.getReward();
		}

		return totalRevenue;
	}

	public Map<Rank, Integer> getResults() {
		return this.results;
	}
}
