package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	@Override
	public String toString() {
		return numbers.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof LottoDto)) return false;
		LottoDto lottoDto = (LottoDto) o;
		return Objects.equals(numbers, lottoDto.numbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numbers);
	}
}
