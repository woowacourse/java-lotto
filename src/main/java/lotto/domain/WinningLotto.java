package lotto.domain;

import java.util.Optional;

import lotto.domain.result.Statistic;
import lotto.exception.InvalidWinningLottoException;

public class WinningLotto {
	public static final int SECOND = 5;

	private final Lotto winningNumbers;
	private final Number bonusNumber;

	public WinningLotto(Lotto winningNumbers, Number bonusNumber) {
		validateDuplication(winningNumbers, bonusNumber);
		this.winningNumbers = winningNumbers;
		this.bonusNumber = bonusNumber;
	}

	public Optional<Statistic> isWinningLotto(Lotto lotto) {
		int numberOfMatch = lotto.compare(winningNumbers);
		if (checkSecond(lotto, numberOfMatch))
			return Optional.of(Statistic.BONUS);
		return Statistic.getRank(numberOfMatch);
	}

	public boolean checkSecond(Lotto lotto, int numberOfMatch) {
		return numberOfMatch == SECOND && lotto.contains(bonusNumber);
	}

	private void validateDuplication(Lotto winningNumbers, Number bonusNumber) {
		if (winningNumbers.contains(bonusNumber)) {
			throw new InvalidWinningLottoException("보너스 번호와 당첨번호는 중복될 수 없습니다.");
		}
	}
}
