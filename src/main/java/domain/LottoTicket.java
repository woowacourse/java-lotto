package domain;

import java.util.EnumMap;
import java.util.List;

public class LottoTicket {

	private static final int PLUS_COUNT = 1;
	private final List<Lotto> lottoTicket;

	public LottoTicket(final List<Lotto> lottoTicket) {
		this.lottoTicket = List.copyOf(lottoTicket);
	}

	public EnumMap<LottoRank, Integer> findWinningResult(final WinningNumbers winningNumbers) {
		EnumMap<LottoRank, Integer> winningResult = LottoRank.createWinningResultMap();
		Lotto winningNumber = winningNumbers.getWinningNumbers();
		Number bonusNumber = winningNumbers.getBonusNumber();

		for (Lotto lotto : lottoTicket) {
			LottoRank rank = lotto.confirmWinningResult(winningNumber, bonusNumber);
			addWinningResultCount(winningResult, rank);
		}
		return winningResult;
	}

	private static void addWinningResultCount(final EnumMap<LottoRank, Integer> winningResult, final LottoRank rank) {
		if (LottoRank.isFail(rank)) {
			return;
		}
		winningResult.put(rank, winningResult.get(rank) + PLUS_COUNT);
	}
}
