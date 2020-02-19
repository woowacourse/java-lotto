package lotto.domain;

import lotto.exceptions.LottoNumberDuplicatedException;

public class WinningNumber {
	private Lotto winningNumber;
	private BonusNumber bonusNumber;

	public WinningNumber(Lotto winningNumber, BonusNumber bonusNumber) {
		this.winningNumber = winningNumber;

		if (bonusNumberDuplicatedWithWinningNumber(bonusNumber)) {
			throw new LottoNumberDuplicatedException("보너스 넘버와 중복될수 없습니다.");
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
