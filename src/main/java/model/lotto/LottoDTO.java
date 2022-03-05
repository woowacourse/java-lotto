package model.lotto;

import model.lottonumber.LottoNumbers;

public class LottoDTO {
	private final LottoNumbers numbers;

	public LottoDTO(LottoNumbers numbers) {
		this.numbers = LottoNumbers.valueOf(numbers);
	}

	@Override
	public String toString() {
		return numbers.toString();
	}
}
