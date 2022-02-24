package domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class WinningResult {

	private final EnumMap<LottoRank, Integer> winningResult;

	private WinningResult(EnumMap<LottoRank, Integer> winningResult) {
		this.winningResult = winningResult;
	}

	public static WinningResult createWinningResult(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
		EnumMap<LottoRank, Integer> winningResult = initializeWinningResult();
		Lotto winningNumber = winningNumbers.getWinningNumbers();
		Number bonusNumber = winningNumbers.getBonusNumber();

		addWinningResult(lottoTicket, winningResult, winningNumber, bonusNumber);
		return new WinningResult(winningResult);
	}

	public static EnumMap<LottoRank, Integer> initializeWinningResult() {
		return new EnumMap<>(Map.ofEntries(
			Map.entry(LottoRank.FIRST, 0),
			Map.entry(LottoRank.SECOND, 0),
			Map.entry(LottoRank.THIRD, 0),
			Map.entry(LottoRank.FOURTH, 0),
			Map.entry(LottoRank.FIFTH, 0)
		));
	}

	private static void addWinningResult(LottoTicket lottoTicket, EnumMap<LottoRank, Integer> winningResult,
		Lotto winningNumber, Number bonusNumber) {
		for (Lotto lotto : lottoTicket.getLottoTicket()) {
			LottoRank rank = lotto.checkWinningResult(winningNumber, bonusNumber);
			addWinningResultCount(winningResult, rank);
		}
	}

	private static void addWinningResultCount(EnumMap<LottoRank, Integer> winningResult, LottoRank rank) {
		if (rank == LottoRank.FAIL) {
			return;
		}
		winningResult.put(rank, winningResult.get(rank) + 1);
	}

	public double getRateOfProfit(Money money) {
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
