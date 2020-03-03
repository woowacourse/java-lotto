package lotto.domain.result;

import lotto.domain.lotto.Lotto;
import lotto.domain.number.LottoNumber;

public class WinningLotto {
	private final Lotto lotto;
	private final LottoNumber bonusNumber;

	public WinningLotto(String inputWinningLottoNumber, String inputBonusLottoNumber) {
		lotto = Lotto.of(inputWinningLottoNumber);
		bonusNumber = LottoNumber.valueOf(inputBonusLottoNumber);
		validateDuplication();
	}

	private void validateDuplication() {
		if (lotto.contains(bonusNumber)) {
			throw new InvalidWinningLottoException(InvalidWinningLottoException.DUPLICATION);
		}
	}

	public Lotto getLotto() {
		return lotto;
	}

	LottoNumber getBonusNumber() {
		return bonusNumber;
	}
}
