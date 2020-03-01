package lotto.domain.lottonumber;

import lotto.util.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 지정된 범위의 로또 번호를 캐시로 가지고 있는 클래스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/21
 */
public class LottoNumber implements Comparable<LottoNumber> {
	private static final int MINIMAL_LOTTO_VALUE = 1;
	private static final int MAXIMUM_LOTTO_VALUE = 45;
	private static final String INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE = "유효한 로또 번호가 아닙니다.";
	private static final List<LottoNumber> LOTTO_NUMBER_CACHE = new ArrayList<>();

	private final int lottoNumber;

	static {
		IntStream.rangeClosed(MINIMAL_LOTTO_VALUE, MAXIMUM_LOTTO_VALUE)
				.mapToObj(LottoNumber::new)
				.forEach(LOTTO_NUMBER_CACHE::add);
	}

	private LottoNumber(final int inputLottoNumber) {
		validate(inputLottoNumber);
		this.lottoNumber = inputLottoNumber;
	}

	public LottoNumber(String inputLottoNumber) {
		validate(StringUtils.parseToInteger(inputLottoNumber));
		this.lottoNumber = Integer.parseInt(inputLottoNumber);
	}

	private static void validate(final int inputLottoNumber) {
		if (isValidRange(inputLottoNumber)) {
			throw new InvalidLottoNumberException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE);
		}
	}

	private static boolean isValidRange(int inputLottoNumber) {
		return inputLottoNumber < MINIMAL_LOTTO_VALUE || inputLottoNumber > MAXIMUM_LOTTO_VALUE;
	}

	public static LottoNumber of(final int lottoNumber) {
		return LOTTO_NUMBER_CACHE.stream()
				.filter(value -> value.lottoNumber == lottoNumber)
				.findFirst()
				.orElseThrow(() -> new InvalidLottoNumberException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE));
	}

	public static List<LottoNumber> getLottoNumberCache() {
		return new ArrayList<>(LOTTO_NUMBER_CACHE);
	}

	public int getLottoNumber() {
		return this.lottoNumber;
	}

	@Override
	public int compareTo(LottoNumber lottoNumber) {
		return this.lottoNumber - lottoNumber.lottoNumber;
	}

	public boolean equals(LottoNumber o) {
		if (this.lottoNumber == o.lottoNumber)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumber);
	}
}
