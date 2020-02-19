package lotto.domain;

import java.util.List;

public class Lotto {
	private static final int MAX_LOTTO_NUMBER_RANGE = 45;
	private static final int MIN_LOTTO_NUMBER_RANGE = 1;
	private static final int LOTTO_NUMBER_SIZE = 6;

	private List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		if (isInvalidNumberRange(numbers)) {
			throw new IllegalArgumentException("1~45 범위의 숫자만 로또 번호가 될 수 있습니다.");
		}
		if (wrongAmountOfNumbers(numbers) || hasDuplicatedNumbers(numbers)) {
			throw new IllegalArgumentException("잘못된 로또 번호입니다. 중복 안됨, 갯수는 6개");
		}
		this.numbers = numbers;
	}

	private boolean hasDuplicatedNumbers(List<Integer> numbers) {
		return LOTTO_NUMBER_SIZE != numbers.stream().distinct().count();
	}

	private boolean wrongAmountOfNumbers(List<Integer> numbers) {
		return LOTTO_NUMBER_SIZE != numbers.size();
	}

	private boolean isInvalidNumberRange(List<Integer> numbers) {
		return numbers.stream()
			.anyMatch(number -> number > MAX_LOTTO_NUMBER_RANGE || number < MIN_LOTTO_NUMBER_RANGE);
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
}
