package lotto.domain;

public class WinningLotto {
	public static final String DUPLICATE_BONUS_NUMBER_ERROR = "보너스 번호가 당첨 번호와 중복됩니다.";

	private final Lotto winningLotto;
	private final LottoNumber bonusNumber;

	public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
		this.winningLotto = winningLotto;
		validateDuplicateWithWinningNumbers(bonusNumber);
		this.bonusNumber = bonusNumber;
	}

	private void validateDuplicateWithWinningNumbers(LottoNumber bonusNumber) {
		if (winningLotto.contains(bonusNumber)) {
			throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER_ERROR);
		}
	}

	public int countMatchingNumbersWith(Lotto lotto) {
		return lotto.countMatchingNumbersWith(winningLotto);
	}

	public boolean hasBonusMatchWith(Lotto lotto) {
		return lotto.contains(bonusNumber);
	}
}
