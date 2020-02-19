package lotto.domain;

public class WinningLotto {
	private final WinningNumbers winningNumbers;
	private final BonusNumber bonusNumber;

	public WinningLotto(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
		this.winningNumbers = winningNumbers;
		this.bonusNumber = bonusNumber;
	}
}
