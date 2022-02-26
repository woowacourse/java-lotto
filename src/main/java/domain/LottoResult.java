package domain;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
	private final Map<Rank, Long> ranks;

	public LottoResult(final Map<Rank, Long> ranks) {
		this.ranks = Objects.requireNonNull(ranks);
	}

	public double calculateProfitRate(Payment payment) {
		return payment.calculateDivision(calculateTotalProfit());
	}

	private int calculateTotalProfit() {
		return ranks.entrySet()
			.stream()
			.mapToInt(entry -> calculateRankProfit(entry.getKey(), entry.getValue().intValue()))
			.sum();
	}

	private int calculateRankProfit(Rank rank, int count) {
		return rank.calculateMoney(count);
	}

	public Map<Rank, Long> getRanks() {
		return Collections.unmodifiableMap(ranks);
	}
}
