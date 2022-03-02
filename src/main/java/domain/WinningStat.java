package domain;

import java.util.Arrays;
import java.util.Map;

public class WinningStat {

	public static final int DEFAULT_VALUE = 0;

	private final Map<LottoRank, Integer> stat;

	public WinningStat(Map<LottoRank, Integer> ranks) {
		stat = Map.copyOf(ranks);
	}

	public Map<LottoRank, Integer> getStat() {
		return stat;
	}

	public double calculateProfit(int ticketPrice) {
		long totalPrize = Arrays.stream(LottoRank.values())
			.mapToLong(rank -> (long) rank.getPrize() * stat.get(rank))
			.reduce(DEFAULT_VALUE, Long::sum);

		int num = stat.values().stream()
			.reduce(DEFAULT_VALUE, Integer::sum);

		return (double) totalPrize / (num * ticketPrice);
	}
}
