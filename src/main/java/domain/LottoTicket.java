package domain;

import java.util.List;

public class LottoTicket {

	private final List<Lotto> lottoTicket;

	public LottoTicket(List<Lotto> lottoTicket) {
		this.lottoTicket = lottoTicket;
	}

	public WinningResult confirmWinningResult(WinningNumbers winningNumbers) {
		WinningResult winningResult = WinningResult.initializeWinningResult();
		Lotto winningNumber = winningNumbers.getWinningNumbers();
		Number bonusNumber = winningNumbers.getBonusNumber();
		for (Lotto lotto : lottoTicket) {
			LottoRank rank = lotto.confirmWinningResult(winningNumber, bonusNumber);
			winningResult.addResult(rank);
		}
		return winningResult;
	}
}
