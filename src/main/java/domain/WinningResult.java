package domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class WinningResult {

	private static final int INITIAL_RANK_COUNT = 0;
	private static final int PLUS_COUNT = 1;
	private final EnumMap<LottoRank, Integer> winningResult;

	private WinningResult(final EnumMap<LottoRank, Integer> winningResult) {
		this.winningResult = winningResult;
	}

	public static WinningResult createWinningResult(final LottoTicket lottoTicket, final WinningNumbers winningNumbers) {
		EnumMap<LottoRank, Integer> winningResult = initializeWinningResult();
		Lotto winningNumber = winningNumbers.getWinningNumbers();
		Number bonusNumber = winningNumbers.getBonusNumber();

		addWinningResult(lottoTicket, winningResult, winningNumber, bonusNumber);
		return new WinningResult(winningResult);
	}

	public static EnumMap<LottoRank, Integer> initializeWinningResult() {
		return new EnumMap<>(Map.ofEntries(
			Map.entry(LottoRank.FIRST, INITIAL_RANK_COUNT),
			Map.entry(LottoRank.SECOND, INITIAL_RANK_COUNT),
			Map.entry(LottoRank.THIRD, INITIAL_RANK_COUNT),
			Map.entry(LottoRank.FOURTH, INITIAL_RANK_COUNT),
			Map.entry(LottoRank.FIFTH, INITIAL_RANK_COUNT)
		));
	}

	private static void addWinningResult(final LottoTicket lottoTicket, final EnumMap<LottoRank, Integer> winningResult,
		final Lotto winningNumber, final Number bonusNumber) {
		for (Lotto lotto : lottoTicket.getLottoTicket()) {
			LottoRank rank = lotto.confirmWinningResult(winningNumber, bonusNumber);
			addWinningResultCount(winningResult, rank);
		}
	}

	private static void addWinningResultCount(final EnumMap<LottoRank, Integer> winningResult, final LottoRank rank) {
		if (rank == LottoRank.FAIL) {
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
