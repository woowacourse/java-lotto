package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lottos;

public class LottosDto {
	private final List<LottoDto> lottosDto;

	private LottosDto(final List<LottoDto> lottosDto) {
		this.lottosDto = lottosDto;
	}

	public static LottosDto from(Lottos lottos) {
		return lottos.getLottos().stream()
			.map(LottoDto::from)
			.collect(Collectors.collectingAndThen(Collectors.toList(), LottosDto::new));
	}

	public List<LottoDto> getLottosDto() {
		return lottosDto;
	}
}
