package lotto.domain.lottocount;

import lotto.domain.lottonumber.InvalidLottoNumberException;

/**
 * 로또 장수 인터페이스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/01
 */
public abstract class LottoCount {
	int lottoCount;

	public LottoCount(final String inputLottoCount, final int totalLottoCount) {
		validate(inputLottoCount, totalLottoCount);
	};

	abstract void validate(final String inputLottoCount, final int totalLottoCount);

	int parseToInteger(final String inputBonusLottoNumber) {
		try {
			return Integer.parseInt(inputBonusLottoNumber);
		} catch (NumberFormatException nfe) {
			throw new InvalidLottoNumberException("입력금액이 정수가 아닙니다.");
		}
	}

	public int getLottoCount() {
		return this.lottoCount;
	}
}
