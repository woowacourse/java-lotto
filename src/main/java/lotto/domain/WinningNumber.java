package lotto.domain;

public class WinningNumber {
	private Lotto winningNumber;
	private BonusNumber bonusNumber;

	public WinningNumber(Lotto winningNumber, BonusNumber bonusNumber) {
		this.winningNumber = winningNumber;

		if (bonusNumberNotDuplicatedWithWinningNumber(bonusNumber)) {
			throw new IllegalArgumentException("보너스 넘버와 중복될수 없습니다.");
		}
		this.bonusNumber = bonusNumber;
	}

	private boolean bonusNumberNotDuplicatedWithWinningNumber(BonusNumber bonusNumber) {
		return !winningNumber.getNumbers().contains(bonusNumber.getBonusNumber());
	}
}
