package model.lotto;

import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbers;

public class LottoDTO {
	private final LottoNumbers numbers;

	public LottoDTO(LottoNumbers numbers) {
		this.numbers = LottoNumbers.valueOf(numbers);
	}

	public List<Integer> getNumbers() {
		return numbers.getNumbers()
			.stream()
			.map(LottoNumber::getNumber)
			.collect(Collectors.toList());
	}
}
