package lotto.domain.lotto;

import java.util.Objects;

import lotto.domain.lottonumber.InvalidLottoNumberException;

/**
 * 구매한 로또 장수 정보를 가지는 객체
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/26
 */
public class LottoCount {
	private static final String NONE_INTEGER_INPUT_EXCEPTION_MESSAGE = "입력금액이 정수가 아닙니다.";
	private static final String MANUAL_LOTTO_COUNT_CAN_NOT_BE_NULL_EXCEPTION_MESSAGE = "수동 구매 장수는 null일 수 없습니다.";
	private static final String MANUAL_LOTTO_COUNT_RANGE_EXCEPTION_MESSAGE = "수동 구매 장수는 0장에서 총 로또 구매 장수 사이여야합니다.";
	private static final int MINIMUM_MANUAL_LOTTO_COUNT = 0;

	private final int lottoCount;

	public LottoCount(final int inputLottoCount) {
		this.lottoCount = inputLottoCount;
	}

	public LottoCount(final String inputLottoCount, final LottoCount totalLottoCount) {
		this.lottoCount = validateStringLottoCount(inputLottoCount, totalLottoCount);
	}

	private int validateStringLottoCount(String inputLottoCount, final LottoCount totalLottoCount) {
		Objects.requireNonNull(inputLottoCount, MANUAL_LOTTO_COUNT_CAN_NOT_BE_NULL_EXCEPTION_MESSAGE);
		int integerManualLottoCount = parseToInteger(inputLottoCount);
		if (isInvalidRange(integerManualLottoCount, totalLottoCount.getLottoCount())) {
			throw new IllegalArgumentException(MANUAL_LOTTO_COUNT_RANGE_EXCEPTION_MESSAGE);
		}
		return integerManualLottoCount;
	}

	private boolean isInvalidRange(final int manualLottoCount, final int totalLottoCount) {
		return totalLottoCount < manualLottoCount || manualLottoCount < MINIMUM_MANUAL_LOTTO_COUNT;
	}

	private int parseToInteger(final String inputBonusLottoNumber) {
		try {
			return Integer.parseInt(inputBonusLottoNumber);
		} catch (NumberFormatException nfe) {
			throw new InvalidLottoNumberException(NONE_INTEGER_INPUT_EXCEPTION_MESSAGE);
		}
	}

	public int getLottoCount() {
		return this.lottoCount;
	}
}
