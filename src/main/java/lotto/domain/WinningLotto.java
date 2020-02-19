package lotto.domain;

import lotto.domain.result.Rank;
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

	public Rank isWinningLotto(Lotto lotto) {
		int numberOfMatch = lotto.countSameNumber(winningNumbers);
		if (numberOfMatch == SECOND && lotto.contains(bonusNumber)) {
			return Rank.BONUS;
		}
		return Rank.getRank(numberOfMatch);
	}

	private void validateDuplication(Lotto winningNumbers, Number bonusNumber) {
		if (winningNumbers.contains(bonusNumber)) {
			throw new InvalidWinningLottoException("보너스 번호와 당첨번호는 중복될 수 없습니다.");
		}
	}
}
