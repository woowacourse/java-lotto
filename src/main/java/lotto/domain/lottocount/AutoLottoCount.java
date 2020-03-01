package lotto.domain.lottocount;

/**
 * 자동 로또 장수 정보를 가지는 객체
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/26
 */
public class AutoLottoCount extends LottoCount {
	public AutoLottoCount(final String inputManualLottoCount, final int totalLottoCount) {
		super(inputManualLottoCount, totalLottoCount);
		this.lottoCount = totalLottoCount - parseToInteger(inputManualLottoCount);
	}

	@Override
	void validate(String inputLottoCount, int totalLottoCount) {
	}
}
