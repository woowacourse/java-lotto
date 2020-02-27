package lotto.domain;

public class Lotto {
	private final LottoNumbers lottoNumbers;

	public Lotto(final LottoNumbers lottoNumbers) {
		validate(lottoNumbers);
		this.lottoNumbers = lottoNumbers;
	}

	private void validate(LottoNumbers lottoNumbers) {
		validateNull(lottoNumbers);
	}

	private void validateNull(LottoNumbers lottoNumbers) {
		if (lottoNumbers == null) {
			throw new IllegalArgumentException("null은 들어올 수 없습니다!");
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
