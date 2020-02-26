package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import exception.LottoNumberRangeException;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final int LOTTO_MINIMUM_NUMBER = 1;
	private static final int LOTTO_MAXIMUM_NUMBER = 45;

	private static final List<LottoNumber> lottoNumbers;
	private static final int EXTRA_INDEX = 1;

	static {
		lottoNumbers = IntStream.rangeClosed(LOTTO_MINIMUM_NUMBER, LOTTO_MAXIMUM_NUMBER)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.toList());
	}

	private final int number;

	private LottoNumber(int number) {
		this.number = number;
	}

	public static LottoNumber createNumber(int number) {
		validate(number);
		return lottoNumbers.get(number - EXTRA_INDEX);
	}

	public static LottoNumber createNumber(String originNumber) {
		int number = Integer.parseInt(originNumber);
		return createNumber(number);
	}

	private static void validate(int number) {
		if (number < LOTTO_MINIMUM_NUMBER || number > LOTTO_MAXIMUM_NUMBER) {
			throw new LottoNumberRangeException();
		}
	}

	public int getNumber() {
		return number;
	}

	@Override
	public int compareTo(LottoNumber other) {
		return this.number - other.number;
	}
}
