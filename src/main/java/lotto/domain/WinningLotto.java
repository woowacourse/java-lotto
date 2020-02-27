package lotto.domain;

/**
 * 당첨 번호 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class WinningLotto {
	private static final String LOTTO_BONUS_DUPLICATED_MESSAGE = "로또 번호와 보너스가 중복되면 안됩니다.";

	private final Lotto lotto;
	private final LottoNumber bonus;

	public WinningLotto(Lotto lotto, LottoNumber bonus) {
		validate(lotto, bonus);
		this.lotto = lotto;
		this.bonus = bonus;
	}

	private void validate(Lotto lotto, LottoNumber bonus) {
		if (lotto.contains(bonus)) {
			throw new IllegalArgumentException(LOTTO_BONUS_DUPLICATED_MESSAGE);
		}
	}

	public LottoRank match(Lotto target) {
		MatchCount matchCount = lotto.calculateMatchCount(target);
		boolean isBonus = target.contains(bonus);
		return LottoRank.of(matchCount, isBonus);
	}
}
