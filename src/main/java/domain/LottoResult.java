package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
	private final Map<Rank, Long> ranks;

	public LottoResult(final Map<Rank, Long> ranks) {
		this.ranks = Objects.requireNonNull(ranks);
	}

	public static EnumMap<Rank, Long> getRankMap() {
		EnumMap<Rank, Long> ranks = new EnumMap<Rank, Long>(Rank.class);

		Arrays.stream(Rank.values())
			.filter(rank -> !(rank.isNothing()))
			.forEach(rank -> ranks.put(rank, 0L));
		return ranks;
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
