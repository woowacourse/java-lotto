package domain;

import java.util.List;

public class Lotto {
	List<Integer> numbers;

	public Lotto(LottoNumbersGenerator lottoNumbersGenerator) {
		this.numbers = lottoNumbersGenerator.generate();
	}

	public int getNumbersSize() {
		return this.numbers.size();
	}
}
