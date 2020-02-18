package lotto.domain;

public class WinningLottoNumbers {
	private final LottoNumbers numbers;
	private final LottoNumber bonusNumber;

	public WinningLottoNumbers(LottoNumbers numbers, LottoNumber bonusNumber) {
		validate(numbers, bonusNumber);
		this.numbers = numbers;
		this.bonusNumber = bonusNumber;
	}

	private void validate(LottoNumbers numbers, LottoNumber bonusNumber) {
		if (numbers.contains(bonusNumber)) {
			throw new IllegalArgumentException("로또 번호와 보너스가 중복되면 안됩니다.");
		}
	}

	public LottoRank match(LottoNumbers numbers) {
		int matchCount = this.numbers.calculateMatchCount(numbers);
		boolean isBonus = numbers.contains(bonusNumber);
		return LottoRank.of(matchCount, isBonus);
	}
}
