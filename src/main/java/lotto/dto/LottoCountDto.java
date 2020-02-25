package lotto.dto;

import lotto.domain.LottoCount;

public class LottoCountDto {
	private final int manualLottoCount;
	private final int autoLottoCount;

	private LottoCountDto(int manualLottoCount, int autoLottoCount) {
		this.manualLottoCount = manualLottoCount;
		this.autoLottoCount = autoLottoCount;
	}

	public static LottoCountDto from(LottoCount lottoCount) {
		return new LottoCountDto(lottoCount.getManualLottoCount(), lottoCount.getAutoLottoCount());
	}

	public int getManualLottoCount() {
		return manualLottoCount;
	}

	public int getAutoLottoCount() {
		return autoLottoCount;
	}
}
