package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {

	private final List<Integer> winningNumbers;

	private WinningNumbers(List<Integer> winningNumbers) {
		checkWinningNumbersRange(winningNumbers);
		checkWinningNumbersSize(winningNumbers);
		checkDuplicateWinningNumbers(winningNumbers);
		this.winningNumbers = winningNumbers;
	}

	public static WinningNumbers from(String[] userInput) {
		checkNotDigit(userInput);
		return new WinningNumbers(toWinningNumbersList(userInput));
	}

	private void checkDuplicateWinningNumbers(List<Integer> winningNumbers) {
		boolean duplicated = winningNumbers.stream()
			.distinct()
			.count() != winningNumbers.size();

		if (duplicated) {
			throw new IllegalArgumentException("중복된 숫자를 입력할 수 없습니다");
		}
	}

	private void checkWinningNumbersSize(List<Integer> winningNumbers) {
		if (winningNumbers.size() != 6) {
			throw new IllegalArgumentException("당첨 번호는 6개의 숫자여야 합니다");
		}
	}

	private static List<Integer> toWinningNumbersList(String[] userInput) {
		return Arrays.stream(userInput)
			.mapToInt(Integer::parseInt)
			.boxed()
			.collect(Collectors.toList());
	}

	private static void checkWinningNumbersRange(List<Integer> winningNumbers) {
		boolean isOutOfRange = winningNumbers.stream().anyMatch(number -> number < 1 || number > 45);
		if (isOutOfRange) {
			throw new IllegalArgumentException("당첨 번호는 1 ~ 45의 숫자여야 합니다");
		}
	}

	private static void checkNotDigit(String[] userInput) {
		boolean isDigit = Arrays.stream(userInput).allMatch(input -> input.matches("^[0-9]*$"));

		if (!isDigit) {
			throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다");
		}
	}
}
