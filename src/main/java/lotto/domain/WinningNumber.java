package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {
	private final List<Integer> winningNumber;
	private final int bonusNumber;

	public WinningNumber(final List<String> winningNumber, final int bonusNumber) {
		validate(winningNumber);
		this.winningNumber = winningNumber.stream()
			.map(Integer::parseInt)
			.collect(Collectors.toList());
		this.bonusNumber = bonusNumber;
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
