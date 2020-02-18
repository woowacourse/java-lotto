package lotto.domain;

import java.util.Arrays;

/**
 * 로또번호 상수들에 대한 enum
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/18
 */
public enum LottoNumber {
	ONE(1),
	TWENTY(20),
	FORTY_FIVE(45);

	private final int lottoNumber;

	LottoNumber(final int inputLottoNumber) {
		this.lottoNumber = inputLottoNumber;
	}

	public static LottoNumber of(final int inputLottoNumber) throws WrongLottoNumberException {
		return Arrays.stream(LottoNumber.values())
				.filter(value -> value.lottoNumber == inputLottoNumber)
				.findFirst()
				.orElseThrow(() -> new WrongLottoNumberException("유효한 로또 번호가 아닙니다."));
	}
}
