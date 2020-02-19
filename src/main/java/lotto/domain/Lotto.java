package lotto.domain;

import java.util.List;

public class Lotto {
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
		return 6 != numbers.stream().distinct().count();
	}

	private boolean wrongAmountOfNumbers(List<Integer> numbers) {
		return 6 != numbers.size();
	}

	private boolean isInvalidNumberRange(List<Integer> numbers) {
		return numbers.stream()
			.anyMatch(number -> number > 45 || number < 1);
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
}
