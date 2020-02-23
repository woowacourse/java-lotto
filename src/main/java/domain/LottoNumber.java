package domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final int LOTTO_MINIMUM_NUMBER = 1;
	private static final int LOTTO_MAXIMUM_NUMBER = 45;

	private static final List<LottoNumber> lottoNumbers;
	private static final int INDEX = 1;

	static {
		lottoNumbers = IntStream.range(LOTTO_MINIMUM_NUMBER, LOTTO_MAXIMUM_NUMBER + INDEX)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.toList());
	}

	private final int number;

	private LottoNumber(int number) {
		this.number = number;
	}

	public static LottoNumber get(int number) {
		validate(number);
		return lottoNumbers.get(number - INDEX);
	}

	private static void validate(int number) {
		if (number < LOTTO_MINIMUM_NUMBER || number > LOTTO_MAXIMUM_NUMBER) {
			throw new IllegalArgumentException("로또번호는 1~45의 수가 필요합니다.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber that = (LottoNumber)o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	public int getNumber() {
		return number;
	}

	@Override
	public int compareTo(LottoNumber other) {
		return this.number - other.number;
	}
}
