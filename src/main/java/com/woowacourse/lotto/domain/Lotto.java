package com.woowacourse.lotto.domain;

import java.util.*;

import com.woowacourse.lotto.exception.InvalidNumberException;

import static com.woowacourse.lotto.domain.LottoNumber.*;

public class Lotto {
	private final Set<Integer> numbers;

	public Lotto(final List<Integer> numbers) {
		validateRange(numbers);
		this.numbers = new TreeSet<>(numbers);
	}

	private void validateRange(List<Integer> numbers) {
		if (!numbers.stream().allMatch(number -> (MIN_NUMBER_OF_LOTTO <= number || MAX_NUMBER_OF_LOTTO >= number))) {
			throw new InvalidNumberException(ExceptionOutput.VIOLATE_LOTTO_NUMBER_RANGE.getExceptionMessage());
		}
	}

	public int duplicateNumber(Lotto lotto) {
		Set<Integer> set = new TreeSet<>(this.numbers);
		set.removeAll(lotto.numbers);
		return NUMBER_OF_LOTTO - set.size();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Lotto)) {
			return false;
		}
		final Lotto lotto = (Lotto) o;
		return Objects.equals(numbers, lotto.numbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numbers);
	}

	@Override
	public String toString() {
		StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

		for (int i : numbers) {
			stringJoiner.add(String.valueOf(i));
		}

		return stringJoiner.toString();
	}
}
