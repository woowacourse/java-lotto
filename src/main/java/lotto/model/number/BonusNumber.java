package lotto.model.number;

import lotto.model.Lotto;

public class BonusNumber {
	private static final String ERROR_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다";

	private final Number number;

	private BonusNumber(Number number) {
		this.number = number;
	}

	public static BonusNumber from(Number number, WinningNumbers winningNumbers) {
		if (winningNumbers.match(number.getNumber())) {
			throw new IllegalArgumentException(ERROR_DUPLICATE);
		}
		return new BonusNumber(number);
	}

	public boolean match(Lotto lotto) {
		return lotto.contains(this.number.getNumber());
	}

}
