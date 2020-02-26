package lotto.domain.lottonumber;

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
	private static final String NONE_INTEGER_INPUT_EXCEPTION_MESSAGE = "입력 보너스 번호가 정수가 아닙니다.";
	private static final List<LottoNumber> LOTTO_NUMBER_CACHE = new ArrayList<>();

	private final int lottoNumber;

	static {
		IntStream.rangeClosed(MINIMAL_LOTTO_VALUE, MAXIMUM_LOTTO_VALUE)
				.mapToObj(LottoNumber::new)
				.forEach(LOTTO_NUMBER_CACHE::add);
	}

	private LottoNumber(int inputLottoNumber) {
		validate(inputLottoNumber);
		this.lottoNumber = inputLottoNumber;
	}

	public LottoNumber(String inputLottoNumber) {
		validate(parseToInteger(inputLottoNumber));
		this.lottoNumber = Integer.parseInt(inputLottoNumber);
	}

	private static void validate(int inputLottoNumber) {
		if (inputLottoNumber < MINIMAL_LOTTO_VALUE || inputLottoNumber > MAXIMUM_LOTTO_VALUE) {
			throw new InvalidLottoNumberException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE);
		}
	}

	public static LottoNumber of(final int lottoNumber) {
		return LOTTO_NUMBER_CACHE.stream()
				.filter(value -> value.lottoNumber == lottoNumber)
				.findFirst()
				.orElseThrow(() -> new InvalidLottoNumberException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE));
	}

	public static int parseToInteger(final String inputBonusLottoNumber) {
		try { // TODO: 2020/02/26 이것도 수정해야할듯 책임이 이게 맞냐는 거지.
			return Integer.parseInt(inputBonusLottoNumber);
		} catch (NumberFormatException nfe) {
			throw new InvalidLottoNumberException(NONE_INTEGER_INPUT_EXCEPTION_MESSAGE);
		}
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
