package lotto.dto;

import java.util.List;

public class LottoDto {
	private final List<Integer> lottoNumber;

	public LottoDto(List<Integer> lottoNumber) {
		this.lottoNumber = lottoNumber;
	}

	public List<Integer> getLottoNumber() {
		return lottoNumber;
	}
}
