package lotto.domain;

public class WinningLotto {
	private final Lotto winningLotto;
	private final LottoNumber bonusNumber;

	public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
		validateDuplicated(winningLotto, bonusNumber);
		this.winningLotto = winningLotto;
		this.bonusNumber = bonusNumber;
	}

	private void validateDuplicated(Lotto winningLotto, LottoNumber bonusNumber) {
		if (winningLotto.isContains(bonusNumber)) {
			throw new InvalidWinningLottoException(InvalidWinningLottoException.DUPLICATED_BONUS_NUMBER);
		}
	}
}
