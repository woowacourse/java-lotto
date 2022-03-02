package domain;

import java.util.EnumMap;
import java.util.List;

public class LottoTicket {

	private final List<Lotto> lottoTicket;

	public LottoTicket(final List<Lotto> lottoTicket) {
		this.lottoTicket = List.copyOf(lottoTicket);
	}

	public WinningResult findWinningResult(final WinningNumbers winningNumbers) {
		EnumMap<LottoRank, Integer> winningResult = LottoRank.createWinningResultMap();
		Lotto winningNumber = winningNumbers.getWinningNumbers();
		LottoNumber bonusLottoNumber = winningNumbers.getBonusNumber();

		for (Lotto lotto : lottoTicket) {
			LottoRank rank = lotto.confirmWinningResult(winningNumber, bonusLottoNumber);
			addWinningResultCount(winningResult, rank);
		}
		return new WinningResult(winningResult);
	}

	private static void addWinningResultCount(final EnumMap<LottoRank, Integer> winningResult, final LottoRank rank) {
		if (LottoRank.isFail(rank)) {
			return;
		}
		winningResult.put(rank, winningResult.get(rank) + 1);
	}
}
