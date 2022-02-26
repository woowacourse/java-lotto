package domain;

public class WinningLotto {
	private final Lotto lotto;
	private final LottoNumber bonusNumber;

	public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
		if (lotto.isContain(bonusNumber)) {
			throw new IllegalArgumentException("보너스 숫자가 로또 번호에 포함되었습니다.");
		}
		this.lotto = lotto;
		this.bonusNumber = bonusNumber;
	}

	public Rank calculateRank(Lotto targetLotto) {
		return Rank.of(targetLotto.calculateMatchCount(lotto), hasBonus(targetLotto));
	}

	private boolean hasBonus(Lotto targetLotto) {
		return targetLotto.isContain(bonusNumber);
	}
}
