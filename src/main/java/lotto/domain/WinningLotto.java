package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {
	public static final String DUPLICATE_BONUS_NUMBER_ERROR = "보너스 번호가 당첨 번호와 중복됩니다.";

	private final LottoNumber bonusNumber;

	public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
		super(winningLotto.getLottoNumbers());

		validateDuplicateWithLottoNumbers(bonusNumber);
		this.bonusNumber = bonusNumber;
	}

	private void validateDuplicateWithLottoNumbers(LottoNumber bonusNumber) {
		if (bonusNumber.isIncludedIn(lottoNumbers)) {
			throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER_ERROR);
		}
	}

	public int countMatchingNumbersWith(List<LottoNumber> lottoNumbers) {
		return (int) this.lottoNumbers
				.stream()
				.filter(lottoNumbers::contains)
				.count();
	}

	public boolean hasBonusMatchWith(List<LottoNumber> numbers) {
		return numbers.contains(bonusNumber);
	}
}
