package lotto.domain.result;

import lotto.domain.lotto.Lotto;
import lotto.domain.lottonumber.LottoNumber;

public class WinningLotto {
	private final Lotto lotto;
	private final LottoNumber bonusNumber;

	public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
		if (lotto.contains(bonusNumber)) {
			throw new InvalidWinningLottoException(InvalidWinningLottoException.DUPLICATION);
		}
		this.lotto = lotto;
		this.bonusNumber = bonusNumber;
	}

	public Lotto getLotto() {
		return lotto;
	}

	LottoNumber getBonusNumber() {
		return bonusNumber;
	}
}
