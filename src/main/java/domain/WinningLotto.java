package domain;

import java.util.Objects;

public class WinningLotto {
	private final Lotto lotto;
	private final LottoNumber bonusNumber;

	public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
		this.lotto = Objects.requireNonNull(lotto, "[ERROR] WinningLotto에 lotto가 null 입니다.");
		this.bonusNumber = Objects.requireNonNull(bonusNumber, "[ERROR] WinningLotto에 bonusNumber기 null 입니다.");
		checkDuplicated(lotto, bonusNumber);
	}

	private void checkDuplicated(Lotto lotto, LottoNumber bonusNumber) {
		if (lotto.contains(bonusNumber)) {
			throw new IllegalArgumentException("보너스 숫자가 로또 번호에 포함되었습니다.");
		}
	}

	public Rank calculateRank(Lotto targetLotto) {
		return Rank.of(targetLotto.calculateMatchCount(lotto), hasBonus(targetLotto));
	}

	private boolean hasBonus(Lotto targetLotto) {
		return targetLotto.contains(bonusNumber);
	}
}
