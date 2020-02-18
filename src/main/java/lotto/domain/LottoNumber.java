package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {
	public static final int MIN = 1;
	public static final int MAX = 45;
	private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

	private final int number;

	static {
		for (int number = MIN; number <= MAX; ++number) {
			CACHE.put(number, new LottoNumber(number));
		}
	}

	private LottoNumber(int number) {
		validate(number);
		this.number = number;
	}

	public static LottoNumber of(int number) {
		validate(number);
		return CACHE.get(number);
	}

	private static void validate(int number) {
		if (number < MIN || number > MAX) {
			throw new IllegalArgumentException("생성할 수 없는 수입니다.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LottoNumber that = (LottoNumber)o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
