package lotto.domain.lottocount;

import java.util.Objects;

/**
 * 수동 로또의 장수 객체
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/01
 */
public class ManualLottoCount extends LottoCount {
	private static final int MINIMUM_MANUAL_LOTTO_COUNT = 0;

	public ManualLottoCount(final String inputLottoCount, final int totalLottoCount) {
		super(inputLottoCount, totalLottoCount);
		this.lottoCount = parseToInteger(inputLottoCount);
	}

	@Override
	void validate(String inputLottoCount, int totalLottoCount) {
		Objects.requireNonNull(inputLottoCount, "수동 구매 장수는 null일 수 없습니다.");
		int integerManualLottoCount = parseToInteger(inputLottoCount);
		if (isInvalidRange(integerManualLottoCount, totalLottoCount)) {
			throw new IllegalArgumentException("수동 구매 장수는 0장에서 총 로또 구매 장수 사이여야합니다.");
		}
	}

	private boolean isInvalidRange(final int manualLottoCount, final int totalLottoCount) {
		return totalLottoCount < manualLottoCount || manualLottoCount < MINIMUM_MANUAL_LOTTO_COUNT;
	}
}
