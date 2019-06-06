package com.woowacourse.lotto.domain;

import java.util.*;

import com.woowacourse.lotto.exception.InvalidNumberException;

import static com.woowacourse.lotto.domain.LottoNumber.*;

public class Lotto {
	public static final String DUPLICATE_LOTTO_NUMBER = "중복된 로또 넘버입니다.";
	private final Set<LottoNumber> numbers;

	public Lotto(final List<LottoNumber> numbers) {
		this.numbers = new TreeSet<>(numbers);
		if (this.numbers.size() != numbers.size()) {
			throw new InvalidNumberException(DUPLICATE_LOTTO_NUMBER);
		}
	}

	public int getCountOfMatchedNumber(Lotto lotto) {
		Set<LottoNumber> set = new TreeSet<>(this.numbers);
		set.removeAll(lotto.numbers);
		return NUMBER_OF_LOTTO - set.size();
	}

	public boolean contains(LottoNumber lottoNumber) {
		return numbers.contains(lottoNumber);
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
