package controller.strategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbersGenerationStrategy;
import utils.InputValidateUtils;
import view.StrategyView;

public class InputLottoNumbersGenerationStrategy implements LottoNumbersGenerationStrategy {
	private static final String NUMBER_SIZE_ERROR_MESSAGE = "[Error]: 로또는 %d개의 숫자여야 합니다.";
	private static final String LOTTO_NUMBER_REDUPLICATION_ERROR_MESSAGE = "[Error]: 로또 번호는 중복이 있으면 안됩니다";
	private static final String LOTTO_NUMBER_BLANK_ERROR_MESSAGE = "[Error]: 번호를 입력하세요.";
	private static final String DELIMITER_COMMA = ",";

	@Override
	public List<LottoNumber> generate(int size) {
		String input = inputNumbers();
		List<LottoNumber> numbers = makeLottoNumber(input);
		long count = numbers.stream().distinct().count();

		validateSize(numbers, size);
		validateReduplication(numbers, count);

		return numbers;
	}

	public String inputNumbers() {
		return StrategyView.inputLottoNumbers();
	}

	private void validateSize(List<LottoNumber> numbers, int size) {
		if (numbers.size() != size) {
			throw new IllegalArgumentException(String.format(NUMBER_SIZE_ERROR_MESSAGE, size));
		}
	}

	private void validateReduplication(List<LottoNumber> numbers, long count) {
		if (count != numbers.size()) {
			throw new IllegalArgumentException(LOTTO_NUMBER_REDUPLICATION_ERROR_MESSAGE);
		}
	}

	private List<LottoNumber> makeLottoNumber(String input) {
		InputValidateUtils.checkInputIsBlank(input, LOTTO_NUMBER_BLANK_ERROR_MESSAGE);
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
