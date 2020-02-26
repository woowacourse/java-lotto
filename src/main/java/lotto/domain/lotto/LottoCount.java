package lotto.domain.lotto;

import java.util.Objects;

import lotto.domain.lottonumber.InvalidLottoNumberException;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/26
 */
public class LottoCount {
	private static final String NONE_INTEGER_INPUT_EXCEPTION_MESSAGE = "입력금액이 정수가 아닙니다.";
	private static final String MANUAL_LOTTO_COUNT_CAN_NOT_BE_NULL_EXCEPTION_MESSAGE = "수동 구매 장수는 null일 수 없습니다.";
	private static final String MANUAL_LOTTO_COUNT_RANGE_EXCEPTION_MESSAGE = "수동 구매 장수는 0장에서 총 로또 구매 장수 사이여야합니다.";
	private static final int MINIMUM_MANUAL_LOTTO_COUNT = 0;

	private final int totalLottoCount;
	private final int manualLottoCount;

	public LottoCount(int inputTotalLottoCount, String inputManualLottoCount) {
		this.totalLottoCount = inputTotalLottoCount;
		this.manualLottoCount = validateManualLottoCount(inputManualLottoCount);
	}


	private int validateManualLottoCount(String inputManualLottoCount) {
		Objects.requireNonNull(inputManualLottoCount, MANUAL_LOTTO_COUNT_CAN_NOT_BE_NULL_EXCEPTION_MESSAGE);
		int integerManualLottoCount = parseToInteger(inputManualLottoCount);
		if (integerManualLottoCount > this.totalLottoCount || integerManualLottoCount < MINIMUM_MANUAL_LOTTO_COUNT) {
			throw new IllegalArgumentException(MANUAL_LOTTO_COUNT_RANGE_EXCEPTION_MESSAGE);
		}
		return integerManualLottoCount;
	}

	private int parseToInteger(final String inputBonusLottoNumber) {
		try {
			return Integer.parseInt(inputBonusLottoNumber);
		} catch (NumberFormatException nfe) {
			throw new InvalidLottoNumberException(NONE_INTEGER_INPUT_EXCEPTION_MESSAGE);
		}
	}

	public int getAutoLottoCount() {
		return this.totalLottoCount - this.manualLottoCount;
	}

	public int getTotalLottoCount() {
		return this.totalLottoCount;
	}

	public int getManualLottoCount() {
		return this.manualLottoCount;
	}
}
