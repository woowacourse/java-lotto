package lotto.dto;

import java.util.List;

public class LottosDto {
	private final List<LottoDto> lottosDto;

	public LottosDto(final List<LottoDto> lottosDto) {
		this.lottosDto = lottosDto;
	}

	public List<LottoDto> getLottosDto() {
		return lottosDto;
	}
}
