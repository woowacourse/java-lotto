package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {
	private final List<Integer> winningNumber;

	public WinningNumber(List<String> winningNumber) {
		validate(winningNumber);
		this.winningNumber = winningNumber.stream()
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	private void validate(List<String> winningNumber) {
		validateNullAndEmpty(winningNumber);
	}

	private void validateNullAndEmpty(List<String> winningNumber) {
		if (winningNumber == null || winningNumber.isEmpty()) {
			throw new IllegalArgumentException("널이나 빈 값이 들어올 수 없습니다.");
		}
	}
}
