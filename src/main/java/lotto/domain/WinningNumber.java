package lotto.domain;

import lotto.exceptions.LottoNumberDuplicatedException;

public class WinningNumber {
	private static final String LOTTO_NUMBER_DUPLICATED_MESSAGE = "보너스 넘버와 중복될수 없습니다.";

	private Lotto winningNumber;
	private BonusNumber bonusNumber;

	public WinningNumber(Lotto winningNumber, BonusNumber bonusNumber) {
		this.winningNumber = winningNumber;

		if (bonusNumberDuplicatedWithWinningNumber(bonusNumber)) {
			throw new LottoNumberDuplicatedException(LOTTO_NUMBER_DUPLICATED_MESSAGE);
		}
		this.bonusNumber = bonusNumber;
	}

	private boolean bonusNumberDuplicatedWithWinningNumber(BonusNumber bonusNumber) {
		return winningNumber.getNumbers().contains(bonusNumber.getBonusNumber());
	}

	public Lotto getWinningNumber() {
		return winningNumber;
	}

	public BonusNumber getBonusNumber() {
		return bonusNumber;
	}
}
