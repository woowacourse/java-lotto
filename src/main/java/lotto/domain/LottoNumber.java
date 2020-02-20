package lotto.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import lotto.domain.exception.InvalidLottoNumberException;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final int MIN = 1;
	private static final int MAX = 45;
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

	public static LottoNumber of(String number) {
		try {
			return of(Integer.parseInt(number));
		} catch (NumberFormatException e) {
			throw e;
		}
	}

	public static Collection<LottoNumber> values() {
		return Collections.unmodifiableCollection(CACHE.values());
	}

	private static void validate(int number) {
		if (number < MIN || number > MAX) {
			throw new InvalidLottoNumberException("로또 번호는 1 ~ 45 사이의 수만 가능합니다.");
		}
	}

	public int getValue() {
		return number;
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

	@Override
	public int compareTo(LottoNumber lottoNumber) {
		return Integer.compare(number, lottoNumber.number);
	}
}
