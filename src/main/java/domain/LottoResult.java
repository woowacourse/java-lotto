package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
	private final Map<Rank, Long> ranks;

	public LottoResult(final Map<Rank, Long> ranks) {
		Objects.requireNonNull(ranks, "[ERROR] ranks가 null 입니다.");
		this.ranks = new EnumMap<Rank, Long>(ranks);
	}

	public static LottoResult from(Map<Rank, Long> result) {
		Map<Rank, Long> ranks = LottoResult.getRankMap();
		ranks.putAll(result);
		return new LottoResult(ranks);
	}

	private static Map<Rank, Long> getRankMap() {
		Map<Rank, Long> ranks = new EnumMap<Rank, Long>(Rank.class);

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
