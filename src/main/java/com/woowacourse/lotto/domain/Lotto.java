package com.woowacourse.lotto.domain;

import java.util.*;

import com.woowacourse.lotto.exception.InvalidNumberException;

import static com.woowacourse.lotto.domain.LottoNumber.*;

public class Lotto {
	private final Set<LottoNumber> numbers;

	public Lotto(final List<LottoNumber> numbers) {
		this.numbers = new TreeSet<>(numbers);
		if (this.numbers.size() != numbers.size()) {
			throw new InvalidNumberException(ExceptionOutput.DUPLICATE_LOTTO_NUMBER.getExceptionMessage());
		}
	}

	public int getCountOfMatchedNumber(Lotto lotto) {
		Set<LottoNumber> set = new TreeSet<>(this.numbers);
		set.removeAll(lotto.numbers);
		return NUMBER_OF_LOTTO - set.size();
	}

	public boolean contains(LottoNumber bonusBall) {
		return numbers.contains(bonusBall);
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
		for (LottoNumber lottoNumber : numbers) {
			stringJoiner.add(String.valueOf(lottoNumber.getName()));
		}
		return stringJoiner.toString();
	}
}
