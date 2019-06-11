package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoGameResult;
import lotto.domain.LottoMachine;
import lotto.domain.Rank;
import lotto.view.LottoDto;
import lotto.view.LottosDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TODO result와 Lotto의 DtoConverter를 분리
public class DtoConverter {
	public List<LottoDto> convertLottosToDto(final List<Lotto> lottos) {
		List<LottoDto> lottosDto = new ArrayList<>();
		for (final Lotto lotto : lottos) {
			LottoDto dto = convertLottoToDto(lotto);
			lottosDto.add(dto);
		}
		return lottosDto;
	}

	public LottoDto convertLottoToDto(final Lotto lotto) {
		List<String> numbers = lotto.getLottoNumbers()
				.stream()
				.map(lottoNumber -> lottoNumber.toString())
				.collect(Collectors.toList());
		return LottoDto.of(numbers);
	}

	public GameResultDto convertResultToDto(final LottoGameResult gameResult) {
		Map<Rank, Integer> counts = new HashMap<>();
		for (Rank rank : Rank.values()) {
			counts.put(rank, gameResult.getRankCount(rank));
		}
		return GameResultDto.of(counts, gameResult.profit(LottoMachine.LOTTO_MONEY));
	}
}
