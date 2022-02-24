package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class WinningResult {

	private static final int PLUS_COUNT = 1;
	private static final int INITIAL_COUNT = 0;
	private final EnumMap<LottoRank, Integer> winningResult;

	private WinningResult(final EnumMap<LottoRank, Integer> winningResult) {
		this.winningResult = winningResult;
	}

	public static WinningResult createWinningResult(final LottoTicket lottoTicket,
		final WinningNumbers winningNumbers) {
		EnumMap<LottoRank, Integer> winningResult = initializeWinningResult();
		Lotto winningNumber = winningNumbers.getWinningNumbers();
		Number bonusNumber = winningNumbers.getBonusNumber();

		for (Lotto lotto : lottoTicket.getLottoTicket()) {
			LottoRank rank = lotto.confirmWinningResult(winningNumber, bonusNumber);
			addWinningResultCount(winningResult, rank);
		}
		return new WinningResult(winningResult);
	}

	private static EnumMap<LottoRank, Integer> initializeWinningResult() {
		EnumMap<LottoRank, Integer> rankMap = new EnumMap<>(LottoRank.class);
		Arrays.stream(LottoRank.values())
			.filter(value -> !LottoRank.isFail(value))
			.forEach(value -> rankMap.put(value, INITIAL_COUNT));
		return rankMap;
	}

	private static void addWinningResultCount(final EnumMap<LottoRank, Integer> winningResult, final LottoRank rank) {
		if (LottoRank.isFail(rank)) {
			return;
		}
		winningResult.put(rank, winningResult.get(rank) + PLUS_COUNT);
	}

	public double getRateOfProfit(final Money money) {
		long profit = winningResult.entrySet()
			.stream()
			.mapToLong(result -> result.getKey().getAmount() * result.getValue())
			.sum();
		return (double)profit / (double)money.getMoney();
	}

	public Map<LottoRank, Integer> getWinningResult() {
		return Collections.unmodifiableMap(winningResult);
	}
}
