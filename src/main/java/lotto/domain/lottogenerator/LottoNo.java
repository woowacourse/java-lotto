package lotto.domain.lottogenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNo implements Comparable<LottoNo> {
	private static final int MIN_LOTTO_NO = 1;
	private static final int MAX_LOTTO_NO = 45;
	private static final String ERROR_MESSAGE_LOTTO_RANGE = "1이상 45이하의 숫자를 입력하세요.";
	private final int number;

	public static Map<Integer, LottoNo> lottoNoBox = new HashMap<>();

	static {
		for (int number = LottoNo.MIN_LOTTO_NO; number <= LottoNo.MAX_LOTTO_NO; number++) {
			lottoNoBox.put(number, valueOf(number));
		}
	}

	private LottoNo(int number) {
		this.number = number;
	}

	private static LottoNo valueOf(int number) {
		validateLottoNo(number);
		return new LottoNo(number);
	}

	private static void validateLottoNo(int lottoNumber) {
		if (lottoNumber < MIN_LOTTO_NO || lottoNumber > MAX_LOTTO_NO) {
			throw new IllegalArgumentException(ERROR_MESSAGE_LOTTO_RANGE);
		}
	}

	public static LottoNo toLottoNo(int number) {
		return lottoNoBox.get(number);
	}

	@Override
	public int compareTo(LottoNo other) {
		return this.number - other.number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNo lottoNo = (LottoNo) o;
		return number == lottoNo.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public String toString() {
		return number + "";
	}
}
