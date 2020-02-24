package lotto.domain;

import lotto.exceptions.WinningLottoNumbersIllegalArgumentException;

import java.util.Objects;

public class WinningInformation {
	private final SerialLottoNumber winningLottoNumbers;
	private final LottoNumber bonusLottoNumber;

	public WinningInformation(final SerialLottoNumber winningLottoNumbers, final LottoNumber bonusLottoNumber) {
		checkWinningNumbersContainsBonus(winningLottoNumbers, bonusLottoNumber);

		this.winningLottoNumbers = winningLottoNumbers;
		this.bonusLottoNumber = bonusLottoNumber;
	}

	private void checkWinningNumbersContainsBonus(SerialLottoNumber winningLottoNumbers, LottoNumber bonus) {
		if (winningLottoNumbers.contains(bonus)) {
			throw new WinningLottoNumbersIllegalArgumentException();
		}
	}

	public WinningType findMatchingWinningTypeWith(SerialLottoNumber lottoTicket) {
		int sameNumberCount = lottoTicket.countMatching(winningLottoNumbers);
		boolean isBonusMatching = lottoTicket.contains(bonusLottoNumber);

		return WinningType.getWinningType(sameNumberCount, isBonusMatching);
	}

	public SerialLottoNumber getWinningLottoNumbers() {
		return winningLottoNumbers;
	}

	public LottoNumber getBonusLottoNumber() {
		return bonusLottoNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WinningInformation that = (WinningInformation) o;
		return Objects.equals(winningLottoNumbers, that.winningLottoNumbers) &&
				Objects.equals(bonusLottoNumber, that.bonusLottoNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(winningLottoNumbers, bonusLottoNumber);
	}
}
