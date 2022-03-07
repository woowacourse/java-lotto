package controller.strategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbersGenerationStrategy;
import utils.InputValidateUtils;
import view.StrategyView;

public class StringInputLottoNumbersGenerationStrategy implements LottoNumbersGenerationStrategy {
	private static final String DELIMITER_COMMA = ",";

	@Override
	public List<LottoNumber> generate(int size) {
		String input = inputNumbers();
		List<LottoNumber> numbers = makeLottoNumber(input);

		return numbers;
	}

	public String inputNumbers() {
		return StrategyView.inputLottoNumbersAsString();
	}

	private List<LottoNumber> makeLottoNumber(String input) {
		InputValidateUtils.checkInputIsBlank(input);
		List<String> numbers = splitLottoNumber(input);
		return makeInputNumbersToLottoNumbers(numbers);
	}

	private List<String> splitLottoNumber(String input) {
		return Arrays.stream(input.split(DELIMITER_COMMA))
			.map(String::trim)
			.collect(Collectors.toList());
	}

	private List<LottoNumber> makeInputNumbersToLottoNumbers(List<String> numbers) {
		return numbers.stream()
			.map(number -> LottoNumber.parseLottoNumber(number))
			.collect(Collectors.toList());
	}
}
