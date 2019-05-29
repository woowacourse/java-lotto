package com.woowacourse.lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.woowacourse.lotto.exception.InvalidNumberException;

import static com.woowacourse.lotto.domain.LottoNumber.*;


public class WinningLotto {
	private List<Integer> numbers;

	public WinningLotto(List<String> numbers) {
		this.numbers = numbers.stream().map(Integer::new).collect(Collectors.toList());
		validateDuplicateNumber();
		validateSize();
		validateNumber();
	}

	private void validateDuplicateNumber() {
		if (numbers.size() != new HashSet<>(numbers).size()) {
			throw new InvalidNumberException(ExceptionOutput.DUPLICATE_LOTTO_NUMBER.getExceptionMessage());
		}
	}

	private void validateSize() {
		if (numbers.size() != NUMBER_OF_LOTTO) {
			throw new InvalidNumberException(ExceptionOutput.VIOLATE_LOTTO_NUMBER_RANGE.getExceptionMessage());
		}
	}

	private void validateNumber() {
		if (numbers.stream().anyMatch(number -> number < MIN_NUMBER_OF_LOTTO || number > MAX_NUMBER_OF_LOTTO)) {
			throw new InvalidNumberException(ExceptionOutput.VIOLATE_LOTTO_NUMBER_RANGE.getExceptionMessage());
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof WinningLotto)) {
			return false;
		}
		final WinningLotto that = (WinningLotto) o;
		return Objects.equals(numbers, that.numbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numbers);
	}
}
