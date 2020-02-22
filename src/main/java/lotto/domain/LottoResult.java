package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {
	static final int PERCENT_MULTIPLIER = 100;

	private final Map<WinningType, Integer> lottoResult;

	public LottoResult(final Map<WinningType, Integer> lottoResult) {
		this.lottoResult = lottoResult;
	}

	public double calculateEarningRate(PurchaseMoney purchaseMoney) {
		double totalEarning = 0;
		for (WinningType winningType : lottoResult.keySet()) {
			totalEarning += winningType.calculateEarning(lottoResult.get(winningType));
		}

		return purchaseMoney.divideBy(totalEarning) * PERCENT_MULTIPLIER;
	}

	public List<WinningType> getWinningKeys() {
		return lottoResult.keySet()
				.stream()
				.filter((t) -> !t.isNONE())
				.sorted()
				.collect(Collectors.toUnmodifiableList());
	}

	public Map<WinningType, Integer> getLottoResult() {
		return Collections.unmodifiableMap(lottoResult);
	}
}
