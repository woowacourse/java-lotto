package domain;

import java.util.List;
import java.util.Map;

public class LottoTicket {

	private final List<Lotto> lottoTicket;

	public LottoTicket(final List<Lotto> lottoTicket) {
		this.lottoTicket = List.copyOf(lottoTicket);
	}

	public WinningResult findWinningResult(final WinningNumbers winningNumbers) {
		Map<LottoRank, Integer> winningResult = LottoRank.createWinningResultMap();
		Lotto winningNumber = winningNumbers.getWinningNumbers();
		LottoNumber bonusLottoNumber = winningNumbers.getBonusNumber();

		for (Lotto lotto : lottoTicket) {
			LottoRank rank = lotto.confirmWinningResult(winningNumber, bonusLottoNumber);
			addWinningResultCount(winningResult, rank);
		}
		return new WinningResult(winningResult);
	}

	private static void addWinningResultCount(final Map<LottoRank, Integer> winningResult, final LottoRank rank) {
		if (LottoRank.isFail(rank)) {
			return;
		}
		winningResult.put(rank, winningResult.get(rank) + 1);
	}
}
