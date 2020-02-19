package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WinningNumbers {
	public static final String DELIMITER = ", ";
	public static final int WINNING_NUMBER_SIZE = 6;
	public static final int ZERO = 0;

	private final List<LottoNumber> winningNumbers;

	public WinningNumbers(String winningNumbers) {
		List<String> winningNumbersValues = Arrays.asList(winningNumbers.split(DELIMITER));
		checkValidationOf(winningNumbersValues);

		this.winningNumbers = winningNumbersValues.stream()
			.mapToInt(Integer::parseInt)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.toList());
	}

	private void checkValidationOf(List<String> winningNumbersValue) {
		if (isNotMatchSize(winningNumbersValue)) {
			throw new IllegalArgumentException("당첨 번호는 보너스 번호를 제외하고 6자리 이어야 합니다");
		}
		if (isNotNumberFormat(winningNumbersValue)) {
			throw new IllegalArgumentException("당첨 번호는 정수만 가능합니다");
		}
	}

	private boolean isNotNumberFormat(List<String> winningNumbersValue) {
		return winningNumbersValue.stream()
			.anyMatch(isNotDigit());
	}

	private Predicate<String> isNotDigit() {
		return str -> str.chars()
				.anyMatch(ch -> !Character.isDigit(ch));
	}

	private boolean isNotMatchSize(List<String> winningNumbersValue) {
		return winningNumbersValue.size() != WINNING_NUMBER_SIZE;
	}
}
