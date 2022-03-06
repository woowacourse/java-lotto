package controller.strategy;

import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbersGenerationStrategy;

public class TestLottoNumberGenerationStrategy implements LottoNumbersGenerationStrategy {
	public final List<Integer> numbers;

	public TestLottoNumberGenerationStrategy(List<Integer> numbers) {
		this.numbers = numbers;
	}

	@Override
	public List<LottoNumber> generate(int size) {
		return numbers.stream()
			.map(number -> LottoNumber.valueOf(number))
			.collect(Collectors.toList());
	}
}
