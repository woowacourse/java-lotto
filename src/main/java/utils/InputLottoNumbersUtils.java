package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.LottoNumber;

public class InputLottoNumbersUtils {
	private static final String LOTTO_NUMBER_BLANK_ERROR_MESSAGE = "[Error]: 번호를 입력하세요.";
	private static final String DELIMITER_COMMA = ",";

	public static List<LottoNumber> makeLottoNumber(String input) {
		InputValidateUtils.inputBlank(input, LOTTO_NUMBER_BLANK_ERROR_MESSAGE);
		List<String> numbers = splitLottoNumber(input);
		return makeInputNumbersToLottoNumbers(numbers);
	}

	private static List<String> splitLottoNumber(String input) {
		return Arrays.stream(input.split(DELIMITER_COMMA))
			.map(String::trim)
			.collect(Collectors.toList());
	}

	private static List<LottoNumber> makeInputNumbersToLottoNumbers(List<String> numbers) {
		return numbers.stream()
			.map(number -> LottoNumber.parseLottoNumber(number))
			.collect(Collectors.toList());
	}
}
