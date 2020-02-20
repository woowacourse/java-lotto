package lotto.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class ResultStatistic {
	private static final int ONE = 1;
	private static final int DEFAULT_COUNT = 0;

	private Map<Rank, Integer> results;

	private ResultStatistic(final Map<Rank, Integer> input) {
		results = input;
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

	public Map<Rank, Integer> getResults() {
		return this.results;
	}
}
