package model.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.LottoNumber;

public class LottoDTO {
	private final List<LottoNumber> numbers;

	public LottoDTO(List<LottoNumber> numbers) {
		this.numbers = new ArrayList<>(numbers);
	}

	public List<Integer> getNumbers() {
		return numbers.stream()
			.map(number -> number.getNumber())
			.collect(Collectors.toList());
	}
}
