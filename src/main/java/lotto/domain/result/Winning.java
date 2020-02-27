package lotto.domain.result;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.SerialLottoNumber;
import lotto.exceptions.DuplicateWinningNumberException;

import java.util.Objects;

public class Winning {
	private final SerialLottoNumber winningNumbers;
	private final LottoNumber bonusNumber;

	public Winning(final SerialLottoNumber winningNumbers, final LottoNumber bonusNumber) {
		checkDuplicateLottoNumber(winningNumbers, bonusNumber);

		this.winningNumbers = winningNumbers;
		this.bonusNumber = bonusNumber;
	}

	private void checkDuplicateLottoNumber(SerialLottoNumber winningNumbers, LottoNumber bonusNumber) {
		if (winningNumbers.contains(bonusNumber)) {
			throw new DuplicateWinningNumberException();
		}
	}

	public Rank findMatchingRank(SerialLottoNumber lottoTicket) {
		return Rank.matching(lottoTicket.countMatchingNumber(winningNumbers), lottoTicket.contains(bonusNumber));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Winning winning = (Winning) o;
		return Objects.equals(winningNumbers, winning.winningNumbers) &&
				Objects.equals(bonusNumber, winning.bonusNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(winningNumbers, bonusNumber);
	}
}
