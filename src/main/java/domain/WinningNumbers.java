package domain;

public class WinningNumbers {

	private static final String DUPLICATE_WINNING_NUMBERS_AND_BONUS_NUMBER_MESSAGE = "당첨 번호와 보너스 번호는 중복될 수 없습니다";

	private final Lotto winningNumbers;
	private final Number bonusNumber;

	public WinningNumbers(final Lotto winningNumbers, final Number bonus) {
		checkDuplicateBonusNumber(winningNumbers, bonus);
		this.winningNumbers = winningNumbers;
		this.bonusNumber = bonus;
	}

	private void checkDuplicateBonusNumber(final Lotto winningNumbers, final Number bonus) {
		if (winningNumbers.isContain(bonus)) {
			throw new IllegalArgumentException(DUPLICATE_WINNING_NUMBERS_AND_BONUS_NUMBER_MESSAGE);
		}
	}

	public Lotto getWinningNumbers() {
		return winningNumbers;
	}

	public Number getBonusNumber() {
		return bonusNumber;
	}
}
