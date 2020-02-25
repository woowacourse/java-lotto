package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.exception.InvalidNumberException;
import lotto.util.StringUtil;

public class Number implements Comparable<Number> {
	public static final int MAX_LOTTO_NUMBER = 45;
	public static final int MIN_LOTTO_NUMBER = 1;
	private static final Map<Integer, Number> numbers = new HashMap<>();

	private final int number;

	static {
		for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
			numbers.put(i, new Number(i));
		}
	}

	private Number(int value) {
		this.number = value;
	}

	public static Number of(String number) {
		validate(number);
		return numbers.get(Integer.parseInt(number));
	}

	private static void validate(String value) {
		checkNull(value);
		checkBlank(value);
		checkNumberFormat(value);
		checkRange(value);
	}

	private static void checkNumberFormat(String value) {
		StringUtil.checkNumberFormat(value);
	}

	private static void checkRange(String value) {
		int number = Integer.parseInt(value);
		if (number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER) {
			throw new InvalidNumberException("로또 번호는 1에서 45까지만 가능합니다");
		}
	}

	private static void checkBlank(String value) {
		StringUtil.checkBlank(value);
	}

	private static void checkNull(String value) {
		StringUtil.checkNull(value);
	}

	public static List<Number> getNumbers() {
		return new ArrayList<>(numbers.values());
	}

	@Override
	public String toString() {
		return Integer.toString(number);
	}

	@Override
	public int compareTo(Number o) {
		return Integer.compare(this.number, o.number);
	}
}
