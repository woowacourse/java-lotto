package lotto.domain;

import lotto.domain.result.Statistic;
import lotto.exception.InvalidWinningLottoException;
import lotto.exception.LottoMismatchException;

public class WinningLotto {
	public static final int SECOND = 5;

	private final Lotto winningNumbers;
	private final Number bonusNumber;

	public WinningLotto(Lotto winningNumbers, Number bonusNumber) {
		validateDuplication(winningNumbers, bonusNumber);
		this.winningNumbers = winningNumbers;
		this.bonusNumber = bonusNumber;
	}

	public Statistic isWinningLotto(Lotto lotto) throws LottoMismatchException {
		int numberOfMatch = lotto.compare(winningNumbers);
		boolean isBonus = lotto.contains(bonusNumber);
		return Statistic.getRank(numberOfMatch, isBonus);
	}

	private boolean checkSecond(Lotto lotto, int numberOfMatch) {
		return numberOfMatch == SECOND && lotto.contains(bonusNumber);
	}

	private void validateDuplication(Lotto winningNumbers, Number bonusNumber) {
		if (winningNumbers.contains(bonusNumber)) {
			throw new InvalidWinningLottoException("보너스 번호와 당첨번호는 중복될 수 없습니다.");
		}
	}
}
