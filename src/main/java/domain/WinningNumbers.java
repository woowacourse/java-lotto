package domain;

public class WinningNumbers {

	private static final String DUPLICATE_NUMBER_EXCEPTION_MESSAGE = "당첨 번호와 보너스 번호는 중복될 수 없습니다";

	private final Lotto winningNumbers;
	private final Number bonusNumber;

	public WinningNumbers(Lotto winningNumbers, Number bonus) {
		checkDuplicateBonusNumber(winningNumbers, bonus);
		this.winningNumbers = winningNumbers;
		this.bonusNumber = bonus;
	}

	private void checkDuplicateBonusNumber(Lotto winningNumbers, Number bonus) {
		if (winningNumbers.isContain(bonus)) {
			throw new IllegalArgumentException(DUPLICATE_NUMBER_EXCEPTION_MESSAGE);
		}
	}

	public Lotto getWinningNumbers() {
		return winningNumbers;
	}

	public Number getBonusNumber() {
		return bonusNumber;
	}
}
