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

	@Override
	public String toString() {
		return lottoDtos.stream()
				.map(LottoDto::toString)
				.toString();
	}
}
