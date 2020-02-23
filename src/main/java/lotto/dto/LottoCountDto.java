package lotto.dto;

import lotto.domain.LottoCount;

public class LottoCountDto {
	private final int lottoCount;

	private LottoCountDto(int lottoCount) {
		this.lottoCount = lottoCount;
	}

	public static LottoCountDto from(LottoCount lottoCount) {
		return new LottoCountDto(lottoCount.getLottoCount());
	}

	public int getLottoCount() {
		return lottoCount;
	}
}
