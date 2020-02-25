package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
	private static final int INITIAL_PRIZE_COUNT = 0;
	private static final int PLUS_COUNT = 1;
	private static final double INITIAL_EARNING = 0D;
	private static final int INITIAL_COUNT = 0;
	private static final int PERCENT = 100;

	private Map<WinningPrize, Integer> winnerCountMapper;
	private long earningRate;

	public LottoResult(List<WinningPrize> winningPrizes) {
		validateNullAndEmpty(winningPrizes);
		initialize();
		countWinning(winningPrizes);
		this.earningRate = calculateEarningRate();
	}

	private void validateNullAndEmpty(List<WinningPrize> winningPrizes) {
		if (winningPrizes == null || winningPrizes.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값이 들어올 수 없습니다.");
		}
	}

	private void initialize() {
		winnerCountMapper = new HashMap<>();
		for (WinningPrize winningPrize : WinningPrize.values()) {
			winnerCountMapper.put(winningPrize, INITIAL_PRIZE_COUNT);
		}
	}

	private void countWinning(List<WinningPrize> winningPrizes) {
		for (WinningPrize winningPrize : winningPrizes) {
			winnerCountMapper.put(winningPrize, winnerCountMapper.get(winningPrize) + PLUS_COUNT);
		}
	}

	private long calculateEarningRate() {
		double totalEarning = INITIAL_EARNING;
		int totalCount = INITIAL_COUNT;

		for (WinningPrize winningPrize : WinningPrize.values()) {
			totalCount += winnerCountMapper.get(winningPrize);
			totalEarning += (winningPrize.getPrize() * winnerCountMapper.get(winningPrize));
		}
		return (long)(totalEarning / (totalCount * LottoCount.LOTTO_PRICE) * PERCENT);
	}

	public Map<WinningPrize, Integer> getWinnerCountMapper() {
		return winnerCountMapper;
	}

	public long getEarningRate() {
		return earningRate;
	}
}
