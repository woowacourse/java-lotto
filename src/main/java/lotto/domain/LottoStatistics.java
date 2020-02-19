package lotto.domain;

import java.util.Map;
import java.util.Objects;

public class LottoStatistics {
	private final Map<LottoRank, Long> statistics;

	public LottoStatistics(Map<LottoRank, Long> statistics) {
		validate(statistics);
		this.statistics = Map.copyOf(statistics);
	}

	private void validate(Map<LottoRank, Long> statistics) {
		if (Objects.isNull(statistics) || statistics.isEmpty()) {
			throw new IllegalArgumentException("통계를 수행할 로또 결과가 없습니다.");
		}
	}

	public long calculateTotalProfits(LottoPurchaseMoney money) {
		long totalWinning = calculateTotalWinnings();
		System.out.println(money);
		return totalWinning * 100 / money.getValue();
	}

	private long calculateTotalWinnings() {
		return statistics.entrySet()
				.stream()
				.mapToLong(result -> result.getKey().calculateTotalWinnings(result.getValue()))
				.sum();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LottoStatistics that = (LottoStatistics)o;
		return Objects.equals(statistics, that.statistics);
	}

	@Override
	public int hashCode() {
		return Objects.hash(statistics);
	}
}
