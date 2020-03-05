package lotto.domain;

import lotto.exceptions.WinningLottoIllegalArgumentException;

import java.util.Objects;

public class WinningLotto {
	private final Lotto winningLottoNumbers;
	private final LottoNumber bonus;

	/**
	 * for invalid instance
	 */
	private WinningLotto() {
		winningLottoNumbers = Lotto.invalidInstance();
		bonus = LottoNumber.invalidInstance();
	}

	private WinningLotto(final Lotto winningLottoNumbers, final LottoNumber bonus) {
		checkWinningNumbersContainsBonus(winningLottoNumbers, bonus);

		this.winningLottoNumbers = winningLottoNumbers;
		this.bonus = bonus;
	}

	public static WinningLotto of(final Lotto winningLottoNumbers, final LottoNumber bonus) {
		return new WinningLotto(winningLottoNumbers, bonus);
	}

	public static WinningLotto invalidInstance() {
		return new WinningLotto();
	}

	public boolean isInvalidInstance() {
		return winningLottoNumbers.isInvalidInstance() ||
				bonus.isInvalidInstance();
	}

	private void checkWinningNumbersContainsBonus(Lotto winningLottoNumbers, LottoNumber bonus) {
		if (winningLottoNumbers.contains(bonus)) {
			throw new WinningLottoIllegalArgumentException();
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
}
