package lotto.dto;

import java.util.List;

public class LottoDtos {
	private final List<LottoDto> lottoDtos;

	public LottoDtos(final List<LottoDto> lottoDtos) {
		this.lottoDtos = lottoDtos;
	}

	public List<LottoDto> getLottoDtos() {
		return lottoDtos;
	}
}
