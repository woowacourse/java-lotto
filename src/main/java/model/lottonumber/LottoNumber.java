package model.lottonumber;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final int LOTTO_SIZE = 6;
	private static final int LOTTO_MIN_NUM = 1;
	private static final int LOTTO_MAX_NUM = 45;
	public static final String OUT_OF_RANGE_ERROR_MESSAGE = "[Error]: 로또 번호는 %d~%d 숫자여야 합니다.";
	private static final String NUMBER_SIZE_ERROR_MESSAGE = "[Error]: 로또는 %d개의 숫자여야 합니다.";
	private static final String NUMBER_ERROR_MESSAGE = "[Error]: 로또 번호는 숫자여야 합니다.";

	private final int number;

	private LottoNumber(int number) {
		if (number < LOTTO_MIN_NUM || number > LOTTO_MAX_NUM) {
			throw new IllegalArgumentException(
				String.format(OUT_OF_RANGE_ERROR_MESSAGE, LOTTO_MIN_NUM, LOTTO_MAX_NUM));
		}
		this.number = number;
	}

	public static List<LottoNumber> makeLottoNumbers() {
		return IntStream.range(LOTTO_MIN_NUM, LOTTO_MAX_NUM)
			.mapToObj(number -> LottoNumber.valueOf(number))
			.collect(Collectors.toList());
	}

	public static void validateSize(List<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException(String.format(NUMBER_SIZE_ERROR_MESSAGE, LOTTO_SIZE));
		}
	}

	public static LottoNumber valueOf(int number) {
		return new LottoNumber(number);
	}

	public static LottoNumber valueOf(LottoNumber lottoNumber) {
		return new LottoNumber(lottoNumber.getNumber());
	}

	public static LottoNumber parseLottoNumber(String number) {
		try {
			int lottoNumber = Integer.parseInt(number);
			return LottoNumber.valueOf(lottoNumber);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(NUMBER_ERROR_MESSAGE);
		}
	}

	public int getNumber() {
		return number;
	}

	@Override
	public int compareTo(LottoNumber o) {
		return this.number - o.number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof LottoNumber))
			return false;
		LottoNumber that = (LottoNumber)o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
