package controller.strategy;

import java.util.List;
import java.util.Scanner;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbersGenerationStrategy;
import utils.InputLottoNumbersUtils;

public class InputLottoNumbersGenerationStrategy implements LottoNumbersGenerationStrategy {
	private static final String NUMBER_SIZE_ERROR_MESSAGE = "[Error]: 로또는 %d개의 숫자여야 합니다.";
	private static final String LOTTO_NUMBER_REDUPLICATION_ERROR_MESSAGE = "[Error]: 로또 번호는 중복이 있으면 안됩니다";

	private Scanner scanner = new Scanner(System.in);

	@Override
	public List<LottoNumber> generate(int size) {
		String input = inputNumbers();
		List<LottoNumber> numbers = InputLottoNumbersUtils.makeLottoNumber(input);
		long count = numbers.stream().distinct().count();

		validateSize(numbers, size);
		validateReduplication(numbers, count);

		return numbers;
	}

	public String inputNumbers() {
		return scanner.nextLine();
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
}
