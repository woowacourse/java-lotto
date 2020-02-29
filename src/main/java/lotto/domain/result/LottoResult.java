package lotto.domain.result;

import lotto.domain.money.Money;

import java.util.*;
import java.util.stream.Collectors;

public class LottoResult {
	private static final int INITIAL_PROFIT = 0;

	private final Map<Rank, Integer> rankToCount;

	LottoResult(final Map<Rank, Integer> rankToCount) {
		this.rankToCount = Collections.unmodifiableMap(rankToCount);
	}

	public static LottoResult of(Winning winning, LottoTickets lottoTickets) {
		List<Rank> result = lottoTickets.findResult(winning);

		Map<Rank, Integer> rankToCount = Arrays.stream(Rank.values())
				.collect(Collectors.toMap(rank -> rank, rank -> Collections.frequency(result, rank)));

		return new LottoResult(rankToCount);
	}

	public double calculateProfitRate(Money money) {
		double totalProfit = INITIAL_PROFIT;

		for (Rank rank : rankToCount.keySet()) {
			totalProfit += rank.calculateReward(rankToCount.get(rank));
		}

		return money.calculateProfitRate(totalProfit);
	}

	public Map<Rank, Integer> getWinningRankCount() {
		return rankToCount.entrySet()
				.stream()
				.filter(entry -> entry.getKey().isWinning())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LottoResult lottoResult = (LottoResult) o;
		return Objects.equals(rankToCount, lottoResult.rankToCount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rankToCount);
	}
}
