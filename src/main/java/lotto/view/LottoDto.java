package lotto.view;

import java.util.ArrayList;
import java.util.List;

public class LottoDto {
	private List<String> numbers;

	private LottoDto(final List<String> numbers) {
		this.numbers = new ArrayList<>(numbers);
	}

	public static LottoDto of(final List<String> numbers) {
		return new LottoDto(numbers);
	}

	public List<String> getNumbers() {
		return new ArrayList<>(numbers);
	}
}
