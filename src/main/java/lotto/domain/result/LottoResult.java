package lotto.domain.result;

import lotto.domain.money.Money;

import java.util.*;
import java.util.stream.Collectors;

public class LottoResult {
	private static final int INITIAL_PROFIT = 0;

	private final Map<Rank, Integer> rankCount;

	LottoResult(final Map<Rank, Integer> rankCount) {
		this.rankCount = Collections.unmodifiableMap(rankCount);
	}

	public static LottoResult of(Winning winning, LottoTickets lottoTickets) {
		List<Rank> result = lottoTickets.findResult(winning);

		Map<Rank, Integer> rankCount = Arrays.stream(Rank.values())
				.collect(Collectors.toMap(rank -> rank, rank -> Collections.frequency(result, rank)));

		return new LottoResult(rankCount);
	}

	public double calculateProfitRate(Money money) {
		double totalProfit = INITIAL_PROFIT;

		for (Rank rank : rankCount.keySet()) {
			totalProfit += rank.calculateReward(rankCount.get(rank));
		}

		return money.calculateProfitRate(totalProfit);
	}

	public Map<Rank, Integer> getWinningRankCount() {
		return rankCount.entrySet()
				.stream()
				.filter(entry -> entry.getKey().isWinning())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LottoResult lottoResult = (LottoResult) o;
		return Objects.equals(rankCount, lottoResult.rankCount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rankCount);
	}
}
