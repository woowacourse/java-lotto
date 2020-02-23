package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoResult {
	public static final int DEFAULT_VALUE = 0;
	public static final int PERCENT_MULTIPLIER = 100;

	private final Map<WinningType, Integer> lottoResult;

	public LottoResult(final Map<WinningType, Integer> lottoResult) {
		this.lottoResult = lottoResult;
	}

	public static LottoResult of(PurchasedLottoTickets purchasedLottoTickets,
								 WinningLottoNumbers winningLottoNumbers) {
		Map<WinningType, Integer> lottoResult = createDefaultMap();
		List<WinningType> winningTypes =
				purchasedLottoTickets.findMatchingWinningTypesWith(winningLottoNumbers);

		lottoResult.replaceAll((key, value) -> Collections.frequency(winningTypes, key));

		return new LottoResult(lottoResult);
	}

	private static Map<WinningType, Integer> createDefaultMap() {
		Map<WinningType, Integer> lottoResult = new HashMap<>();
		for (WinningType winningType : WinningType.values()) {
			lottoResult.put(winningType, DEFAULT_VALUE);
		}
		return lottoResult;
	}

	public double calculateEarningPercentage(PurchaseMoney purchaseMoney) {
		double totalEarning = 0;
		for (WinningType winningType : lottoResult.keySet()) {
			totalEarning += winningType.calculateEarning(lottoResult.get(winningType));
		}

		return purchaseMoney.divideBy(totalEarning) * PERCENT_MULTIPLIER;
	}

	public Map<WinningType, Integer> getLottoResult() {
		return lottoResult;
	}

	public List<WinningType> getWinningKeys() {
		return lottoResult.keySet()
				.stream()
				.filter(WinningType::isWin)
				.sorted()
				.collect(Collectors.toUnmodifiableList());
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
