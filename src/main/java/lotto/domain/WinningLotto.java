package lotto.domain;

import lotto.domain.result.Statistic;
import lotto.exception.InvalidWinningLottoException;

public class WinningLotto {
	private final Lotto winningNumbers;
	private final Number bonusNumber;

	public WinningLotto(Lotto winningNumbers, Number bonusNumber) {
		validateDuplication(winningNumbers, bonusNumber);
		this.winningNumbers = winningNumbers;
		this.bonusNumber = bonusNumber;
	}

	public Statistic countMatch(Lotto lotto) {
		int numberOfMatch = lotto.compare(winningNumbers);
		return Statistic.getRank(numberOfMatch, lotto.contains(bonusNumber));
	}

	private void validateDuplication(Lotto winningNumbers, Number bonusNumber) {
		if (winningNumbers.contains(bonusNumber)) {
			throw new InvalidWinningLottoException("보너스 번호와 당첨번호는 중복될 수 없습니다.");
		}
	}
}
