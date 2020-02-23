package lotto.domain;

import lotto.dto.LottoDto;

public class Lotto {
	public static final int SIZE = 6;

	private final LottoNumbers lottoNumbers;

	public Lotto(final LottoNumbers lottoNumbers) {
		validate(lottoNumbers);
		this.lottoNumbers = lottoNumbers;
	}

	private void validate(LottoNumbers lottoNumbers) {
		validateNull(lottoNumbers);
		validateSizeMismatch(lottoNumbers);
	}

	private void validateNull(LottoNumbers lottoNumbers) {
		if (lottoNumbers == null) {
			throw new IllegalArgumentException("null은 들어올 수 없습니다!");
		}
	}

	private void validateSizeMismatch(LottoNumbers lottoNumbers) {
		if (lottoNumbers.size() != SIZE) {
			throw new IllegalArgumentException("로또 번호는 " + SIZE + "개여야 합니다!");
		}
	}

	public WinningPrize findLottoPrize(WinningLotto winningLotto) {
		int matchCount = lottoNumbers.matchCount(winningLotto.getWinningNumber());
		boolean bonusMatch = lottoNumbers.contains(winningLotto.getBonusNumber());

		return WinningPrize.of(matchCount, bonusMatch);
	}

	public LottoNumbers getLottoNumbers() {
		return lottoNumbers;
	}
}
