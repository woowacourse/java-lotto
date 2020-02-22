package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoResult {
	public static final int PERCENT_MULTIPLIER = 100;

	private final Map<WinningType, Integer> lottoResult;

	public LottoResult(final Map<WinningType, Integer> lottoResult) {
		this.lottoResult = lottoResult;
	}

	public Map<WinningType, Integer> getLottoResult() {
		return lottoResult;
	}

	public List<WinningType> getWinningKeys() {
		return lottoResult.keySet()
				.stream()
				.filter((t) -> !t.isNone())
				.sorted()
				.collect(Collectors.toUnmodifiableList());
	}

	public double calculateEarningRate(PurchaseMoney purchaseMoney) {
		double totalEarning = 0;
		for (WinningType winningType : lottoResult.keySet()) {
			totalEarning += winningType.calculateEarning(lottoResult.get(winningType));
		}

		return purchaseMoney.divideBy(totalEarning) * PERCENT_MULTIPLIER;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LottoResult that = (LottoResult) o;
		return Objects.equals(lottoResult, that.lottoResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoResult);
	}
}
