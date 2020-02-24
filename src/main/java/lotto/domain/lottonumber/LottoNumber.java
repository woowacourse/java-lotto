package lotto.domain.lottonumber;

import java.util.*;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final int MIN_BOUND = 1;
	private static final int MAX_BOUND = 45;
	// TODO: 2020-02-24 상수니까 대문자로 바꾸자 캐시는 팩토리로 만들자 일립시스?를 이용하여 파라미터를 받아보자
	private static final Map<Integer, LottoNumber> cache = new HashMap<>();

	private final int number;

	static {
		for (int i = MIN_BOUND; i <= MAX_BOUND; i++) {
			cache.put(i, new LottoNumber(i));
		}
	}

	private LottoNumber(int number) {
		validateBound(number);
		this.number = number;
	}

	private void validateBound(int number) {
		if (number < MIN_BOUND || number > MAX_BOUND) {
			throw new InvalidLottoNumberException(InvalidLottoNumberException.OUT_OF_BOUND);
		}
	}

	public static LottoNumber valueOf(int number) {
		if (number >= MIN_BOUND && number <= MAX_BOUND) {
			return cache.get(number);
		}
		return new LottoNumber(number);
	}

	public static LottoNumber valueOf(String inputNumber) {
		int number = parseToInt(inputNumber);
		if (number >= MIN_BOUND && number <= MAX_BOUND) {
			return cache.get(number);
		}
		return new LottoNumber(number);
	}

	private static int parseToInt(String inputNumber) {
		try {
			return Integer.parseInt(inputNumber);
		} catch (NumberFormatException nfe) {
			throw new InvalidLottoNumberException(InvalidLottoNumberException.NOT_INTEGER);
		}
	}

	public static List<LottoNumber> values() {
		return Collections.unmodifiableList(new ArrayList<>(cache.values()));
	}

	public int getNumber() {
		return number;
	}

	@Override
	public int compareTo(LottoNumber o) {
		return number - o.number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber lottoNumber = (LottoNumber) o;
		return this.number == lottoNumber.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
