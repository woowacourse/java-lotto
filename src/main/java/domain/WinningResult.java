package domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class WinningResult {

	private static final int DEFAULT_RANK_COUNT = 0;
	private final EnumMap<LottoRank, Integer> winningResult;

	private WinningResult(EnumMap<LottoRank, Integer> winningResult) {
		this.winningResult = new EnumMap<>(winningResult);
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
			Map.entry(LottoRank.FIRST, DEFAULT_RANK_COUNT),
			Map.entry(LottoRank.SECOND, DEFAULT_RANK_COUNT),
			Map.entry(LottoRank.THIRD, DEFAULT_RANK_COUNT),
			Map.entry(LottoRank.FOURTH, DEFAULT_RANK_COUNT),
			Map.entry(LottoRank.FIFTH, DEFAULT_RANK_COUNT)
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
		winningResult.put(rank, increaseCount(winningResult, rank));
	}

	private static int increaseCount(EnumMap<LottoRank, Integer> winningResult, LottoRank rank) {
		return winningResult.get(rank) + 1;
	}

	public double getRateOfProfit(Money money) {
		long profit = winningResult.entrySet()
			.stream()
			.mapToLong(this::calculateProfit)
			.sum();
		return (double)profit / (double)money.getMoney();
	}

	private long calculateProfit(Map.Entry<LottoRank, Integer> rank) {
		return rank.getKey().getAmount() * rank.getValue();
	}

	public Map<LottoRank, Integer> getWinningResult() {
		return Collections.unmodifiableMap(winningResult);
	}
}
