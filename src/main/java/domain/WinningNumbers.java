package domain;

public class WinningNumbers {

	private final Lotto winningNumbers;
	private final Number bonusNumber;

	public WinningNumbers(Lotto winningNumbers, Number bonus) {
		checkDuplicateBonusNumber(winningNumbers, bonus);
		this.winningNumbers = winningNumbers;
		this.bonusNumber = bonus;
	}

	private void checkDuplicateBonusNumber(Lotto winningNumbers, Number bonus) {
		if (winningNumbers.isContain(bonus)) {
			throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다");
		}
	}
}
