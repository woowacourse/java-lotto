package lotto.domain;

import java.util.*;

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
	private static final List<LottoNumber> CACHE;

	private final int lottoNumber;

	static {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		for (int i = MINIMAL_LOTTO_VALUE; i <= MAXIMUM_LOTTO_VALUE; i++) {
			lottoNumbers.add(new LottoNumber(i));
		}
		CACHE = Collections.unmodifiableList(lottoNumbers);
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
		return CACHE.stream()
				.filter(value -> value.lottoNumber == lottoNumber)
				.findFirst()
				.orElseThrow(() -> new InvalidLottoNumberException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE));
	}

	public static int parseToInteger(final String inputBonusLottoNumber) {
		try {
			return Integer.parseInt(inputBonusLottoNumber);
		} catch (NumberFormatException nfe) {
			throw new InvalidLottoNumberException(NONE_INTEGER_INPUT_EXCEPTION_MESSAGE);
		}
	}

	public static List<LottoNumber> getCache() {
		return new ArrayList<>(CACHE);
	}

	public int getLottoNumber() {
		return this.lottoNumber;
	}

	@Override
	public int compareTo(LottoNumber lottoNumber) {
		return this.lottoNumber - lottoNumber.lottoNumber;
	}

	public boolean equals(LottoNumber o) {
		if (this.getLottoNumber() == o.getLottoNumber())
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumber);
	}
}
