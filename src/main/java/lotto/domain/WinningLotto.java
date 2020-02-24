package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.domain.lottonumber.LottoNumber;

public class WinningLotto {
	private final Lotto lotto;
	private final LottoNumber bonusNumber;

	public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
		if (lotto.isContains(bonusNumber)) {
			throw new InvalidWinningLottoException(InvalidWinningLottoException.DUPLICATION);
		}
		this.lotto = lotto;
		this.bonusNumber = bonusNumber;
	}

	public Lotto getLotto() {
		return lotto;
	}

	public LottoNumber getBonusNumber() {
		return bonusNumber;
	}
}
