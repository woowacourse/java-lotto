package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lotto.exception.InvalidNumberException;

public class Number {
	private static final int MIN_BOUND = 1;
	private static final int MAX_BOUND = 45;

	private final int number;

	private Number(int number) {
		validateBound(number);
		this.number = number;
	}

	private void validateBound(int number) {
		if (number < MIN_BOUND || number > MAX_BOUND) {
			throw new InvalidNumberException(InvalidNumberException.OUT_OF_BOUND);
		}
	}

	private static class NumberCache {
		final static List<Number> cache;

		static {
			cache = new ArrayList<>();

			int value = MIN_BOUND;
			for (int i = 0; i < ((MAX_BOUND - MIN_BOUND) + 1); i++) {
				cache.add(new Number(value++));
			}
		}

		private NumberCache() {
		}
	}

	public static Number valueOf(int number) {
		if (number >= MIN_BOUND && number <= MAX_BOUND) {
			return NumberCache.cache.get(number + (-MIN_BOUND));
		}
		return new Number(number);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Number number = (Number)o;
		return this.number == number.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
