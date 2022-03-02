package domain;

public class WinningNumbers {

	private static final String DUPLICATE_WINNING_NUMBERS_AND_BONUS_NUMBER_MESSAGE = "당첨 번호와 보너스 번호는 중복될 수 없습니다";

	private final Lotto winningNumbers;
	private final LottoNumber bonusLottoNumber;

	public WinningNumbers(final Lotto winningNumbers, final LottoNumber bonus) {
		checkDuplicateBonusNumber(winningNumbers, bonus);
		this.winningNumbers = winningNumbers;
		this.bonusLottoNumber = bonus;
	}

	private void checkDuplicateBonusNumber(final Lotto winningNumbers, final LottoNumber bonus) {
		if (winningNumbers.isContain(bonus)) {
			throw new IllegalArgumentException(DUPLICATE_WINNING_NUMBERS_AND_BONUS_NUMBER_MESSAGE);
		}
	}

	public Lotto getWinningNumbers() {
		return winningNumbers;
	}

	public LottoNumber getBonusNumber() {
		return bonusLottoNumber;
	}
}
