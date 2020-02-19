package lotto.domain;

public class WinningLotto {
	private final Lotto winningLotto;
	private final LottoNumber bonus;

	public WinningLotto(Lotto winningLotto, LottoNumber bonus) {
		validate(winningLotto, bonus);
		this.winningLotto = winningLotto;
		this.bonus = bonus;
	}

	private void validate(Lotto winningLotto, LottoNumber bonus) {
		if (winningLotto.contains(bonus)) {
			throw new IllegalArgumentException("로또 번호와 보너스가 중복되면 안됩니다.");
		}
	}

	public LottoRank match(Lotto lotto) {
		long matchCount = lotto.countOfMatch(winningLotto);
		boolean hasBonus = lotto.contains(bonus);
		return LottoRank.of(matchCount, hasBonus);
	}
}
