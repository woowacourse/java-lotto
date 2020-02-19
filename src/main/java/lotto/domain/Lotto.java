package lotto.domain;

import java.util.List;

public class Lotto {
	List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return numbers.toString();
	}
}
