package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumbers;

public class LottoDto {
	private final LottoNumbers lottoNumbers;

	private LottoDto(LottoNumbers lottoNumbers) {
		this.lottoNumbers = lottoNumbers;
	}

	public static LottoDto from(Lotto lotto) {
		return new LottoDto(lotto.getLottoNumbers());
	}

	public LottoNumbers getLottoNumbers() {
		return lottoNumbers;
	}
}
