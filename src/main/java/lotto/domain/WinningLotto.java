package lotto.domain;

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
}
