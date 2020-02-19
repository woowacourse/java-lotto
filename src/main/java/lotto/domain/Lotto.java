package lotto.domain;

import java.util.List;

import lotto.exception.InvalidLottoException;

public class Lotto {
	public static final int CORRECT_SIZE = 6;

	private final List<Number> numbers;

	public Lotto(List<Number> numbers) {
		validateNull(numbers);
		validateSize(numbers);
		validateDuplication(numbers);
		this.numbers = numbers;
	}

	private void validateNull(List<Number> numbers) {
		if (null == numbers) {
			throw new InvalidLottoException(InvalidLottoException.NULL);
		}
	}

	private void validateSize(List<Number> numbers) {
		if (numbers.size() != CORRECT_SIZE) {
			throw new InvalidLottoException(InvalidLottoException.WRONG_SIZE);
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

	public List<Number> getNumbers() {
		return numbers;
	}
}
