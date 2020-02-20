package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {
	private static final int LOTTO_SIZE = 6;

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
		validateNumberAmount(winningNumber);
	}

	private void validateNullAndEmpty(List<String> winningNumber) {
		if (winningNumber == null || winningNumber.isEmpty()) {
			throw new IllegalArgumentException("널이나 빈 값이 들어올 수 없습니다.");
		}
	}

	private void validateNumberAmount(List<String> winningNumber) {
		if (winningNumber.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException(String.format("로또 변호는 %s개여야 합니다.", LOTTO_SIZE));
		}
	}
}
