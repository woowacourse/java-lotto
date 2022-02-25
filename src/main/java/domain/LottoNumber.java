package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final int MIN_BOUND = 1;
	private static final int MAX_BOUND = 45;

	private static Map<Integer, LottoNumber> LOTTO_NUMBERS = new HashMap<>();

	static {
		IntStream.rangeClosed(MIN_BOUND, MAX_BOUND)
			.forEach(number -> LOTTO_NUMBERS.put(number, new LottoNumber(number)));
	}

	private final int lottoNumber;

	public LottoNumber(int lottoNumber) {
		checkRange(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	public LottoNumber(String lottoNumber) {
		this(Integer.parseInt(lottoNumber));
	}

	public static LottoNumber of(int number) {
		checkRange(number);
		return LOTTO_NUMBERS.get(number);
	}

	public static List<LottoNumber> ofList() {
		return new ArrayList<>(LOTTO_NUMBERS.values());
	}

	private static void checkRange(int lottoNumber) {
		if (lottoNumber < MIN_BOUND || lottoNumber > MAX_BOUND) {
			throw new IllegalArgumentException("로또 범위를 벗어난 숫자입니다.");
		}
	}

	public int getLottoNumber() {
		return lottoNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber that = (LottoNumber)o;
		return lottoNumber == that.lottoNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumber);
	}

	@Override
	public String toString() {
		return String.valueOf(lottoNumber);
	}

	@Override
	public int compareTo(LottoNumber o) {
		return Integer.compare(this.lottoNumber, o.lottoNumber);
	}
}
