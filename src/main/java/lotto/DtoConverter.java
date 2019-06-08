package lotto;

import lotto.domain.Lotto;
import lotto.view.LottoDto;
import lotto.view.LottosDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DtoConverter {
	public LottosDto convertLottosToDto(final List<Lotto> lottos) {
		List<LottoDto> LottoDtos = new ArrayList<>();
		for (final Lotto lotto : lottos) {
			LottoDto lottoDto = convertLottoToDto(lotto);
			LottoDtos.add(lottoDto);
		}
		return LottosDto.of(LottoDtos);
	}

	private LottoDto convertLottoToDto(final Lotto lotto) {
		List<String> numbers = lotto.getLottoNumbers()
				.stream()
				.map(lottoNumber -> lottoNumber.toString())
				.collect(Collectors.toList());
		return LottoDto.of(numbers);
	}
}
