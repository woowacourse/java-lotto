package lotto.domain.result;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoRank.LottoRank;
import lotto.domain.lottoRank.MatchCount;
import lotto.domain.lottoTicket.LottoTicket;

public class WinningLotto {
	private final LottoTicket winningLottoTicket;
	private final LottoNumber bonusLottoNumber;

	public WinningLotto(LottoTicket winningLottoTicket, LottoNumber bonusLottoNumber) {
		validateDuplication(winningLottoTicket, bonusLottoNumber);
		this.winningLottoTicket = winningLottoTicket;
		this.bonusLottoNumber = bonusLottoNumber;
	}

	private void validateDuplication(LottoTicket winningLottoTicket, LottoNumber bonusLottoNumber) {
		if (winningLottoTicket.contains(bonusLottoNumber)) {
			throw new InvalidWinningLottoException(InvalidWinningLottoException.DUPLICATION);
		}
	}

	public LottoRank match(LottoTicket lottoTicket) {
		MatchCount matchCount = lottoTicket.countMatchingWith(winningLottoTicket);
		boolean hasBonusLottoNumber = lottoTicket.contains(bonusLottoNumber);
		return LottoRank.of(matchCount, hasBonusLottoNumber);
	}
}
