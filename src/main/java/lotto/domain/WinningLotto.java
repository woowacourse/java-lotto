package lotto.domain;

import lotto.exceptions.WinningLottoException;

import java.util.Objects;

public class WinningLotto {
	private final Lotto winningLottoNumbers;
	private final LottoNumber bonus;

	private WinningLotto(final Lotto winningLottoNumbers, final LottoNumber bonus) {
		checkWinningNumbersContainsBonus(winningLottoNumbers, bonus);

		this.winningLottoNumbers = winningLottoNumbers;
		this.bonus = bonus;
	}

	public static WinningLotto of(final String winningLottoNumbers, final int bonusNumber) {
		Lotto lotto = Lotto.of(winningLottoNumbers);
		LottoNumber lottoNumber = LottoNumber.of(bonusNumber);
		return new WinningLotto(lotto, lottoNumber);
	}

	private void checkWinningNumbersContainsBonus(Lotto winningLottoNumbers, LottoNumber bonus) {
		if (winningLottoNumbers.contains(bonus)) {
			throw new WinningLottoException();
		}
	}

	public WinningType findMatchingWinningTypeWith(Lotto serialLottoNumber) {
		int sameNumberCount = serialLottoNumber.countMatching(winningLottoNumbers);
		boolean isContainsBonus = serialLottoNumber.contains(bonus);

		return WinningType.getWinningType(sameNumberCount, isContainsBonus);
	}

	public Lotto getWinningLottoNumbers() {
		return winningLottoNumbers;
	}

	public LottoNumber getBonus() {
		return bonus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WinningLotto that = (WinningLotto) o;
		return Objects.equals(winningLottoNumbers, that.winningLottoNumbers) &&
				Objects.equals(bonus, that.bonus);
	}

	@Override
	public int hashCode() {
		return Objects.hash(winningLottoNumbers, bonus);
	}

	@Override
	public String toString() {
		return "WinningLotto{" +
				"winningLottoNumbers=" + winningLottoNumbers +
				", bonus=" + bonus +
				'}';
	}
}
