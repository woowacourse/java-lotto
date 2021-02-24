package lotto.domain;

import java.util.List;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
	public static final int MINIMUM_CANDIDATE_NUMBER = 1;
	public static final int MAXIMUM_CANDIDATE_NUMBER = 45;
	public static final String NUMBER_OUT_OF_BOUNDS_ERROR = String.format(
			"%d 에서 %d 사이의 숫자만 입력해주세요.",
			MINIMUM_CANDIDATE_NUMBER,
			MAXIMUM_CANDIDATE_NUMBER
	);
	private final int number;

	public LottoNumber(int number) {
		validateRangeOfNumber(number);
		this.number = number;
	}

	@Override
	public int compareTo(LottoNumber that) {
		return this.number - that.number;
	}

	private void validateRangeOfNumber(int number) {
		if (number < MINIMUM_CANDIDATE_NUMBER || MAXIMUM_CANDIDATE_NUMBER < number) {
			throw new IllegalArgumentException(NUMBER_OUT_OF_BOUNDS_ERROR);
		}
	}

	//TODO
	// this 로 바꾸기
	public boolean isIncludedIn(List<LottoNumber> numbers) {
		return numbers.contains(new LottoNumber(number));
	}

	public String getNumberAsString() {
		return String.valueOf(number);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LottoNumber that = (LottoNumber) o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
