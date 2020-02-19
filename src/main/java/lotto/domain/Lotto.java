package lotto.domain;

import java.util.List;

public class Lotto {
	private final List<Number> numbers;

	public Lotto(List<Number> numbers) {
		validateNullOrEmpty(numbers);
		validateDuplication(numbers);
		this.numbers = numbers;
	}

	private void validateNullOrEmpty(List<Number> numbers) {
		if (null == numbers || numbers.isEmpty()) {
			throw new InvalidLottoException(InvalidLottoException.NULL_OR_EMPTY);
		}
	}

	private void validateDuplication(List<Number> numbers) {
		long distinctSize = numbers.stream()
			.distinct()
			.count();

		if (distinctSize != numbers.size()) {
			throw new InvalidLottoException(InvalidLottoException.DUPLICATION);
		}
	}
}
