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

	public WinningPrize findLottoPrize(WinningNumber winningNumber) {
		int matchCount = lottoNumbers.matchCount(winningNumber.getWinningNumber());
		boolean bonusMatch = lottoNumbers.contains(winningNumber.getBonusNumber());

		return WinningPrize.of(matchCount, bonusMatch);
	}

	public LottoDto makeLottoDto() {
		return new LottoDto(this.lottoNumbers);
	}
}
