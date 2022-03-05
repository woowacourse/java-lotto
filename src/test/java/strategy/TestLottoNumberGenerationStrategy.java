package strategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbersGenerationStrategy;

public class TestLottoNumberGenerationStrategy implements LottoNumbersGenerationStrategy {

	@Override
	public List<LottoNumber> generate(int size) {
		return Arrays.asList(1, 2, 3, 4, 5, 6).stream()
			.map(number -> LottoNumber.valueOf(number))
			.collect(Collectors.toList());
	}
}
