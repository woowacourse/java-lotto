package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class LottoNumber {
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;
	private static final String NUMBER_IN_RANGE = MIN_NUMBER + "이상 " + MAX_NUMBER + "이하의 숫자만 허용됩니다.";
	private static final Map<Integer, LottoNumber> TOTAL_LOTTO_NUMBER
		= IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
		.boxed()
		.collect(toMap(identity(), LottoNumber::new));

	private final int number;

	private LottoNumber(int number) {
		this.number = number;
	}

	public static LottoNumber of(int number) {
		validateNumberInRange(number);
		return TOTAL_LOTTO_NUMBER.get(number);
	}

	public int getNumber() {
		return number;
	}

	public static List<LottoNumber> getTotalLottoNumber() {
		return new ArrayList<>(TOTAL_LOTTO_NUMBER.values());
	}

	private static void validateNumberInRange(int number) {
		if (number > MAX_NUMBER || number < MIN_NUMBER) {
			throw new IllegalArgumentException(NUMBER_IN_RANGE);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof LottoNumber)) {
			return false;
		}
		if (o == this) {
			return true;
		}

		LottoNumber target = (LottoNumber) o;
		return number == target.getNumber();
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

}
