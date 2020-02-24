package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {
	private static final int PERCENT_MULTIPLIER = 100;
	private static final int INITIAL_COUNT = 0;
	private static final int INITIAL_EARNING = 0;

	private final Map<WinningType, Integer> lottoResult;

	public LottoResult(final Map<WinningType, Integer> lottoResult) {
		this.lottoResult = Collections.unmodifiableMap(lottoResult);
	}

	public static LottoResult of(PurchasedLottoTickets purchasedLottoTickets,
								 WinningInformation winningInformation) {

		Map<WinningType, Integer> initialLottoResult = Arrays.stream(WinningType.values())
				.collect(Collectors.toMap(winningType -> winningType, initialCount -> INITIAL_COUNT));

		List<WinningType> matchingWinningTypes =
				purchasedLottoTickets.findMatchingWinningTypesWith(winningInformation);

		initialLottoResult.replaceAll((winningType, count) ->
				Collections.frequency(matchingWinningTypes, winningType));

		return new LottoResult(initialLottoResult);
	}

	public double calculateEarningRate(PurchaseMoney purchaseMoney) {
		double totalEarning = INITIAL_EARNING;
		for (WinningType winningType : lottoResult.keySet()) {
			totalEarning += winningType.calculateEarning(lottoResult.get(winningType));
		}

		return purchaseMoney.divideBy(totalEarning) * PERCENT_MULTIPLIER;
	}

	public List<WinningType> getWinningKeys() {
		return lottoResult.keySet()
				.stream()
				.filter(WinningType::isWinning)
				.sorted()
				.collect(Collectors.toUnmodifiableList());
	}

	public Map<WinningType, Integer> getLottoResult() {
		return Collections.unmodifiableMap(lottoResult);
	}
}
