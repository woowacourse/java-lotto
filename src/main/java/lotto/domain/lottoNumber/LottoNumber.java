package lotto.domain.lottoNumber;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
	public static final int MIN_LOTTO_NUMBER = 1;
	public static final int MAX_LOTTO_NUMBER = 45;
	private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

	private final int lottoNumber;

	static {
		for (int lottoNumber = MIN_LOTTO_NUMBER; lottoNumber <= MAX_LOTTO_NUMBER; lottoNumber++) {
			CACHE.put(lottoNumber, new LottoNumber(lottoNumber));
		}
	}

	private LottoNumber(int lottoNumber) {
		validate(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	private void validate(int number) {
		if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
			throw new InvalidLottoNumberException(InvalidLottoNumberException.OUT_OF_BOUND_LOTTO_NUMBER);
		}
	}

	public static LottoNumber valueOf(int number) {
		if (number >= MIN_LOTTO_NUMBER && number <= MAX_LOTTO_NUMBER) {
			return CACHE.get(number);
		}
		return new LottoNumber(number);
	}

	public static LottoNumber valueOf(String inputNumber) {
		return valueOf(parseToInt(inputNumber));
	}

	private static int parseToInt(String inputNumber) {
		try {
			return Integer.parseInt(inputNumber);
		} catch (NumberFormatException e) {
			throw new InvalidLottoNumberException(InvalidLottoNumberException.NOT_INTEGER);
		}
	}

	public static Collection<LottoNumber> values() {
		return Collections.unmodifiableCollection(CACHE.values());
	}

	public int getLottoNumber() {
		return lottoNumber;
	}

	@Override
	public int compareTo(LottoNumber that) {
		return Integer.compare(this.lottoNumber, that.lottoNumber);
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
		return this.lottoNumber == that.lottoNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumber);
	}
}
