package lotto.dto;

import lotto.domain.LottoNumbers;

public class LottoDto {
	private final LottoNumbers lottoNumbers;

	public LottoDto(LottoNumbers lottoNumbers) {
		this.lottoNumbers = lottoNumbers;
	}

	public LottoNumbers getLottoNumbers() {
		return lottoNumbers;
	}
}
