package com.woowacourse.lotto.domain;

import java.util.*;

import com.woowacourse.lotto.exception.InvalidNumberException;

import static com.woowacourse.lotto.domain.LottoNumber.MAX_NUMBER_OF_LOTTO;
import static com.woowacourse.lotto.domain.LottoNumber.MIN_NUMBER_OF_LOTTO;

public class Lotto {
	private final Set<Integer> numbers;

	public Lotto(final List<Integer> numbers) {
		validateRange(numbers);
		this.numbers = new TreeSet<>(numbers);
	}

	private void validateRange(List<Integer> numbers) {
		if(numbers.stream().anyMatch(number -> MIN_NUMBER_OF_LOTTO > number || MAX_NUMBER_OF_LOTTO < number)) {
			throw new InvalidNumberException("잘못된 로또 넘버입니다.");
		}
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

		for(int i : numbers) {
			stringJoiner.add(String.valueOf(i));
		}

		return stringJoiner.toString();
	}
}
