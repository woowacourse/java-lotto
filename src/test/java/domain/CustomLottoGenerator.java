package domain;

import java.util.List;

public class CustomLottoGenerator implements LottoGenerator {

	private List<Integer> numbers;

	public void initNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	@Override
	public List<Integer> generate() {
		return this.numbers;
	}

}
