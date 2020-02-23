package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
	private static final int INITIAL_PRIZE_COUNT = 0;
	private static final int PLUS_COUNT = 1;
	private static final int INITIAL_EARNING = 0;
	private static final int INITIAL_COUNT = 0;
	private static final int PERCENT = 100;

	private Map<WinningPrize, Integer> lottoResult;

	public LottoResult(List<WinningPrize> winningPrizes) {
		validateNullAndEmpty(winningPrizes);
		initialize();
		countWinning(winningPrizes);
	}

	private void validateNullAndEmpty(List<WinningPrize> winningPrizes) {
		if (winningPrizes == null || winningPrizes.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값이 들어올 수 없습니다.");
		}
	}

	private void initialize() {
		lottoResult = new HashMap<>();
		for (WinningPrize winningPrize : WinningPrize.values()) {
			lottoResult.put(winningPrize, INITIAL_PRIZE_COUNT);
		}
	}

	private void countWinning(List<WinningPrize> winningPrizes) {
		for (WinningPrize winningPrize : winningPrizes) {
			lottoResult.put(winningPrize, lottoResult.get(winningPrize) + PLUS_COUNT);
		}
	}

	public long calculateEarningRate() {
		long totalEarning = INITIAL_EARNING;
		int totalCount = INITIAL_COUNT;

		for (WinningPrize winningPrize : WinningPrize.values()) {
			totalCount += lottoResult.get(winningPrize);
			totalEarning += (winningPrize.getPrize() * lottoResult.get(winningPrize));
		}
		return totalEarning / (totalCount * LottoCount.LOTTO_PRICE) * PERCENT;
	}

	public Map<WinningPrize, Integer> getLottoResult() {
		return lottoResult;
	}
}
