package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 로또 수 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class LottoNumber implements Comparable<LottoNumber> {
	public static final int MIN_VALUE = 1;
	public static final int MAX_VALUE = 45;
	private static final String INVALID_NUMBER_MESSAGE = "생성할 수 없는 수입니다.";
	private static final Map<Integer, LottoNumber> LOTTO_NUMBER_CACHE = new HashMap<>();

	static {
		for (int number = MIN_VALUE; number <= MAX_VALUE; ++number) {
			LOTTO_NUMBER_CACHE.put(number, new LottoNumber(number));
		}
	}

	private final int number;

	private LottoNumber(int number) {
		validate(number);
		this.number = number;
	}

	public static LottoNumber of(int number) {
		validate(number);
		return LOTTO_NUMBER_CACHE.get(number);
	}

	private static void validate(int number) {
		if (number < MIN_VALUE || number > MAX_VALUE) {
			throw new IllegalArgumentException(INVALID_NUMBER_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		LottoNumber that = (LottoNumber)object;
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

	@Override
	public String toString() {
		return String.valueOf(number);
	}
}
