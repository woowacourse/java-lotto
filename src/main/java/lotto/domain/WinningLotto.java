package lotto.domain;

import java.util.Objects;

import lotto.domain.exception.InvalidWinningLottoException;

public class WinningLotto {
	private final Lotto winningLotto;
	private final LottoNumber bonus;

	public WinningLotto(Lotto winningLotto, LottoNumber bonus) {
		validate(winningLotto, bonus);
		this.winningLotto = winningLotto;
		this.bonus = bonus;
	}

	private void validate(Lotto winningLotto, LottoNumber bonus) {
		if (winningLotto == null || bonus == null) {
			throw new IllegalArgumentException();
		}
		if (winningLotto.contains(bonus)) {
			throw new InvalidWinningLottoException();
		}
	}

	public LottoRank match(Lotto lotto) {
		int matchCount = lotto.countOfMatch(winningLotto);
		boolean hasBonus = lotto.contains(bonus);
		return LottoRank.of(matchCount, hasBonus);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WinningLotto that = (WinningLotto)o;
		return Objects.equals(winningLotto, that.winningLotto) &&
				Objects.equals(bonus, that.bonus);
	}

	@Override
	public int hashCode() {
		return Objects.hash(winningLotto, bonus);
	}
}
