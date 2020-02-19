package lotto.domain;

public class WinningLotto {
	private final Lotto lotto;
	private final LottoNumber bonus;

	public WinningLotto(Lotto lotto, LottoNumber bonus) {
		validate(lotto, bonus);
		this.lotto = lotto;
		this.bonus = bonus;
	}

	private void validate(Lotto lotto, LottoNumber bonus) {
		if (lotto.contains(bonus)) {
			throw new IllegalArgumentException("로또 번호와 보너스가 중복되면 안됩니다.");
		}
	}

	public LottoRank match(Lotto target) {
		long matchCount = lotto.calculateMatchCount(target);
		boolean isBonus = target.contains(bonus);
		return LottoRank.of(matchCount, isBonus);
	}
}
