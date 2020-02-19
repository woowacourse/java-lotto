package lotto.domain;

import lotto.domain.result.Statistic;
import lotto.exception.InvalidWinningLottoException;

public class WinningLotto {

	private final WinningNumbers winningNumbers;
	private final BonusNumber bonusNumber;

	public WinningLotto(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
		this.winningNumbers = winningNumbers;
		validateDuplication(winningNumbers, bonusNumber);
		this.bonusNumber = bonusNumber;
	}

	public Statistic isWinningLotto(Lotto lotto) {
		int numberOfMatch = lotto.compareLotto(winningNumbers);
		if (numberOfMatch == 5 && lotto.hasBonusNumber(bonusNumber)) {
			return Statistic.BONUS;
		}
		return Statistic.getRank(numberOfMatch);
	}

	private void validateDuplication(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
		if (winningNumbers.getNumbers().contains(bonusNumber.getNumber())) {
			throw new InvalidWinningLottoException("보너스 번호와 당첨번호는 중복될 수 없습니다.");
		}
	}

}
