package lotto.domain;

import lotto.exceptions.WinningLottoNumbersIllegalArgumentException;

import java.util.Objects;

public class WinningLottoNumbers {
	private final SerialLottoNumber winningLottoNumbers;
	private final LottoNumber bonus;

	public WinningLottoNumbers(final SerialLottoNumber winningLottoNumbers, final LottoNumber bonus) {
		checkWinningNumbersContainsBonus(winningLottoNumbers, bonus);

		this.winningLottoNumbers = winningLottoNumbers;
		this.bonus = bonus;
	}

	private void checkWinningNumbersContainsBonus(SerialLottoNumber winningLottoNumbers, LottoNumber bonus) {
		if (winningLottoNumbers.contains(bonus)) {
			throw new WinningLottoNumbersIllegalArgumentException();
		}
	}

	public SerialLottoNumber getWinningLottoNumbers() {
		return winningLottoNumbers;
	}

	public LottoNumber getBonus() {
		return bonus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WinningLottoNumbers that = (WinningLottoNumbers) o;
		return Objects.equals(winningLottoNumbers, that.winningLottoNumbers) &&
				Objects.equals(bonus, that.bonus);
	}

	@Override
	public int hashCode() {
		return Objects.hash(winningLottoNumbers, bonus);
	}

	public WinningType findMatchingWinningTypeWith(SerialLottoNumber lottoTicket) {
		int sameNumberCount = lottoTicket.countMatching(winningLottoNumbers);
		boolean isContainsBonus = lottoTicket.contains(bonus);

		return WinningType.getWinningType(sameNumberCount, isContainsBonus);
	}
}
